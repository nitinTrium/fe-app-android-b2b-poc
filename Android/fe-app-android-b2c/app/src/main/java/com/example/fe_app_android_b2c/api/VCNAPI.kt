package com.example.fe_app_android_b2c.api

import com.example.fe_app_android_b2c.models.BaseResponse
import com.example.fe_app_android_b2c.models.Login.SendLoginCodeREQ
import com.example.fe_app_android_b2c.models.Vcn.CreateVcnREQ
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface VCNAPI {


//    @GET("http://10.0.2.2:8082/b2c/payments/mobile/v1/virtual-cards/all?page=1&limit=10")
    @GET("http://192.168.1.4:8082/b2c/payments/mobile/v1/virtual-cards/all?page=1&limit=10")
    suspend fun getVCNs(

    ): Response<BaseResponse>

    @GET("http://localhost:8082/apps/payments/mobile/v1/virtual-cards/:vcnId")
    suspend fun getVCNDetails(

    )

    @POST("http://10.0.2.2:8082/b2c/payments/mobile/v1/virtual-cards/real-card/f6f69a56-76c8-4a3c-a67d-98240bc3e778")
    suspend fun createVCNUser(
        @Body createVcnREQ: CreateVcnREQ
    ): Response<BaseResponse>

    @POST("http://localhost:8082/apps/payments/mobile/v1/virtual-cards/wallet/:walletId")
    suspend fun createVCNMember(

    )

    @DELETE("http://localhost:8082/apps/payments/mobile/v1/virtual-cards/:vcnId")
    suspend fun deleteVCN(

    )

    @POST("http://localhost:8000/apps/identity/mobile/v1/user/otp/virtual-card/generation-code/send")
    suspend fun sendVCNCode(

    )

    @POST("http://localhost:8000/apps/identity/mobile/v1/user/otp/virtual-card/generation-code/resend")
    suspend fun resendVCNCode(

    )
}