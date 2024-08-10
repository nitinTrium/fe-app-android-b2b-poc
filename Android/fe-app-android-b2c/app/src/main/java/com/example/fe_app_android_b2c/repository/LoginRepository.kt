package com.example.fe_app_android_b2c.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fe_app_android_b2c.api.LoginAPI
import com.example.fe_app_android_b2c.models.BaseResponse
import com.example.fe_app_android_b2c.models.Login.SendLoginCodeREQ
import com.example.fe_app_android_b2c.models.Login.VerifyLoginCodeREQ
import com.example.fe_app_android_b2c.utils.Constants
import com.example.fe_app_android_b2c.utils.NetworkResult
import com.example.fe_app_android_b2c.utils.TokenManager
import org.json.JSONObject
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginAPI: LoginAPI
) {
    @Inject
    lateinit var tokenManager: TokenManager

    private val _response = MutableLiveData<NetworkResult<BaseResponse>>()
    val responses: LiveData<NetworkResult<BaseResponse>>
        get() = _response

    suspend fun sendLoginCode(sendLoginCodeREQ: SendLoginCodeREQ): BaseResponse?{
        val response = loginAPI.sendLoginCode(sendLoginCodeREQ);
//        Log.d(Constants.TAG, response.body().toString())
        if(response.isSuccessful && response.body() != null){
            _response.postValue(NetworkResult.Success(response.body()!!))
//            Log.d(Constants.TAG, "REPO UPDATE")
            return response.body();
        }else if(response.errorBody() != null){
            _response.postValue(NetworkResult.Error("Something went wrong"))
        }else{
            _response.postValue(NetworkResult.Error("Something went wrong"))
        }
        return null
    }

    suspend fun verifyLoginCode(verifyLoginCodeREQ: VerifyLoginCodeREQ){
        val response = loginAPI.verifyLoginUser(verifyLoginCodeREQ)
        Log.d(Constants.TAG, response.body().toString())
        if(response.isSuccessful && response.body() != null){
            // store token
            val temp = JSONObject(response.body()!!.data.toString())
            // getprofile info
            tokenManager.saveAccessToken(
                JSONObject(temp.get("tokens").toString()).get("access").toString()
            )
            tokenManager.saveRefreshToken(
                JSONObject(temp.get("tokens").toString()).get("refreshAccess").toString()
            )
            _response.postValue(NetworkResult.Success(response.body()!!))
        }
    }

}