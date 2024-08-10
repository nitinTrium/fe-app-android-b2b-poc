package com.example.fe_app_android_b2c.api

import com.example.fe_app_android_b2c.models.BaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH

interface ProfileAPI {

    @GET("identity/mobile/v1/user/profile")
    suspend fun getProfile(): Response<BaseResponse>

    @PATCH("identity/mobile/v1/user/profile/update-names")
    suspend fun updateUserName(

    )

}