package com.example.fe_app_android_b2b_poc.api

import com.example.fe_app_android_b2b_poc.models.BaseResponse
import com.example.fe_app_android_b2b_poc.models.Login.LoginREQ
import com.example.fe_app_android_b2b_poc.models.Login.LoginRES
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {

    @POST("identity/auth/v2/login")
    suspend fun login(
        @Body loginREQ: LoginREQ
    ): Response<Any>

}