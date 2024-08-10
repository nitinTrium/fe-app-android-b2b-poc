package com.example.fe_app_android_b2c.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fe_app_android_b2c.models.BaseResponse
import com.example.fe_app_android_b2c.models.Profile.ContactInfo
import com.example.fe_app_android_b2c.models.Profile.Mobile
import com.example.fe_app_android_b2c.models.Profile.Profile
import com.example.fe_app_android_b2c.repository.ProfileRepository
import com.example.fe_app_android_b2c.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
): ViewModel() {
    val profileResponseLiveData: LiveData<Profile>
        get() = profileRepository.apiResponse

    init {
        viewModelScope.launch {
            Log.d("LUSID", "ViewModel Profile init")
            profileRepository.getUserProfile();
//            val temp = JSONObject(profileResponseLiveData.value?.data?.data.toString()).get("Profile")
//            val tempProfile: Profile = Profile(
//                Avatar = JSONObject(temp.toString()).get("Avatar"),
//                CircleCode = JSONObject(temp.toString()).get("CircleCode").toString(),
//                ContactInfo = ContactInfo(
//                    Email = "",
//                    Mobile = Mobile(
//                        CountryCode = "+91",
//                        Number = "9166853618"
//                    ),
//                ),
//                FirstName = "Nitin",
//                MiddleName = "",
//                LastName = "Singh"
//            )
//            _profileData.setValue(tempProfile)
//            Log.d("LUSID", tempProfile.toString())
        }
    }

    fun getUserProfile(){
        viewModelScope.launch {
            profileRepository.getUserProfile();
        }
    }
}