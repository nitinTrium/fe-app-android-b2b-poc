package com.example.fe_app_android_b2b_poc.models.Expense.Get

data class Data(
    val count: Int,
    val links: Links,
    val results: List<Result>
)