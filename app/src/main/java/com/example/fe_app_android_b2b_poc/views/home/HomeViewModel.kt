package com.example.fe_app_android_b2b_poc.views.home

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fe_app_android_b2b_poc.repository.ProfileRepository
import com.example.fe_app_android_b2b_poc.views.profile.ProfileViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

): ViewModel(){


    init {
        viewModelScope.launch {

        }
    }
}