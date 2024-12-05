package com.rs.tmobiledemosample.presentation.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.rs.tmobiledemosample.core.navigation.Screen
import com.rs.tmobiledemosample.presentation.viewmodel.AuthViewModel
import com.rs.tmobiledemosample.utils.phoneNumberFilter

@Composable
fun AuthScreen(onNavigateTo:(String) -> Unit ={},viewModel: AuthViewModel = hiltViewModel()) {
    val phoneNumber by viewModel.phoneNumber.collectAsState()
    val password by viewModel.password.collectAsState()
    val isFormValid by viewModel.isFormValid.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Phone Number Input Field
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = viewModel::onPhoneNumberChange,
            label = { Text("Phone number") },
            placeholder = { Text("(000) 000-0000") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            visualTransformation = { phoneNumberFilter(it) },
            maxLines = 1,
            isError = phoneNumber.isNotEmpty() && phoneNumber.length < 10
        )

        if (phoneNumber.isNotEmpty() && phoneNumber.length < 10) {
            Text(
                text = "Please enter a valid 10-digit phone number",
                color = Color.Red,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Password Input Field
        OutlinedTextField(
            value = password,
            onValueChange = viewModel::onPasswordChange,
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            isError = password.isNotEmpty() && password.length < 8
        )

        if (password.isNotEmpty() && password.length < 8) {
            Text(
                text = "Password must be at least 8 characters including special character and number",
                color = Color.Red,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Login Button (Enabled only if form is valid)
        Button(
            onClick = { /* Handle sign in or sign up logic here */
                onNavigateTo(Screen.AuthToMainScreen.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            enabled = isFormValid, // Button enabled state depends on form validation
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF007AFF),
                disabledContainerColor = Color(0xFFB0BEC5)
            )
        ) {
            Text(
                text = "LOG IN",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Forgot Password
        Text(
            text = "Forgot your password?",
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier.clickable { /* Handle forgot password action here */ }
        )
    }
}
