package com.example.fe_app_android_b2c.api

import com.example.fe_app_android_b2c.models.BaseResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface CircleAPI {

    @GET("http://localhost:8000/b2c/identity/mobile/v1/user/find")
    suspend fun searchUserCircle(

    )

    @POST("http://localhost:8000/b2c/identity/mobile/v1/user/circle/invite-user")
    suspend fun inviteUserCircle(

    )

    @POST("http://localhost:8000/b2c/identity/mobile/v1/user/circle/new-request")
    suspend fun newRequestUserCircle(

    )

    @GET("http://localhost:8000/b2c/identity/mobile/v1/user/circle/pending-requests")
    suspend fun getPendingRequestsCircle(

    ): Response<BaseResponse>

    @POST("http://localhost:8000/b2c/identity/mobile/v1/user/circle/add-circle-member")
    suspend fun addUserToCircle(

    )

    @GET("http://localhost:8000/b2c/identity/mobile/v1/user/circle")
    suspend fun getCircleDetails(

    )

    @DELETE("http://localhost:8000/b2c/identity/mobile/v1/user/circle/remove-circle-member")
    suspend fun removeUserCircle(

    )
}