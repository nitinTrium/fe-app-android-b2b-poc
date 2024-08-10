package com.example.fe_app_android_b2b_poc.models.Category.Get

data class Result(
    val category: String,
    val category_id: String,
    val description: String,
    val is_active: Boolean,
    val payment_methods: List<PaymentMethod>
)