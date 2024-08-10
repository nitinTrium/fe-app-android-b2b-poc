package com.example.fe_app_android_b2c.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fe_app_android_b2c.models.SendRegisterCodeRequest
import com.example.fe_app_android_b2c.models.SendRegisterCodeResponse
import com.example.fe_app_android_b2c.repository.RegisterRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val resposity: RegisterRepository
) : ViewModel() {

//    val register: StateFlow<SendRegisterCodeResponse>

    init {
        viewModelScope.launch {
//            resposity.sendRegisterCode()
            Log.d("LUSID", "ViewModel register init")
        }
    }

    fun sendRegistrationCode(countryCode: String, number: String){
        viewModelScope.launch {
            val request = SendRegisterCodeRequest(countryCode, number);
            Log.d("LUSID", countryCode)
            Log.d("LUSID", number)
            resposity.sendRegisterCode(request);
        }
    }
}