package com.example.fe_app_android_b2c.views.Registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.fe_app_android_b2c.R
import com.example.fe_app_android_b2c.ui.components.ButtonPrimary
import com.example.fe_app_android_b2c.ui.components.ButtonPrimaryText
import com.example.fe_app_android_b2c.ui.components.NumberInputField

@Composable
fun VerifyCodeScreen(
    mobile: String,
    onClick: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .padding(all = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column (
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Enter verification code",
                style = MaterialTheme.typography.titleLarge,
                color = colorResource(id = R.color.secondary_dark)
            )
            Text(
                text = "sent on $mobile",
                style = MaterialTheme.typography.titleSmall,
                color = colorResource(id = R.color.secondary_dark)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(){
                val code = remember {
                    mutableStateOf("");
                }
                val pattern = remember { Regex("^\\d+\$") }
                NumberInputField(
                    text = code.value,
                    onChange= { if (it.isEmpty() || it.matches(pattern)) { code.value = it }}
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            ButtonPrimaryText(
                label = "Register",
                onClick = { onClick() }
            )
        }
    }
}