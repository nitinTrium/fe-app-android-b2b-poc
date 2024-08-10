package com.example.fe_app_android_b2c.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fe_app_android_b2c.models.BaseResponse
import com.example.fe_app_android_b2c.models.Login.SendLoginCodeREQ
import com.example.fe_app_android_b2c.models.Login.VerifyLoginCodeREQ
import com.example.fe_app_android_b2c.models.SendRegisterCodeRequest
import com.example.fe_app_android_b2c.repository.LoginRepository
import com.example.fe_app_android_b2c.repository.ProfileRepository
import com.example.fe_app_android_b2c.repository.RealCardRepository
import com.example.fe_app_android_b2c.repository.VcnRepository
import com.example.fe_app_android_b2c.utils.Constants
import com.example.fe_app_android_b2c.utils.NetworkResult
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val profileRepository: ProfileRepository,
    private val realCardRepository: RealCardRepository
): ViewModel() {

//    private var _loading: Boolean = true;
//    val loading: Boolean
//        get() = _loading

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private var _loggedIn: Boolean = false;
    val loggedIn: Boolean
        get() = _loggedIn

    val loginResponseLiveData: LiveData<NetworkResult<BaseResponse>>
        get() = loginRepository.responses

    private val _loginCodes: MutableState<LoginCodes> = mutableStateOf(LoginCodes("", ""))
    val loginCodes: State<LoginCodes> get() = _loginCodes

    init {
        viewModelScope.launch {
            Log.d("CODE", "ViewModel Login init")
        }
    }

    fun sendLoginCode(countryCode: String, number: String){
        viewModelScope.launch {
            val request = SendLoginCodeREQ(countryCode, number);
            val response = loginRepository.sendLoginCode(request);
            if (response != null){
                val temp: JSONObject = JSONObject(response.data.toString())
                Log.d("CODE", temp.get("Code").toString())
                val obj: LoginCodes = LoginCodes(
                    Code = temp.get("Code").toString(),
                    ReferenceId = temp.get("ReferenceId").toString()
                )
//                Code = obj.Code;
//                ReferenceId = obj.ReferenceId
                _loginCodes.value = obj;
                Log.d("CODE", loginCodes.value.toString())
            }
        }
    }

    fun verifyLoginCode(
        countryCode: String, number: String){
        viewModelScope.launch {
            Log.d("CODE", loginCodes.value.toString())
            Log.d("CODE", _loginCodes.value.toString())
//            val temp: JSONObject = JSONObject(loginResponseLiveData.value?.data?.data.toString())
//            val request = VerifyLoginCodeREQ(countryCode, number, loginCodes.value.Code,
//                _loginCodes.ReferenceId)
//            loginRepository.verifyLoginCode(request);

            delay(1000)
            profileRepository.getUserProfile();
            _loggedIn = true;

//            realCardRepository.getRealCards();
//            vcnViewModel.getVcns();
        }
    }
    data class LoginCodes (
        var Code: String,
        var ReferenceId: String
    )
}

