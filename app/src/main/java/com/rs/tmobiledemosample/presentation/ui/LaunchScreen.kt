package com.rs.tmobiledemosample.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
/**
 * Created by shankar
 * Screen used to display list of menu items with image and name and to perform shared element tranisitions
 *
 **/
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rs.tmobiledemosample.R
import com.rs.tmobiledemosample.core.navigation.Screen

@Composable
fun LaunchScreen(onNavigateTo:(String) -> Unit ={}) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF007AFF)) // Set background color similar to the image
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(180.dp))
            // Image of the books
            Image(
                painter = painterResource(id = R.drawable.books), // Replace with your actual image resource
                contentDescription = "Books",
                modifier = Modifier
                    .width(180.dp)
                    .height(180.dp)

            )

            Spacer(modifier = Modifier.height(50.dp))

            // Subtitle text
            Text(
                text = "You can study various \n sciences right at home",
                color = Color.White,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp)
            )

            Spacer(modifier = Modifier.height(60.dp))

            // Next Button
            Button(
                onClick = {
                    onNavigateTo(Screen.LaunchToAuthScreen.route)
                },
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .height(48.dp)
                    .width(200.dp)
            ) {
                Text(
                    text = "Next",
                    color = Color(0xFF007AFF),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            // Sign In text
            ClickableText(
                text = AnnotatedString("Don't have an account?"),
                onClick = {

                },
                style = TextStyle(
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(15.dp))
            ClickableText(
                text = AnnotatedString("Sign in here"),
                onClick = {

                },
                style = TextStyle(
                    color = Color.White.copy(alpha = 0.9f),
                     fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}

