package com.example.fe_app_android_b2c.viewModel

import com.example.fe_app_android_b2c.models.Circle.PendingRequestsList
import com.example.fe_app_android_b2c.models.RealCard.RealCardObj
import com.example.fe_app_android_b2c.repository.CircleRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class CircleViewModel @Inject constructor(
    private val circleRepository: CircleRepository
) {

    val pendingRequestsList: StateFlow<List<PendingRequestsList>>
        get() = circleRepository.pendingRequestsList


}