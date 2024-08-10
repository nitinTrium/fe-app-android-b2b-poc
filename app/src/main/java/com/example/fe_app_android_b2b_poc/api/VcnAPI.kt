package com.example.fe_app_android_b2b_poc.api

import com.example.fe_app_android_b2b_poc.models.Login.LoginREQ
import com.example.fe_app_android_b2b_poc.models.Vcn.CreateVcnREQ
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface VcnAPI {
    @GET("virtual-cards/vc/v1/vcn/")
    suspend fun getVCNs(
        @Query ("c_id") companyId: String = "df1043ac-a888-4a35-b016-8cd9232360d5"
    ): Response<Any>

    @GET("virtual-cards/vc/v1/vcn/{id}/")
    suspend fun getVCNDetails(
        @Path("id") id: String,
        @Query ("c_id") companyId: String = "df1043ac-a888-4a35-b016-8cd9232360d5"
    ): Response<Any>

    @POST("virtual-cards/vc/v1/vcn/")
    suspend fun createVCN(
        @Body createVcnREQ: CreateVcnREQ,
        @Query ("c_id") companyId: String = "df1043ac-a888-4a35-b016-8cd9232360d5",
    ): Response<Any>
}