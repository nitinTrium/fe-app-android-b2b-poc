package com.example.fe_app_android_b2c.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fe_app_android_b2c.api.VCNAPI
import com.example.fe_app_android_b2c.models.BaseResponse
import com.example.fe_app_android_b2c.models.RealCard.RealCardObj
import com.example.fe_app_android_b2c.models.Vcn.CreateVcnREQ
import com.example.fe_app_android_b2c.models.Vcn.VcnObj
import com.example.fe_app_android_b2c.utils.NetworkResult
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.json.JSONObject
import javax.inject.Inject

class VcnRepository @Inject constructor(
    private val vcnapi: VCNAPI
) {
    private val _response = MutableLiveData<NetworkResult<BaseResponse>>()
    val apiResponse: LiveData<NetworkResult<BaseResponse>>
        get() = _response

    private val _vcnList = MutableStateFlow<List<VcnObj>>(emptyList())
    val vcnList: StateFlow<List<VcnObj>>
        get() = _vcnList


    suspend fun getVcns(): List<VcnObj> {
        val response = vcnapi.getVCNs();
//        Log.d("REAL", response.body().toString())
        if(response.isSuccessful && response.body() != null){
            _response.setValue(NetworkResult.Success(response.body()!!))
            val data = JSONObject(response.body()!!.data.toString())
            val list: List<VcnObj> = Gson().fromJson(data.get("Records").toString(), Array<VcnObj>::class.java).toList()
//            Log.d("REAL", list.toString())
            _vcnList.emit(list)
            return list;
        }else{
            _response.postValue(NetworkResult.Error("Something went wrong"))
        }

        return emptyList<VcnObj>()
    }

    suspend fun createVcn(createVcnREQ: CreateVcnREQ){
        val response = vcnapi.createVCNUser(createVcnREQ)
        Log.d("REAL", response.body().toString())
        if(response.isSuccessful && response.body() != null){
            _response.setValue(NetworkResult.Success(response.body()!!))
        }else{
            _response.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

}