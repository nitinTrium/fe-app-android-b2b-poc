package com.example.fe_app_android_b2b_poc.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProfileAPI {
    @GET("identity/auth/v2/associated-companies")
    suspend fun getAssociatedCompanies(

    ): Response<Any>

    @POST("identity/auth/v2/my-profile")
    suspend fun getProfile(
        @Query ("c_id") companyId: String = "df1043ac-a888-4a35-b016-8cd9232360d5"
    ): Response<Any>
}