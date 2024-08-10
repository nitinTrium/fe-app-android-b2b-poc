package com.example.fe_app_android_b2b_poc.views.profile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fe_app_android_b2b_poc.models.Profile.ProfileRES
import com.example.fe_app_android_b2b_poc.models.Vcn.Result
import com.example.fe_app_android_b2b_poc.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
): ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _profile = MutableLiveData<ProfileRES>()
    val profile: LiveData<ProfileRES>
        get() = _profile

    init {
        getAssociatedCompanies()
    }

    // ================ API calls ================
     fun getAssociatedCompanies (){
        viewModelScope.launch {
            _isLoading.value = true
            val response = profileRepository.getAssociatedCompanies()
            if(response != null){
                _profile.value = response
            }
            _isLoading.value = false
        }
    }
}