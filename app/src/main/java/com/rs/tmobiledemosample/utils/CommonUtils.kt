package com.rs.tmobiledemosample.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText

fun phoneNumberFilter(text: AnnotatedString): TransformedText {
    // Define the format of (000) 000-0000
    val trimmed = if (text.text.length >= 10) text.text.substring(0..9) else text.text
    val out = StringBuilder()
    var cursorOffset = 0
    for (i in trimmed.indices) {
        if (i == 0) out.append("(")
        else if (i == 3) out.append(") ")
        else if (i == 6) out.append("-")
        out.append(trimmed[i])
    }
    cursorOffset = out.length

    val phoneNumberOffsetMapping = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 0) return 0
            if (offset <= 3) return offset + 1
            if (offset <= 6) return offset + 3
            if (offset <= 10) return offset + 4
            return cursorOffset
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 1) return 0
            if (offset <= 5) return offset - 1
            if (offset <= 9) return offset - 3
            if (offset <= 14) return offset - 4
            return 10
        }
    }

    return TransformedText(
        AnnotatedString(out.toString()),
        phoneNumberOffsetMapping
    )
}