package com.tlc.utils

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserRecord.CreateRequest
import com.google.firebase.cloud.StorageClient
import com.google.firebase.cloud.FirestoreClient
import com.tlc.models.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.FileInputStream
import io.github.cdimascio.dotenv.Dotenv

val dotenv: Dotenv = Dotenv.configure().load()

fun configureFirebase() {
	val serviceAccount = FileInputStream("creds.json")
	val options = FirebaseOptions.builder()
		.setCredentials(GoogleCredentials.fromStream(serviceAccount))
		.setStorageBucket(dotenv["DATABASE_NAME"])
		.build()
	FirebaseApp.initializeApp(options)
}

fun Route.verifyToken(url: String) {
	post(url) {
		val token = call.request.headers["Authorization"] ?: throw IllegalArgumentException("No token was provided.")
		try {
			val decodedToken = FirebaseAuth.getInstance().verifyIdToken(token)
			val uid = decodedToken.uid
			call.respond(HttpStatusCode.OK, "Token is valid for the user: $uid.")
		} catch (e: Exception) {
			call.respond(HttpStatusCode.Unauthorized, "Invalid token.")
		}
	}
}

/**
 * BODY:
 * username, email, password
 *
 * Make sure Content-Type set to application/json.
 */
fun Route.register(url: String) {
	post(url) {
		val data = call.receive<User>()
		val request = CreateRequest()
			.setDisplayName(data.username)
			.setEmail(data.email)
			.setPassword(data.password)

		try {
			val userRecord = FirebaseAuth.getInstance().createUser(request)
			call.respondText("User ${userRecord.uid} created with username: ${data.username}, email: ${data.email} and password: ${data.password}")
		} catch (e: Exception) {
			call.respondText("Error creating user: ${e.localizedMessage}")
		}
	}
}

/**
 * BODY:
 * uid,
 *
 * Make sure Content-Type is
 */
fun Route.uploadImage(url: String) {
	post(url) {
		val multipart = call.receiveMultipart()
		var userId: String? = null
		var imageUrl: String? = null

		multipart.forEachPart { part ->
			if (part is PartData.FileItem) {
				val inputStream = part.streamProvider()
				val storage = StorageClient.getInstance().bucket()
				val blob = storage.create(part.originalFileName!!, inputStream, part.contentType?.toString())
				imageUrl = blob.mediaLink
			}
			if (part is PartData.FormItem && part.name == "userId") {
				userId = part.value
			}
			part.dispose()
		}

		userId?.let {
			val db = FirestoreClient.getFirestore()
			val docRef = db.collection("users").document(it)
			docRef.set(mapOf("imageUrl" to imageUrl))
		}

		call.respondText("Image uploaded successfully!")
	}
}