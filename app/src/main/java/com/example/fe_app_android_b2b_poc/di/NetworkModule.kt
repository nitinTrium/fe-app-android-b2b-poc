package com.example.fe_app_android_b2b_poc.di

import com.example.fe_app_android_b2b_poc.api.AuthInterceptor
import com.example.fe_app_android_b2b_poc.api.CategoryAPI
import com.example.fe_app_android_b2b_poc.api.CostAccountAPI
import com.example.fe_app_android_b2b_poc.api.ExpenseAPI
import com.example.fe_app_android_b2b_poc.api.LoginAPI
import com.example.fe_app_android_b2b_poc.api.ProfileAPI
import com.example.fe_app_android_b2b_poc.api.ResourceAPI
import com.example.fe_app_android_b2b_poc.api.SpendWalletAPI
import com.example.fe_app_android_b2b_poc.api.VcnAPI
import com.example.fe_app_android_b2b_poc.utils.Constants.BASE_URL
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
    //LOGIN API
    @Singleton
    @Provides
    fun providesLoginAPI(retrofitBuilder: Retrofit.Builder): LoginAPI{
        return retrofitBuilder.build().create(LoginAPI::class.java)
    }

    //VCN API
    @Singleton
    @Provides
    fun providesVCNAPI(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): VcnAPI{
        return retrofitBuilder.client(okHttpClient).build().create(VcnAPI::class.java)
    }

    //PROFILE API
    @Singleton
    @Provides
    fun providesProfileAPI(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): ProfileAPI {
        return retrofitBuilder.client(okHttpClient).build().create(ProfileAPI::class.java)
    }

    //SPEND WALLET API
    @Singleton
    @Provides
    fun providesSpendWalletAPI(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): SpendWalletAPI {
        return retrofitBuilder.client(okHttpClient).build().create(SpendWalletAPI::class.java)
    }

    //EXPENSE API
    @Singleton
    @Provides
    fun providesExpenseAPI(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): ExpenseAPI {
        return retrofitBuilder.client(okHttpClient).build().create(ExpenseAPI::class.java)
    }

    //CATEGORY API
    @Singleton
    @Provides
    fun providesCategoryAPI(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): CategoryAPI {
        return retrofitBuilder.client(okHttpClient).build().create(CategoryAPI::class.java)
    }

    //COST ACCOUNT API
    @Singleton
    @Provides
    fun providesCostAccountAPI(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): CostAccountAPI {
        return retrofitBuilder.client(okHttpClient).build().create(CostAccountAPI::class.java)
    }

    //RESOURCE API
    @Singleton
    @Provides
    fun providesResourceAPI(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): ResourceAPI {
        return retrofitBuilder.client(okHttpClient).build().create(ResourceAPI::class.java)
    }
//
//
//    //REAL CARD API
//    @Singleton
//    @Provides
//    fun providesRealCardAPI(
//        retrofitBuilder: Retrofit.Builder,
//        okHttpClient: OkHttpClient
//    ): RealCardAPI{
//        return retrofitBuilder.client(okHttpClient).build().create(RealCardAPI::class.java)
//    }
//
//    //VCN API
//    @Singleton
//    @Provides
//    fun providesVcnAPI(
//        retrofitBuilder: Retrofit.Builder,
//        okHttpClient: OkHttpClient
//    ): VCNAPI{
//        return retrofitBuilder.client(okHttpClient).build().create(VCNAPI::class.java)
//    }
//
//    //CIRCLE API
//    @Singleton
//    @Provides
//    fun providesCircleAPI(
//        retrofitBuilder: Retrofit.Builder,
//        okHttpClient: OkHttpClient
//    ): CircleAPI{
//        return retrofitBuilder.client(okHttpClient).build().create(CircleAPI::class.java)
//    }
}