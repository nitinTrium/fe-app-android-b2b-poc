package com.example.fe_app_android_b2b_poc.views.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fe_app_android_b2b_poc.R

@Composable
fun SplashScreen (
    navigateToHomeScreen: () -> Unit
){


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ){
        Image(
            modifier = Modifier
                .size(100.dp),
            painter = painterResource(id = getLogo()),
            contentDescription = "logo")
    }
}

@Composable
fun getLogo(): Int{
    return if(isSystemInDarkTheme()){
        R.drawable.ic_logo_dark
    }else{
        R.drawable.ic_logo_light
    }
}