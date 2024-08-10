package com.example.fe_app_android_b2c.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fe_app_android_b2c.api.RealCardAPI
import com.example.fe_app_android_b2c.models.BaseResponse
import com.example.fe_app_android_b2c.models.RealCard.RealCardObj
import com.example.fe_app_android_b2c.utils.Constants
import com.example.fe_app_android_b2c.utils.NetworkResult
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.json.JSONObject
import javax.inject.Inject

class RealCardRepository @Inject constructor(
    private val realCardAPI: RealCardAPI
) {
    private val _response = MutableLiveData<NetworkResult<BaseResponse>>()
    val apiResponse: LiveData<NetworkResult<BaseResponse>>
        get() = _response

    private val _realCardsList = MutableStateFlow<List<RealCardObj>>(emptyList())
    val realCardsList: StateFlow<List<RealCardObj>>
        get() = _realCardsList

    // get real cards
    suspend fun getRealCards(){
        val response = realCardAPI.getRealCards();
//        Log.d("REAL", response.body().toString())
        if(response.isSuccessful && response.body() != null){
            _response.setValue(NetworkResult.Success(response.body()!!))
            val data = JSONObject(response.body()!!.data.toString())
            val list: List<RealCardObj> = Gson().fromJson(data.get("Records").toString(), Array<RealCardObj>::class.java).toList()
//            Log.d("REAL", list.toString())
            _realCardsList.emit(list)
        }else{
            _response.postValue(NetworkResult.Error("Something went wrong"))
        }
    }


    // add virtual cards

}