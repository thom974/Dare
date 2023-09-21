package com.tlc.dare_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.tlc.dare_android.ui.theme.Dare_androidTheme

@Composable
fun PrimaryButton(
	text: String,
	modifier: Modifier = Modifier.fillMaxWidth(),
	onClick: (() -> Unit) = { println("Primary button was clicked!") },
	fontSize: TextUnit = 16.sp
) {
	Button(
		onClick = onClick,
		modifier = modifier,
		colors = ButtonDefaults.buttonColors(
			containerColor = Color.Red,
			contentColor = Color.White
		),
		shape = RoundedCornerShape(4.dp)
	) {
		Text(
			text = text,
			fontSize = fontSize
		)
	}
}

@Composable
fun SecondaryButton(
	text: String,
	modifier: Modifier = Modifier.fillMaxWidth(),
	onClick: (() -> Unit) = { println("Secondary button was clicked!") },
	fontSize: TextUnit = 16.sp
) {
	Button(
		onClick = onClick,
		border = BorderStroke(1.dp, Color.Transparent),
		modifier = modifier,
		colors = ButtonDefaults.buttonColors(
			containerColor = Color.Transparent,
			contentColor = Color.Red
		),
		shape = RoundedCornerShape(4.dp)
	) {
		Text(
			text = text,
			fontSize = fontSize,
			textDecoration = TextDecoration.Underline
		)
	}
}

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			DiscoverFeedScreen()
		}
	}
}


@Composable
//@Preview(showBackground = true)
fun LandingScreen() {
	Column(
		modifier = Modifier
			.fillMaxSize(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Row(
			horizontalArrangement = Arrangement.spacedBy(10.dp),
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier
		) {
			Icon(
				imageVector = Icons.Rounded.ExitToApp,
				contentDescription = "Dare!",
				tint = Color.Red,
				modifier = Modifier
					.size(60.dp)
			)
			Text(
				text = "Dare!",
				fontSize = 60.sp
			)
		}
		Spacer(modifier = Modifier.height(8.dp))
		Text(
			text = "Delve outside your comfort zone.",
//			color = Color.Gray
		)
		Spacer(modifier = Modifier.height(36.dp))
		PrimaryButton(
			text = "Let's start!",
			modifier = Modifier
				.padding(horizontal = 32.dp)
				.fillMaxWidth()
		)
	}
}

@Composable
//@Preview(showBackground = true)
fun LoginScreen() {
	var (username, setUsername) = remember { mutableStateOf("") }
	var (password, setPassword) = remember { mutableStateOf("") }

	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(32.dp),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	){
		Text(
			text = "Login.",
			fontSize = 48.sp,
			modifier = Modifier
				.fillMaxWidth()
				.align(alignment = Alignment.Start)
		)
		Spacer(modifier = Modifier.height(24.dp))
		OutlinedTextField(
			value = "",
			modifier = Modifier.fillMaxWidth(),
			onValueChange = { username = it },
			label = { Text("Enter username") },
			colors = TextFieldDefaults.textFieldColors(
				containerColor = Color.White,
				focusedIndicatorColor = Color.Red,
				focusedLabelColor = Color.Red,
				cursorColor = Color.Red
			)
		)
		Spacer(modifier = Modifier.height(16.dp))
		OutlinedTextField(
			value = "",
			modifier = Modifier
				.fillMaxWidth(),
			onValueChange = { username = it },
			label = { Text("Enter password") },
			visualTransformation = PasswordVisualTransformation(),
			keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
			colors = TextFieldDefaults.textFieldColors(
				containerColor = Color.White,
				focusedIndicatorColor = Color.Red,
				focusedLabelColor = Color.Red,
				cursorColor = Color.Red
			)
		)
		Spacer(modifier = Modifier.height(32.dp))
		PrimaryButton(
			text = "Login",
			modifier = Modifier
				.fillMaxWidth()
		)
		Spacer(modifier = Modifier.height(8.dp))
		SecondaryButton(
			text = "Sign Up",
			modifier = Modifier
				.fillMaxWidth()
		)
	}
}

@Composable
//@Preview(showBackground = true)
fun CreateAccountScreen() {
	var (email, setEmail) = remember { mutableStateOf("") }
	var (username, setUsername) = remember { mutableStateOf("") }
	var (password, setPassword) = remember { mutableStateOf("") }
	var (phoneNumber, setPhoneNumber) = remember { mutableStateOf("") }

	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(32.dp),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	){
		Text(
			text = "Sign up.",
			fontSize = 48.sp,
			modifier = Modifier
				.fillMaxWidth()
				.align(alignment = Alignment.Start)
		)
		Spacer(modifier = Modifier.height(24.dp))
		OutlinedTextField(
			value = "",
			modifier = Modifier.fillMaxWidth(),
			onValueChange = { email = it },
			label = { Text("Enter email") },
			colors = TextFieldDefaults.textFieldColors(
				containerColor = Color.White,
				focusedIndicatorColor = Color.Red,
				focusedLabelColor = Color.Red,
				cursorColor = Color.Red
			)
		)
		Spacer(modifier = Modifier.height(16.dp))
		OutlinedTextField(
			value = "",
			modifier = Modifier.fillMaxWidth(),
			onValueChange = { username = it },
			label = { Text("Enter username") },
			colors = TextFieldDefaults.textFieldColors(
				containerColor = Color.White,
				focusedIndicatorColor = Color.Red,
				focusedLabelColor = Color.Red,
				cursorColor = Color.Red
			)
		)
		Spacer(modifier = Modifier.height(16.dp))
		OutlinedTextField(
			value = "",
			modifier = Modifier.fillMaxWidth(),
			onValueChange = { phoneNumber = it },
			label = { Text("Enter phone number") },
			colors = TextFieldDefaults.textFieldColors(
				containerColor = Color.White,
				focusedIndicatorColor = Color.Red,
				focusedLabelColor = Color.Red,
				cursorColor = Color.Red
			)
		)
		Spacer(modifier = Modifier.height(16.dp))
		OutlinedTextField(
			value = "",
			modifier = Modifier
				.fillMaxWidth(),
			onValueChange = { password = it },
			label = { Text("Enter password") },
			visualTransformation = PasswordVisualTransformation(),
			keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
			colors = TextFieldDefaults.textFieldColors(
				containerColor = Color.White,
				focusedIndicatorColor = Color.Red,
				focusedLabelColor = Color.Red,
				cursorColor = Color.Red
			)
		)
		Spacer(modifier = Modifier.height(32.dp))
		PrimaryButton(
			text = "Create account",
			modifier = Modifier
				.fillMaxWidth()
		)
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun DiscoverFeedScreen() {

	Scaffold(
		bottomBar = {
			BottomAppBar(
				containerColor = MaterialTheme.colorScheme.primaryContainer,
				contentColor = MaterialTheme.colorScheme.primary
			) {
//				Insert icons here
			}
		},
		floatingActionButton = {
			FloatingActionButton(
				onClick = { /*TODO*/ }
			) {
				Icon(Icons.Default.Add, contentDescription = "Add")
			}
		},
		modifier = Modifier.fillMaxSize()
	) { innerPadding ->
		Column(
			modifier = Modifier.padding(innerPadding),
			verticalArrangement = Arrangement.spacedBy(16.dp),
		) {

		}
	}
}
