package com.example.fe_app_android_b2c.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.fe_app_android_b2c.R

@Composable
fun ButtonPrimaryText(
    label: String,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.secondary_dark)
        )
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(id = R.color.primary_light)
        )
    }
}

@Composable
fun ButtonPrimary(
    content: @Composable () -> Unit,
    onClick: () -> Unit
){

    Button(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.secondary_dark)
        )
    ) {
        content()
    }
}


@Composable
fun ButtonSecondary() {

}