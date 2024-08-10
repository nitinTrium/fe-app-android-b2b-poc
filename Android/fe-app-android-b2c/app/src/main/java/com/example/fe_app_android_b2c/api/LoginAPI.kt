package com.example.fe_app_android_b2c.api

import com.example.fe_app_android_b2c.models.BaseResponse
import com.example.fe_app_android_b2c.models.Login.SendLoginCodeREQ
import com.example.fe_app_android_b2c.models.Login.VerifyLoginCodeREQ
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginAPI {

    @POST("identity/mobile/v1/user/send-login-code")
    suspend fun sendLoginCode(
        @Body sendLoginCodeREQ: SendLoginCodeREQ
    ): Response<BaseResponse>

    @POST("identity/mobile/v1/user/re-send-login-code")
    suspend fun resendLoginCode(

    )

    @POST("identity/mobile/v1/user/login")
    suspend fun verifyLoginUser(
        @Body verifyLoginCodeREQ: VerifyLoginCodeREQ
    ): Response<BaseResponse>

    @GET("identity/mobile/v1/user/refreshAccess")
    suspend fun refreshAccessToken(

    )
}