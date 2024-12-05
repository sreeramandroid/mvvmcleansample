import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rs.tmobiledemosample.R
import com.rs.tmobiledemosample.core.navigation.Screen

@Composable
fun MainScreen(onNavigateTo:(String) -> Unit ={}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top Half Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // App Title
            Text(
                text = "Rentapp",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Greeting Section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE0E8FF)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Hello friend",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Do you want to know what the best educational apps for professionals are? This ultimate guide",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }

        // Bottom Half Section
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 6.dp, start = 16.dp, end = 16.dp, bottom = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // First Card - takes up half the screen
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 6.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE0E8FF)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(modifier = Modifier
                    .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                OptionItem(
                    text = "I am a student",
                    iconRes = R.drawable.student_hat, // replace with your graduation icon resource
                    onClick = {
                        onNavigateTo(Screen.MaintoStudentScreen.route)
                    /* Handle student click */ }
                )
                }
            }

            // Second Card - takes up half the screen
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 2.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE0E8FF)),
                shape = RoundedCornerShape(8.dp)
            ) {
                OptionItem(
                    text = "I am a professional",
                    iconRes = R.drawable.briefcase_icon, // replace with your graduation icon resource
                    onClick = { /* Handle student click */ }
                )
            }
        }
    }
}

@Composable
fun OptionItem(text: String, iconRes: Int, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick() }
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF007AFF),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )

    }
}
