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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.fe_app_android_b2c.R
import com.example.fe_app_android_b2c.ui.components.ButtonPrimary
import com.example.fe_app_android_b2c.ui.components.ButtonPrimaryText
import com.example.fe_app_android_b2c.ui.components.TextInputField

data class FormData (
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var dob: String = ""
)

@Composable
fun BasicDetailsScreen() {
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
                text = "Just few details",
                style = MaterialTheme.typography.titleLarge,
                color = colorResource(id = R.color.secondary_dark)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier.fillMaxWidth()
            ){
                val form = remember { mutableStateOf(FormData()) }
                TextInputField(
                    text = form.value.firstName,
                    onChange = {
                        form.value = form.value.copy(firstName = it)
                    },
                    placeHolder = "First Name"
                )
                Spacer(modifier = Modifier.width(10.dp))
                TextInputField(
                    text = form.value.dob,
                    onChange = {
                        form.value = form.value.copy(dob =  it)
                    },
                    placeHolder = "Middle Name (optional)"
                )
                Spacer(modifier = Modifier.width(10.dp))
                TextInputField(
                    text = form.value.lastName,
                    onChange = {
                        form.value = form.value.copy(lastName = it)
                    },
                    placeHolder = "Last Name"
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            ButtonPrimaryText(
                label = "Register",
                onClick = { }
            )
        }
    }
}