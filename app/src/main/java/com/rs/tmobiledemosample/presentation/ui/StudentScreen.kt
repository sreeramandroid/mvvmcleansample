package com.rs.tmobiledemosample.presentation.ui

import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rs.tmobiledemosample.R
import com.rs.tmobiledemosample.core.navigation.Screen
import com.rs.tmobiledemosample.data.model.Student
import com.rs.tmobiledemosample.presentation.viewmodel.StudentViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.time.temporal.ChronoUnit
import java.util.Locale
import kotlin.random.Random


@Composable
fun StudentScreen(
    viewModel: StudentViewModel = hiltViewModel(),
    onNavigateTo:(String) -> Unit ={}) {

    val selectedTab by viewModel.selectedTab
    val students by viewModel.students.collectAsState()
    val error by viewModel.error.collectAsState()

    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            // Tab Row
            // Top navigation tabs
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TabButton(
                    "Activity",
                    selectedTab == "Activity"
                ) { viewModel.onTabSelected("Activity") }
                TabButton(
                    "Learning Plan",
                    selectedTab == "Learning Plan"
                ) { viewModel.onTabSelected("Learning Plan") }
                TabButton(
                    "Progress",
                    selectedTab == "Progress"
                ) { viewModel.onTabSelected("Progress") }
            }

            // Display Content Based on Selected Tab
            when (selectedTab) {
                "Activity" -> ActivityContent(students)
                "Learning Plan" -> LearningPlanContent()
                "Progress" -> ProgressContent()
            }
        }
        BottomBar(Modifier.align(Alignment.BottomCenter))
        IconButton(
            onClick = {
                onNavigateTo(Screen.StudentScreenToMapScreen.route)
            },
            modifier = Modifier
                .padding(bottom = 35.dp)
                .clip(CircleShape)
                .background(Color.White)
                .align(Alignment.BottomCenter)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.profile_icon),
                contentDescription = "profile",
                tint = Color(0XFFA9A9A9),
                modifier = Modifier
                    .size(50.dp)
            )
        }
    }

}

@Composable
fun UnitProgressItem(unitProgress: Student) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8E1)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Unit${unitProgress.id}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = unitProgress.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "20 cards",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "${unitProgress.percentage}%",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))

                // Progress Bar
                LinearProgressIndicator(
                    progress = unitProgress.percentage / 100f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp),
                    color = Color(0xFFFF4081),
                    trackColor = Color(0xFFBBDEFB)
                )
            }
        }
    }
}

@Composable
fun TabButton(label: String, isSelected: Boolean, onClick: () -> Unit) {
    Text(
        text = label,
        color = if (isSelected) Color.Blue else Color.Gray,
        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun ActivityContent(students: List<Student>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        // Progress Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF007AFF)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Keep improving",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Implementing some reading skills strategies can improve a child",
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
                // Replace this icon with your actual resource
                Image(
                    painter = painterResource(id = R.drawable.books), // Replace with actual drawable
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Latest Results Title
        Text(
            text = "Latest results",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Unit Progress List
        LazyColumn {
            items(students) { item ->
                UnitProgressItem(unitProgress = item)
            }
        }
    }

}

@Composable
fun BottomBar(modifier: Modifier = Modifier) {

    var navNum by remember {
        mutableStateOf(0)
    }

//    Box(modifier = modifier.fillMaxWidth()) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            //.clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(Color.White)
            .padding(vertical = 15.dp, horizontal = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (navNum == 0) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.student_hat),
                        contentDescription = "home",
                        tint = Color(0XFFA9A9A9),
                        modifier = Modifier
                            .size(50.dp)
                    )
                }

            } else {
                IconButton(onClick = { navNum = 0 }) {
                    Icon(
                        painter = painterResource(id = R.drawable.student_hat),
                        contentDescription = "home",
                        tint = Color(0XFFA9A9A9),
                        modifier = Modifier
                            .size(50.dp)
                    )
                }
            }


            Spacer(modifier = Modifier.width(12.dp))
            if (navNum == 1) {

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.notification_icon),
                        contentDescription = "home",
                        tint = Color(0XFFA9A9A9),
                        modifier = Modifier.size(25.dp)
                    )
                }
            } else {
                IconButton(onClick = { navNum = 1 }) {
                    Icon(
                        painter = painterResource(id = R.drawable.notification_icon),
                        contentDescription = "home",
                        tint = Color(0XFFA9A9A9),
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (navNum == 2) {

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.settings_icon),
                        contentDescription = "home",
                        tint = Color(0XFFA9A9A9),
                        modifier = Modifier.size(25.dp)
                    )
                }
            } else {
                IconButton(onClick = { navNum = 2 }) {
                    Icon(
                        painter = painterResource(id = R.drawable.settings_icon),
                        contentDescription = "home",
                        tint = Color(0XFFA9A9A9),
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            if (navNum == 3) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.menu_icon),
                        contentDescription = "home",
                        tint = Color(0XFFA9A9A9),
                        modifier = Modifier.size(25.dp)
                    )
                }
            } else {
                IconButton(onClick = { navNum = 3 }) {
                    Icon(
                        painter = painterResource(id = R.drawable.menu_icon),
                        contentDescription = "home",
                        tint = Color(0XFFA9A9A9),
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
        }
    }
//    }
}

