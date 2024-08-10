package com.example.fe_app_android_b2b_poc.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fe_app_android_b2b_poc.api.LoginAPI
import com.example.fe_app_android_b2b_poc.models.BaseResponse
import com.example.fe_app_android_b2b_poc.models.Login.LoginREQ
import com.example.fe_app_android_b2b_poc.models.Login.LoginRES
//import com.example.fe_app_android_b2b_poc.models.BaseResponse
import com.example.fe_app_android_b2b_poc.utils.Constants
//import com.example.fe_app_android_b2b_poc.utils.NetworkResult
import com.example.fe_app_android_b2b_poc.utils.TokenManager
import com.google.gson.Gson
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginAPI: LoginAPI
) {
    @Inject
    lateinit var tokenManager: TokenManager

    private val _response = MutableLiveData<LoginRES>()
    val responses: LiveData<LoginRES>
        get() = _response

    suspend fun login(loginREQ: LoginREQ){
        val response = loginAPI.login(loginREQ);
//        Log.d("TEST", "PRE " + response.body()!!.toString())
        if(response.isSuccessful && response.body() != null){
            val gson = Gson()
//            Log.d("TEST", "BEFORE " + response.body()!!.toString())
//            Log.d("TEST", "After " + gson.toJson(response.body()))
            val temp = gson.fromJson(gson.toJson(response.body()).toString(), LoginRES::class.java)

            // saving token
            tokenManager.saveAccessToken(
                temp.data.access_token
            )

            _response.postValue(temp)
        }
    }
}