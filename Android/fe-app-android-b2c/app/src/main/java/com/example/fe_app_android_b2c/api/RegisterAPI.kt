package com.example.fe_app_android_b2c.api

import com.example.fe_app_android_b2c.models.SendRegisterCodeRequest
import com.example.fe_app_android_b2c.models.SendRegisterCodeResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterAPI {
    // this is just interface not implementation
    // send registration code
    // resend registration code
    // register


    // suspend - is async for kotlin, which is also known as coroutines
    // use need annotation in kotlin for almost all things

    @POST("identity/mobile/v1/user/send-registration-code")
    suspend fun sendRegisterCode(
        @Body sendRegisterCodeRequest: SendRegisterCodeRequest
    ) : Response<SendRegisterCodeResponse>

    @POST("identity/mobile/v1/user/re-send-registration-code")
    suspend fun resendRegisterCode(

    )

    @POST("identity/mobile/v1/user/register")
    suspend fun registerUser(

    )

}