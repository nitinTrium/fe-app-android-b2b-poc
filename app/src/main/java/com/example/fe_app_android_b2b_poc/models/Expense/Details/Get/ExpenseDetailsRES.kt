package com.example.fe_app_android_b2b_poc.models.Expense.Details.Get

data class ExpenseDetailsRES(
    val `data`: Data,
    val error: Boolean,
    val message: String,
    val status: String
)