package com.example.fe_app_android_b2c.api

import retrofit2.http.GET

interface MiscellaneousAPI {

    @GET("http://localhost:8082/apps/payments/mobile/v1/currency?page=1&limit=10")
    suspend fun getCurrencies(

    )
}