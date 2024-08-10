package com.example.fe_app_android_b2c.views.Login

import android.util.Log
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fe_app_android_b2c.R
import com.example.fe_app_android_b2c.ui.components.ButtonPrimaryText
import com.example.fe_app_android_b2c.ui.components.NumberInputField
import com.example.fe_app_android_b2c.viewModel.LoginViewModel

@Composable
fun VerifyCodeScreen(
    mobile: String,
    onClick: () -> Unit,
//    state: LoginViewModel
) {
    val state = viewModel(modelClass = LoginViewModel::class.java)
//    val state: LoginViewModel by viewModels()

    state.loginResponseLiveData.observeAsState().let {
        Log.d("LUSID", it.value?.data.toString())
    }

    when(state.loggedIn){
        true -> onClick()
        false -> Unit
        else -> Unit
    }

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
            val code = remember {
                mutableStateOf("");
            }
            Row(){
                val pattern = remember { Regex("^\\d+\$") }
                NumberInputField(
                    text = code.value,
                    onChange= { if (it.isEmpty() || it.matches(pattern)) { code.value = it }}
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            ButtonPrimaryText(
                label = "Register",
                onClick = {
//                    state.loginResponseLiveData.value?.data?.data?.let {
                        state.verifyLoginCode(
                            "91",
                            mobile
                        )
//                    }
//                    onClick()
                }
            )
        }
    }
}