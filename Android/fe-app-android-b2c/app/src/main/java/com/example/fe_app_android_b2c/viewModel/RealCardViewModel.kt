package com.example.fe_app_android_b2c.viewModel

import android.util.Log
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fe_app_android_b2c.models.BaseResponse
import com.example.fe_app_android_b2c.models.RealCard.RealCardObj
import com.example.fe_app_android_b2c.repository.RealCardRepository
import com.example.fe_app_android_b2c.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RealCardViewModel @Inject constructor(
    private val realCardRepository: RealCardRepository
): ViewModel() {
    val realCardResponseLiveData: LiveData<NetworkResult<BaseResponse>>
        get() = realCardRepository.apiResponse

    val realCardList: StateFlow<List<RealCardObj>>
        get() = realCardRepository.realCardsList

    init {
        viewModelScope.launch {
            Log.d("LUSID", "ViewModel real card init")
            realCardRepository.getRealCards();
        }
    }
}