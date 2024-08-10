package com.example.fe_app_android_b2c.repository

import android.util.Log
import com.example.fe_app_android_b2c.api.CircleAPI
import com.example.fe_app_android_b2c.models.Circle.PendingRequestsList
import com.example.fe_app_android_b2c.models.Vcn.VcnObj
import com.example.fe_app_android_b2c.utils.NetworkResult
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.json.JSONObject
import javax.inject.Inject

class CircleRepository @Inject constructor(
    private val circleAPI: CircleAPI
){
    private val _pendingReqList = MutableStateFlow<List<PendingRequestsList>>(emptyList())
    val pendingRequestsList: StateFlow<List<PendingRequestsList>>
        get() = _pendingReqList


    suspend fun getPendingRequests(){
        val response = circleAPI.getPendingRequestsCircle()

        Log.d("LUSID", response.body().toString())
//        if(response.isSuccessful && response.body() != null){
//            _response.setValue(NetworkResult.Success(response.body()!!))
//            val data = JSONObject(response.body()!!.data.toString())
//            val list: List<VcnObj> = Gson().fromJson(data.get("Records").toString(), Array<VcnObj>::class.java).toList()
////            Log.d("REAL", list.toString())
//            _vcnList.emit(list)
//        }else{
//            _response.postValue(NetworkResult.Error("Something went wrong"))
//        }

    }


}