package com.example.fe_app_android_b2b_poc.models.Login

import com.google.gson.annotations.SerializedName

data class LoginRES(
    @SerializedName("data") val data: LoginRESData,
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: String
)

data class LoginRESData(
    @SerializedName("access_token") val access_token: String,
    @SerializedName("refresh_token") val refresh_token: String,
    @SerializedName("user_info") val user_info: LoginRESDataUserInfo
)

data class LoginRESDataUserInfo(
    @SerializedName("FirstName") val FirstName: String,
    @SerializedName("LastName") val LastName: String,
    @SerializedName("Avatar") val Avatar: String
)