@Composable
fun LearningPlanContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        CalendarView()
    }
   // Text(text = "Learning Plan Content")
}







@Composable
private fun AddDefaultStackedBarChart() {

}





data class DataPoint(
    val x: Float,
    val y: Float
)

fun List<DataPoint>.xMax(): Float = maxByOrNull { it.x }?.x ?: 0f
fun List<DataPoint>.yMax(): Float = maxByOrNull { it.y }?.y ?: 0f

fun Float.toRealX(xMax: Float, width: Float) = (this / xMax) * width
fun Float.toRealY(yMax: Float, height: Float) = (this / yMax) * height


@Composable
fun LineChartWithShadow(
    dataPoint: List<DataPoint>
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(0.5f)
    ) {
        val width = size.width
        val height = size.height
        val spacingOf16DpInPixels = 16.dp.toPx()
        val verticalAxisLineStartOffset = Offset(spacingOf16DpInPixels, spacingOf16DpInPixels)
        val verticalAxisLineEndOffset = Offset(spacingOf16DpInPixels, height)

        drawLine(
            Color.Gray,
            verticalAxisLineStartOffset,
            verticalAxisLineEndOffset,
            strokeWidth = Stroke.DefaultMiter
        )

        val horizontalAxisLineStartOffset = Offset(spacingOf16DpInPixels, height)
        val horizontalAxisLineEndOffset = Offset(width - spacingOf16DpInPixels, height)

        drawLine(
            Color.Gray,
            horizontalAxisLineStartOffset,
            horizontalAxisLineEndOffset,
            strokeWidth = Stroke.DefaultMiter
        )

        val xMax = dataPoint.xMax()
        val yMax = dataPoint.yMax()
        val gradientPath = Path()

        gradientPath.moveTo(spacingOf16DpInPixels, height)
        dataPoint.forEachIndexed { index, curDataPoint ->
            var normX = curDataPoint.x.toRealX(xMax, width)
            var normY = curDataPoint.y.toRealY(yMax, height)

            if (index == 0) normX += spacingOf16DpInPixels
            if (index == dataPoint.size - 1) normX -= spacingOf16DpInPixels

            if (index < dataPoint.size - 1) {
                val offsetStart = Offset(normX, normY)
                var nextNormXPoint = dataPoint[index + 1].x.toRealX(xMax, width)

                if (index == dataPoint.size - 2)
                    nextNormXPoint = dataPoint[index + 1].x.toRealX(xMax, width = width) - spacingOf16DpInPixels

                val nextNormYPoint = dataPoint[index + 1].y.toRealY(yMax, height)
                val offsetEnd = Offset(nextNormXPoint, nextNormYPoint)

                drawLine(
                    Color(0XFF4d88e8).copy(alpha = 0.5f),
                    offsetStart,
                    offsetEnd,
                    strokeWidth = Stroke.DefaultMiter
                )
            }

            drawCircle(
                Color(0XFF4d88e8).copy(alpha = 0.5f),
                radius = 6.dp.toPx(),
                Offset(normX, normY)
            )
            with(
                gradientPath
            ) {
                lineTo(normX, normY)
            }
        }

        with(
            gradientPath
        ) {
            lineTo(width - spacingOf16DpInPixels, height)
            lineTo(0f, height)
            close()
            drawPath(
                this,
                brush = Brush.verticalGradient(colors = listOf(
                    Color(0XFF4d88e8).copy(alpha = 0.5f),
                    Color(0xFFAFFFC)
                ))
            )
        }
    }
}

@Composable
fun ProgressChart() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
            .background(Color.Transparent)
    ) {
        LineChartWithShadow(getDataPoints())
        Column(modifier = Modifier.padding(start = 12.dp, top = 135.dp)) {
            BarChart()
        }



        Spacer(modifier = Modifier.height(360.dp))

        // Statistics Section
        Column(modifier = Modifier.fillMaxSize().padding(top = 230.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 170.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatisticCard("Average Score", "70%")
                StatisticCard("Exams Taken", "12")
            }
        }
    }


}


private enum class Level(val color: Color) {
    Zero(Color(0xFFEBEDF0)),
    One(Color(0xFF9BE9A8)),
    Two(Color(0xFF40C463)),
    Three(Color(0xFF30A14E)),
    Four(Color(0xFF216E3A)),
}

