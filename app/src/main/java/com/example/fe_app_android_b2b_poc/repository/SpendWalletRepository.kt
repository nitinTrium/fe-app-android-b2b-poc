package com.example.fe_app_android_b2b_poc.repository

import com.example.fe_app_android_b2b_poc.api.SpendWalletAPI
import com.example.fe_app_android_b2b_poc.models.SpendWallet.Get.SpendWalletRES
import com.google.gson.Gson
import javax.inject.Inject

class SpendWalletRepository @Inject constructor(
    private val spendWalletAPI: SpendWalletAPI
) {
    suspend fun getSpendWallet(): SpendWalletRES?{
        val response = spendWalletAPI.getSpendWallets()
        if(response.isSuccessful && response.body() != null){
            val gson = Gson()
            val temp = gson.fromJson(gson.toJson(response.body()).toString(), SpendWalletRES::class.java)
            return temp
        }
        return null
    }
}