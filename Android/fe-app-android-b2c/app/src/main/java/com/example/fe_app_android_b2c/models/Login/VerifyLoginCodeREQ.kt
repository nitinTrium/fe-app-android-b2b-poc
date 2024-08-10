package com.example.fe_app_android_b2c.models.Login

data class VerifyLoginCodeREQ (
    val MobileCountryCode: String,
    val MobileNumber: String,
    val Code: String,
    val ReferenceId: String
)