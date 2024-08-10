package com.example.fe_app_android_b2c.views.Login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fe_app_android_b2c.R
import com.example.fe_app_android_b2c.ui.components.ButtonPrimary
import com.example.fe_app_android_b2c.ui.components.ButtonPrimaryText
import com.example.fe_app_android_b2c.ui.components.NumberInputField
import com.example.fe_app_android_b2c.viewModel.LoginViewModel
import com.example.fe_app_android_b2c.viewModel.RegisterViewModel

@Composable
fun MobileNumberScreen(
    onClick: (mobile: String) -> Unit,
//    state: LoginViewModel
) {
    val state: LoginViewModel = hiltViewModel()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .padding(all = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column {
            Text(
                text = "Enter Mobile Number",
                style = MaterialTheme.typography.titleLarge,
                color = colorResource(id = R.color.secondary_dark)
            )

            val text = remember {
                mutableStateOf("")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ){
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(56.dp)
                        .clip(shape = RoundedCornerShape(12.dp))
                        .background(colorResource(id = R.color.primary_light)),

                )
                Spacer(modifier = Modifier.width(10.dp))
                val pattern = remember { Regex("^\\d+\$") }
                NumberInputField(
                    text = text.value,
                    onChange= { if (it.isEmpty() || it.matches(pattern)) { text.value = it }}
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            ButtonPrimaryText(
                label = "Continue",
                onClick = {
                    state.sendLoginCode(
                        countryCode = "91",
                        number = text.value
                    )
                    onClick(text.value)
                }
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "By continuing, you agree to our",
                style = MaterialTheme.typography.labelSmall,
                color = colorResource(id = R.color.gray_text)
            )
            Text(
                text = "Terms of Service Privacy Policy",
                style = MaterialTheme.typography.labelSmall,
                color = colorResource(id = R.color.gray_text)
            )
        }

    }
}