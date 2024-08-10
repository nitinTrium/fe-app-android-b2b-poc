package com.example.fe_app_android_b2b_poc.ui.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.fe_app_android_b2b_poc.R

@Composable
fun PageLoading() {
    CircularProgressIndicator(
        strokeCap = StrokeCap.Round,
        strokeWidth = 4.dp,
        color = colorResource(id = R.color.primary)
    )
}