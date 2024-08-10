package com.example.fe_app_android_b2c.di

import com.example.fe_app_android_b2c.api.AuthInterceptor
import com.example.fe_app_android_b2c.api.CircleAPI
import com.example.fe_app_android_b2c.api.LoginAPI
import com.example.fe_app_android_b2c.api.ProfileAPI
import com.example.fe_app_android_b2c.api.RealCardAPI
import com.example.fe_app_android_b2c.api.RegisterAPI
import com.example.fe_app_android_b2c.api.VCNAPI
import com.example.fe_app_android_b2c.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    //RETROFIT
    @Singleton
    @Provides
    fun providesRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
    }

    // --------------- INTERCEPTOR --------------------
    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }


    // --------------- API - INTERFACES ----------------
    //REGISTER API
    @Singleton
    @Provides
    fun providesRegisterAPI(retrofitBuilder: Retrofit.Builder): RegisterAPI{
        return retrofitBuilder.build().create(RegisterAPI::class.java)
    }

    //LOGIN API
    @Singleton
    @Provides
    fun providesLoginAPI(retrofitBuilder: Retrofit.Builder): LoginAPI{
        return retrofitBuilder.build().create(LoginAPI::class.java)
    }

    //PROFILE API
    @Singleton
    @Provides
    fun providesProfileAPI(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): ProfileAPI{
        return retrofitBuilder.client(okHttpClient).build().create(ProfileAPI::class.java)
    }

    //REAL CARD API
    @Singleton
    @Provides
    fun providesRealCardAPI(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): RealCardAPI{
        return retrofitBuilder.client(okHttpClient).build().create(RealCardAPI::class.java)
    }

    //VCN API
    @Singleton
    @Provides
    fun providesVcnAPI(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): VCNAPI{
        return retrofitBuilder.client(okHttpClient).build().create(VCNAPI::class.java)
    }

    //CIRCLE API
    @Singleton
    @Provides
    fun providesCircleAPI(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): CircleAPI{
        return retrofitBuilder.client(okHttpClient).build().create(CircleAPI::class.java)
    }
}