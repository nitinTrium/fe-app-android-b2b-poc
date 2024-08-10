package com.example.fe_app_android_b2b_poc.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CostAccountAPI {
    @GET("virtual-cards/identity/v1/cost-account/")
    suspend fun getCostAccounts(
        @Query("c_id") companyId: String = "df1043ac-a888-4a35-b016-8cd9232360d5",
        @Query("page") page: String = "1",
        @Query("page_size") pageSize: String = "100",
    ): Response<Any>
}