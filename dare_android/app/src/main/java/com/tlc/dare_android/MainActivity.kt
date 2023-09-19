package com.tlc.dare_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.tlc.dare_android.ui.theme.Dare_androidTheme

@Composable
fun PrimaryButton(text: String, modifier: Modifier?, onClick: (() -> Unit)?) {
	Button(
		onClick = onClick ?: { println("Primary button was clicked!") },
		modifier = modifier ?: Modifier.fillMaxWidth(),
		colors = ButtonDefaults.buttonColors(
			containerColor = Color.Red,
			contentColor = Color.White
		)
	) {
		Text(
			text = text,
		)
	}
}

@Composable
fun SecondaryButton(text: String, modifier: Modifier?, onClick: (() -> Unit)?) {
	Button(
		onClick = onClick ?: { println("Secondary button was clicked!") },
		border = BorderStroke(1.dp, Color.Red),
		colors = ButtonDefaults.buttonColors(
			containerColor = Color.White,
			contentColor = Color.Red
		),
		modifier = modifier ?: Modifier.fillMaxWidth()
	) {
		Text(
			text = text,
		)
	}
}
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			Dare_androidTheme {
				// A surface container using the 'background' color from the theme
				Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

				}
			}
		}
	}
}
//@Composable
// @Preview(showBackground = true)
//fun SignUpLoginScreen() {
//	Column(
//		modifier = Modifier
//			.fillMaxSize()
//			.padding(16.dp),
//		horizontalAlignment = Alignment.CenterHorizontally,
//		verticalArrangement = Arrangement.Center
//	) {
//		TextField(
//			value = "email",
//			onValueChange = { },
//			label = { Text("Email") },
//			modifier = Modifier.fillMaxWidth()
//		)
//		Spacer(modifier = Modifier.height(16.dp))
//		TextField(
//			value = "password",
//			onValueChange = { },
//			label = { Text("Password") },
//			modifier = Modifier.fillMaxWidth()
//		)
//		Spacer(modifier = Modifier.height(16.dp))
//		Button(onClick = { /* Handle Sign Up */ }) {
//			Text("Sign Up")
//		}
//		Spacer(modifier = Modifier.height(8.dp))
//		Button(onClick = { /* Handle Login */ }) {
//			Text("Login")
//		}
//	}
//}

@Composable
@Preview(showBackground = true)
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
				.padding(32.dp)
				.fillMaxWidth()
		){}
	}
}

@Composable
@Preview(showBackground = true)
fun LoginScreen() {

}