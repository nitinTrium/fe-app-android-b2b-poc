package com.example.fe_app_android_b2b_poc.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fe_app_android_b2b_poc.R

@Composable
fun ButtonPrimary(
    label: String,
    enabled: Boolean = true,
    onClick: () -> Unit
){
    Button(
        onClick = { onClick() },
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.primary)
        )
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.primary_light)
        )
    }
}

@Composable
fun LoadingButtonPrimary(
    label: String,
    enabled: Boolean = true,
    loading: Boolean = false,
    onClick: () -> Unit
){
    Button(
        onClick = { onClick() },
        enabled = !loading && enabled,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.primary)
        )
    ) {
        if (loading){
            CircularProgressIndicator(
                modifier = Modifier.size(28.dp),
                strokeCap = StrokeCap.Round,
                strokeWidth = 4.dp,
                color = colorResource(id = R.color.primary)
            )
        }else{
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = if(enabled) colorResource(id = R.color.primary_light)
                    else colorResource(id = R.color.light_grey)
            )
        }
    }
}