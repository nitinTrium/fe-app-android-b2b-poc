package com.example.fe_app_android_b2b_poc.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    variant: String = "",
    label: String = "",
){

    // variants
    // 1. info
    // 2. success
    // 3. error
    // 4. warning

    Text(
        text = label,
        modifier = modifier
            .background(
                color = when(variant){
                    "info" -> Color("#dce6f1".toColorInt())
                    "success" -> Color("#d7ebce".toColorInt())
                    "warning" -> Color("#fce2ba".toColorInt())
                    "error" -> Color("#fadfd8".toColorInt())
                    else -> Color("#f1f1f1".toColorInt())
                },
                shape = RoundedCornerShape(25.dp)
            )
            .padding(15.dp, 10.dp),
        style = MaterialTheme.typography.labelMedium,
        color =  when(variant){
            "info" -> Color("#004182".toColorInt())
            "success" -> Color("#44712e".toColorInt())
            "warning" -> Color("#915907".toColorInt())
            "error" -> Color("#b24020".toColorInt())
            else -> Color("#000000".toColorInt())
        },
        fontWeight = FontWeight.Bold
    )
}