private fun generateRandomData(startDate: LocalDate, endDate: LocalDate): Map<LocalDate, Level> {
    val levels = Level.entries
    return (0..ChronoUnit.DAYS.between(startDate, endDate))
        .associateTo(hashMapOf()) { count ->
            startDate.plusDays(count) to levels.random()
        }
}


@Composable
fun CalendarView() {
    val today = LocalDate.now()
    val tenDaysLater = today.plusDays(10)
    var selectedDate by remember { mutableStateOf(today) }
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Month Header with Navigation Arrows
        MonthHeader(
            month = currentMonth,
            onPreviousMonth = { currentMonth = currentMonth.minusMonths(1) },
            onNextMonth = { currentMonth = currentMonth.plusMonths(1) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Days of the Week Header
        DaysOfWeekHeader()

        Spacer(modifier = Modifier.height(8.dp))

        // Days Grid
        DaysGrid(
            currentMonth = currentMonth,
            today = today,
            tenDaysLater = tenDaysLater,
            selectedDate = selectedDate,
            onDateSelected = { selectedDate = it }
        )
    }
}

@Composable
fun MonthHeader(
    month: YearMonth,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "<",
            modifier = Modifier.clickable { onPreviousMonth() },
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "${month.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${month.year}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = ">",
            modifier = Modifier.clickable { onNextMonth() },
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun DaysOfWeekHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        DayOfWeek.values().forEach { dayOfWeek ->
            Text(
                text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun DaysGrid(
    currentMonth: YearMonth,
    today: LocalDate,
    tenDaysLater: LocalDate,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    val daysInMonth = currentMonth.lengthOfMonth()
    val firstDayOfMonth = currentMonth.atDay(1).dayOfWeek.value % 7
    val days = (1 - firstDayOfMonth..daysInMonth).map { it.takeIf { it > 0 } }

    Column {
        days.chunked(7).forEach { week ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                week.forEach { day ->
                    if (day == null) {
                        Spacer(modifier = Modifier.size(40.dp))
                    } else {
                        val date = currentMonth.atDay(day)
                        Day(
                            date = date,
                            isSelected = selectedDate == date,
                            isToday = date == today,
                            isTenDaysLater = date == tenDaysLater,
                            onClick = { onDateSelected(date) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Day(
    date: LocalDate,
    isSelected: Boolean,
    isToday: Boolean,
    isTenDaysLater: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = when {
        isSelected -> Color(0xFFD35C5C)
        isToday -> Color(0xFFD35C5C)
        isTenDaysLater -> Color(0xFFD35C5C)
        else -> Color.Transparent
    }

    Box(
        modifier = Modifier
            .size(40.dp)
            .background(color = backgroundColor, shape = MaterialTheme.shapes.medium)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = date.dayOfMonth.toString(),
            color = if (backgroundColor != Color.Transparent) Color.White else Color.Black
        )
    }
}


@Composable
fun StatisticCard(title: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = title, color = Color.Gray, fontSize = 14.sp)
        Text(text = value, color = Color(0xFF1565C0), fontWeight = FontWeight.Bold, fontSize = 24.sp)
    }
}



fun getDataPoints(): List<DataPoint> {
    val random = Random.Default
    return (0..10).map {
        DataPoint(it.toFloat(), random.nextInt(50).toFloat() + 1f)
    }
}

@Composable
fun BarChart() {
    val point = listOf(
        android.graphics.Point(10, 10),
        android.graphics.Point(90, 100),
        android.graphics.Point(170, 30),
        android.graphics.Point(250, 200),
        android.graphics.Point(330, 120),
        android.graphics.Point(410, 10),
        android.graphics.Point(490, 280),
        android.graphics.Point(570, 100),
        android.graphics.Point(650, 10),
        android.graphics.Point(730, 100),
        android.graphics.Point(810, 200),
    )

    val context = LocalContext.current
    var start by remember { mutableStateOf(false) }
    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )

    Canvas(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(0.5f)
            .background(Color.Transparent)
    ) {
        drawLine(
            start = Offset(10f, 600f),
            end = Offset(10f, 0f),
            color = Color.Black,
            strokeWidth = 3f
        )
        drawLine(
            start = Offset(10f, 600f),
            end = Offset(900f, 600f),
            color = Color.Black,
            strokeWidth = 3f
        )
        start = true

        for (p in point) {
            drawRect(
                color = Color(
                    0XFF4d88e8),
                topLeft = Offset(p.x + 30f, 600 - (600 - p.y) * heightPre),
                size = Size(55f, (600 - p.y) * heightPre)
            )
        }
    }
}



@Composable
fun ProgressContent() {
    ProgressChart()
}

