package com.rs.tmobiledemosample.utils

import androidx.compose.foundation.shape.GenericShape
import androidx.compose.ui.geometry.Rect

val BottomBarShape = GenericShape { size, _ ->
    // Define the shape path for the bottom navigation bar with a center dip
    val width = size.width
    val height = size.height
    val arcRadius = 50f // Adjust this for the dip size

    moveTo(0f, 0f)
    lineTo(width / 2 - arcRadius, 0f)
    arcTo(
        rect = Rect(width / 2 - arcRadius, -arcRadius, width / 2 + arcRadius, arcRadius),
        startAngleDegrees = 180f,
        sweepAngleDegrees = -180f,
        forceMoveTo = false
    )
    lineTo(width, 0f)
    lineTo(width, height)
    lineTo(0f, height)
    close()
}


