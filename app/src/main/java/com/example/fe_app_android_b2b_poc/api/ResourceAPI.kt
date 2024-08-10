package com.example.fe_app_android_b2b_poc.api

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ResourceAPI {
    @Multipart
    @POST("identity/upload-files/v1")
    suspend fun uploadFiles(
        @Part form: MultipartBody.Part
    ): Response<Any>
}