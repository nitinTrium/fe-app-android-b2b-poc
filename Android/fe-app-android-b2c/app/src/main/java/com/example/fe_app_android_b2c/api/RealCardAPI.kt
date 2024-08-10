package com.example.fe_app_android_b2c.api

import com.example.fe_app_android_b2c.models.BaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface RealCardAPI {

//    @GET("http://10.0.2.2:8082/b2c/payments/mobile/v1/real-cards?page=1&limit=10")
    @GET("http://192.168.1.4:8082/b2c/payments/mobile/v1/real-cards?page=1&limit=10")
    suspend fun getRealCards(

    ): Response<BaseResponse>

    @POST("http://localhost:8082/apps/payments/mobile/v1/real-cards")
    suspend fun addRealCard(

    )

    @PATCH("http://localhost:8082/apps/payments/mobile/v1/real-cards/activate-deactivate/:realCardId")
    suspend fun toggleRealCard(

    )

}
