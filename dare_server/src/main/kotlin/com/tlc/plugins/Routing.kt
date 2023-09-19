package com.tlc.plugins

import com.tlc.utils.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.google.firebase.auth.FirebaseAuth
import io.ktor.http.*

fun Application.configureRouting() {
	routing {
		verifyToken("/verifyToken")
		register("/register")
		uploadImage("/uploadImage")
	}
}
