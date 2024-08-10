package com.example.fe_app_android_b2b_poc.repository

import com.example.fe_app_android_b2b_poc.api.CategoryAPI
import com.example.fe_app_android_b2b_poc.api.CostAccountAPI
import com.example.fe_app_android_b2b_poc.models.Category.Get.CategoryRES
import com.example.fe_app_android_b2b_poc.models.CostAccount.Get.CostAccountRES
import com.google.gson.Gson
import javax.inject.Inject

class CostAccountRepository @Inject constructor(
    private val costAccountAPI: CostAccountAPI
) {
    suspend fun getCostAccounts(): CostAccountRES?{
        val response = costAccountAPI.getCostAccounts()

        if(response.isSuccessful && response.body() != null){
            val gson = Gson()
            val temp = gson.fromJson(gson.toJson(response.body()).toString(), CostAccountRES::class.java)
            return temp
        }
        return null
    }
}