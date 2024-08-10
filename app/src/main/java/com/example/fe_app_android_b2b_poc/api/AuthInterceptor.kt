package com.example.fe_app_android_b2b_poc.api

import com.example.fe_app_android_b2b_poc.utils.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(): Interceptor {

    @Inject
    lateinit var tokenManager: TokenManager

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        val token = tokenManager.getAccessToken()
        request.addHeader("Authorization", "Bearer $token")

        return chain.proceed(request.build())
    }
}