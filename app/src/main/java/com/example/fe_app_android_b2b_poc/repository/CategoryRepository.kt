package com.example.fe_app_android_b2b_poc.repository

import com.example.fe_app_android_b2b_poc.api.CategoryAPI
import com.example.fe_app_android_b2b_poc.models.Category.Get.CategoryRES
import com.google.gson.Gson
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryAPI: CategoryAPI
) {
    suspend fun getCategories(): CategoryRES?{
        val response = categoryAPI.getCategories()

        if(response.isSuccessful && response.body() != null){
            val gson = Gson()
            val temp = gson.fromJson(gson.toJson(response.body()).toString(), CategoryRES::class.java)
            return temp
        }
        return null
    }
}