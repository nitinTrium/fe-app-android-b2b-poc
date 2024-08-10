package com.example.fe_app_android_b2c.models.Vcn

data class CreateVcnREQ(
    val Amount: Int,
    val Curreny: String = "USD",
    val Description: String,
    val Bearer: String = "McD",
    val Expiry: String = "2024-12-22T18: 30: 00.000Z"
)