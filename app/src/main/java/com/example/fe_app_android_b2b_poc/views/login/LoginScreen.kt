package com.example.fe_app_android_b2b_poc.views.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fe_app_android_b2b_poc.R
import com.example.fe_app_android_b2b_poc.ui.components.LoadingButtonPrimary
import com.example.fe_app_android_b2b_poc.ui.components.PasswordInputField
import com.example.fe_app_android_b2b_poc.ui.components.TextInputField

@Composable
fun LoginScreen (
    handleNav: () -> Unit
){
    val state: LoginViewModel = hiltViewModel()
    val formData = state.formData.observeAsState()

    when(state.isLoggedIn.value){
        true -> handleNav()
        false -> Unit
        else -> Unit
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column(
            modifier = Modifier
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .padding(20.dp, bottom = 40.dp)
                    .width(150.dp),
            )

            Text(
                text = "Login into your account",
                modifier = Modifier,
                style = MaterialTheme.typography.titleLarge,
            )

            TextInputField(
                modifier = Modifier
                    .fillMaxWidth(),
                readOnly = state.isLoading.value,
                text = formData.value!!["userName"].toString(),
                onChange = {
                    state.onValueChanged("userName", it)
                },
                placeHolder = "Email"
            )

            PasswordInputField(
                modifier = Modifier
                    .fillMaxWidth(),
                readOnly = state.isLoading.value,
                text = formData.value!!["password"].toString(),
                onChange = {
                    state.onValueChanged("password", it)
                },
                placeHolder = "Password"
            )

            LoadingButtonPrimary(
                onClick = { state.handleLogin() },
                loading = state.isLoading.value,
                label = "Login"
            )
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "By signing up you agree to our Privacy Policy and Terms & Conditions.",
                color = Color.Gray,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
                )
        }
    }
}

