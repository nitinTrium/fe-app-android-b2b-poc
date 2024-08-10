package com.example.fe_app_android_b2b_poc.ui.components.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun VendorAvatar(
    label: String = "",
    color: String,
) {
    fun getFirstCapitalLetters(input: String): String {
        val words = input.split(" ").filter { it.isNotEmpty() }
        return words.take(2).joinToString("") { it.firstOrNull()?.uppercase() ?: "" }
    }

    Row (
        modifier = Modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ){
        Box (
            modifier = Modifier
                .padding(10.dp)
                .width(40.dp)
                .height(40.dp)
                .background(
                    color = Color(android.graphics.Color.parseColor(color)),
                    shape = RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = getFirstCapitalLetters(label),
                style = MaterialTheme.typography.titleMedium
            )
        }
        Text(text = label)
    }
}