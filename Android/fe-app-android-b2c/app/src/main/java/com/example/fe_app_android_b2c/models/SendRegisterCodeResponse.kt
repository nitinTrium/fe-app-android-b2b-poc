package com.example.fe_app_android_b2c.models

data class SendRegisterCodeResponse(
    val `data`: Data,
    val error: Boolean,
    val errors: List<Any>,
    val message: String
)