package com.example.fe_app_android_b2c.models

data class BaseResponse(
    val data: Any,
    val error: Boolean,
    val errors: List<Any>,
    val message: String
)
