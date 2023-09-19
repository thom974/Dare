package com.tlc.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
	val username: String,
	val email: String,
	val password: String
)
