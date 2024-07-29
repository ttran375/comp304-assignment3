package com.example.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    private val correctUserId = ""
    private val correctPassword = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen(
                onLoginSuccess = {
                    startActivity(Intent(this, SuccessActivity::class.java))
                },
                onLoginFailed = {
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                },
                correctUserId = correctUserId,
                correctPassword = correctPassword
            )
        }
    }
}


@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onLoginFailed: () -> Unit,
    correctUserId: String,
    correctPassword: String
) {
    var userId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Login Operation", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))
        OutlinedTextField(
            value = userId,
            onValueChange = { userId = it },
            label = { Text("User ID") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (userId == correctUserId && password == correctPassword) {
                    onLoginSuccess()
                } else {
                    onLoginFailed()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Click to Login")
        }
    }
}
