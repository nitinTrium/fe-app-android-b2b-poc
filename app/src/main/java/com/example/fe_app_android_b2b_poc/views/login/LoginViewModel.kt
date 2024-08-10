package com.example.fe_app_android_b2b_poc.views.login

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fe_app_android_b2b_poc.models.Login.LoginREQ
import com.example.fe_app_android_b2b_poc.models.Login.LoginRES
import com.example.fe_app_android_b2b_poc.repository.LoginRepository
//import com.example.fe_app_android_b2b_poc.repository.ProfileRepository
//import com.example.fe_app_android_b2b_poc.repository.RealCardRepository
//import com.example.fe_app_android_b2b_poc.repository.VcnRepository
//import com.example.fe_app_android_b2b_poc.utils.Constants
//import com.example.fe_app_android_b2b_poc.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

var FormDataSet = mapOf(
    "userName" to "",
    "password" to ""
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
): ViewModel() {

    val formData = MutableLiveData(FormDataSet)

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _isLoggedIn: MutableState<Boolean> = mutableStateOf(false)
    val isLoggedIn: State<Boolean> get() = _isLoggedIn

    val loginResponseLiveData: LiveData<LoginRES>
        get() = loginRepository.responses

    init {
        viewModelScope.launch {
            Log.d("CODE", "ViewModel Login init")
        }
    }


    // ---------------- onChange ----------------
    fun onValueChanged(name: String, value: String) {
        val temp = formData.value?.toMutableMap()
        temp?.set(name, value)
        formData.value = temp
    }

    // ================ API calls ================
    fun handleLogin() {
        viewModelScope.launch {
            _isLoading.value = true;
            _isLoggedIn.value = false;
            val request = LoginREQ(
                formData.value?.get("userName") ?: "",
                formData.value?.get("password") ?: "");
            val response = loginRepository.login(request);
            _isLoggedIn.value = true;
            _isLoading.value = false;

            // reset the state
            formData.value = FormDataSet
        }
    }

}