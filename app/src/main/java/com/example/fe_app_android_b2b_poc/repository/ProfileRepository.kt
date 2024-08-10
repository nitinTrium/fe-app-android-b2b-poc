package com.example.fe_app_android_b2b_poc.repository

import android.util.Log
import com.example.fe_app_android_b2b_poc.api.ProfileAPI
import com.example.fe_app_android_b2b_poc.models.Profile.ProfileRES
import com.example.fe_app_android_b2b_poc.models.Vcn.testVCNRES
import com.google.gson.Gson
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val profileAPI: ProfileAPI
) {
    suspend fun getAssociatedCompanies(): ProfileRES?{
        val response = profileAPI.getAssociatedCompanies()
        if(response.isSuccessful && response.body() != null){
            val gson = Gson()
            val temp = gson.fromJson(gson.toJson(response.body()).toString(), ProfileRES::class.java)

            return temp
        }
        return null
    }
}