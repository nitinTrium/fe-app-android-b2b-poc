package com.example.fe_app_android_b2c.models.Vcn

data class VcnObj(
    val Amount: Int,
    val Card: Card,
    val Currency: String,
    val CurrentStatus: String,
    val Description: String = "",
    val GeneratedBy: String,
    val Id: String,
    val ParentConfigurations: ParentConfigurations,
    val SelfConfiguration: SelfConfiguration,
    val Timeline: Timeline,
    val Last4Digits: String
)