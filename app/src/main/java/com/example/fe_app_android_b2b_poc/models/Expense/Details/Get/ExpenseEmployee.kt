package com.example.fe_app_android_b2b_poc.models.Expense.Details.Get

data class ExpenseEmployee(
    val created_at: String,
    val deleted_at: Any,
    val emp_id: String,
    val ref_employee_id: RefEmployeeId,
    val ref_expense_id: String,
    val updated_at: String
)