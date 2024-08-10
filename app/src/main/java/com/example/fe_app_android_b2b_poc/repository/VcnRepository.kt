package com.example.fe_app_android_b2b_poc.repository

import com.example.fe_app_android_b2b_poc.api.VcnAPI
import com.example.fe_app_android_b2b_poc.models.Vcn.CreateVcnREQ
import com.example.fe_app_android_b2b_poc.models.Vcn.Details.VcnDetailsRES
import com.example.fe_app_android_b2b_poc.models.Vcn.Result
import com.example.fe_app_android_b2b_poc.models.Vcn.testVCNRES
import com.google.gson.Gson
import javax.inject.Inject

class VcnRepository @Inject constructor(
    private val vcnAPI: VcnAPI
) {
    suspend fun getVcns(): List<Result>?{
        val response = vcnAPI.getVCNs()
        if(response.isSuccessful && response.body() != null){
            val gson = Gson()
            val temp = gson.fromJson(gson.toJson(response.body()).toString(), testVCNRES::class.java)
            return temp.data.results
        }
        return null
    }

    suspend fun getVcnDetails(id: String): VcnDetailsRES?{
        val response = vcnAPI.getVCNDetails(id)
        if(response.isSuccessful && response.body() != null){
            val gson = Gson()
            val temp = gson.fromJson(gson.toJson(response.body()).toString(), VcnDetailsRES::class.java)
            return temp
        }
        return null
    }

    suspend fun createVcn(createVcnREQ: CreateVcnREQ): Any{
        val response = vcnAPI.createVCN(createVcnREQ)

        return "";
    }
}