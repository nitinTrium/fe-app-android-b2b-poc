package com.example.fe_app_android_b2c.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fe_app_android_b2c.api.ProfileAPI
import com.example.fe_app_android_b2c.models.BaseResponse
import com.example.fe_app_android_b2c.models.Login.VerifyLoginCodeREQ
import com.example.fe_app_android_b2c.models.Profile.Profile
import com.example.fe_app_android_b2c.models.RealCard.RealCardObj
import com.example.fe_app_android_b2c.utils.Constants
import com.example.fe_app_android_b2c.utils.NetworkResult
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.json.JSONObject
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val profileAPI: ProfileAPI
) {
    private val _response = MutableLiveData<Profile>()
    val apiResponse: LiveData<Profile>
        get() = _response

    suspend fun getUserProfile(){
        val response = profileAPI.getProfile();
        Log.d(Constants.TAG, response.body().toString())
        if(response.isSuccessful && response.body() != null){
//            _response.setValue(NetworkResult.Success(response.body()!!))
            val data = JSONObject(response.body()!!.data.toString())
            val obj: Profile = Gson().fromJson(data.get("Profile").toString(), Profile::class.java)
//            Log.d("REAL", obj.toString())
            _response.postValue(obj)
        }else{
//            _response.postValue(NetworkResult.Error("Something went wrong"))
        }
    }
}