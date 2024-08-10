package com.example.fe_app_android_b2b_poc.models.Vcn

import com.example.fe_app_android_b2b_poc.models.Login.LoginRESData
import com.example.fe_app_android_b2b_poc.models.Login.LoginRESDataUserInfo
import com.google.gson.annotations.SerializedName

data class GetVcnRES (
    @SerializedName("data") val data: Any,
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: String
)
