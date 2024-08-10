package com.example.fe_app_android_b2c.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fe_app_android_b2c.models.Login.VerifyLoginCodeREQ
import com.example.fe_app_android_b2c.models.RealCard.RealCardObj
import com.example.fe_app_android_b2c.models.Vcn.CreateVcnREQ
import com.example.fe_app_android_b2c.models.Vcn.VcnObj
import com.example.fe_app_android_b2c.repository.VcnRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VcnViewModel @Inject constructor(
    private val vcnRepository: VcnRepository
): ViewModel() {
//    val realCardList: StateFlow<List<VcnObj>>
//        get() = vcnRepository.vcnList

    private val _vcnList = MutableStateFlow<List<VcnObj>>(emptyList())
    val vcnList: StateFlow<List<VcnObj>>
        get() = _vcnList


    init {
        viewModelScope.launch{
            Log.d("VCN", "ViewModel VCN init")
            val list = vcnRepository.getVcns();
            _vcnList.value = list
        }
    }

    fun getVcns(){
        viewModelScope.launch {
            val list = vcnRepository.getVcns();
            _vcnList.value = list
        }
    }

    fun createVcn(amount: String, description: String){
        viewModelScope.launch {
            val request = CreateVcnREQ(
                Amount = amount.toInt(),
                Description = description
            )
            vcnRepository.createVcn(request)
            vcnRepository.getVcns()
        }
    }
}