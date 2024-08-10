package com.example.fe_app_android_b2b_poc.models.Category.Get

data class PaymentMethod(
    val id: Int,
    val is_active: Boolean,
    val payment_method: PaymentMethodX
)