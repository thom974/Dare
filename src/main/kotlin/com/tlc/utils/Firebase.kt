package com.tlc.utils

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserRecord.CreateRequest
import com.google.firebase.cloud.FirestoreClient
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.FileInputStream
import com.tlc.models.*

fun configureFirebase() {
	val serviceAccount = FileInputStream("creds.json")
	val options = FirebaseOptions.builder()
		.setCredentials(GoogleCredentials.fromStream(serviceAccount))
		.build()
	FirebaseApp.initializeApp(options)
}

/**
 * PARAMETERS:
 * none
 */
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
 * PARAMETERS:
 * username, email, password
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