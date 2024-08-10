package com.example.fe_app_android_b2b_poc.repository

import com.example.fe_app_android_b2b_poc.api.CategoryAPI
import com.example.fe_app_android_b2b_poc.api.ResourceAPI
import com.example.fe_app_android_b2b_poc.models.Expense.Post.CreateExpenseREQ
import com.example.fe_app_android_b2b_poc.models.Resource.Post.ResourceUploadRES
import com.google.gson.Gson
import okhttp3.MultipartBody
import javax.inject.Inject

class ResourceRepository @Inject constructor(
    private val resourceAPI: ResourceAPI
) {
    suspend fun uploadFiles(form: MultipartBody.Part): ResourceUploadRES?{
        val response = resourceAPI.uploadFiles(form);

       if(response.isSuccessful && response.body() != null){
            val gson = Gson()
            val temp = gson.fromJson(gson.toJson(response.body()).toString(), ResourceUploadRES::class.java)
            return temp
        }

        return null;
    }
}