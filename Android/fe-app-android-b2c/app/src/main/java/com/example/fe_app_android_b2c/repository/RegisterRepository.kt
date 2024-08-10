package com.example.fe_app_android_b2c.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fe_app_android_b2c.api.RegisterAPI
import com.example.fe_app_android_b2c.models.SendRegisterCodeRequest
import com.example.fe_app_android_b2c.models.SendRegisterCodeResponse
import com.example.fe_app_android_b2c.utils.Constants.TAG
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class RegisterRepository @Inject constructor(
    private val registerAPI: RegisterAPI
){
//    private val _response = MutableStateFlow<SendRegisterCodeResponse>(null!!)
    private val _response = MutableLiveData<SendRegisterCodeResponse>()

    // this is private to keep safe
    val responses: LiveData<SendRegisterCodeResponse> // this is exposed, which is read-only
        get() = _response

    // functions for register API

    suspend fun sendRegisterCode(sendRegisterCodeRequest: SendRegisterCodeRequest){
//        Log.d("LUSID", sendRegisterCodeRequest.toString())
        val response = registerAPI.sendRegisterCode(sendRegisterCodeRequest);
        Log.d(TAG, response.body().toString())
        if(response.isSuccessful && response.body() != null){
            _response.postValue(response.body()!!)
        }
    }

//    suspend fun resendRegisterCode()
}