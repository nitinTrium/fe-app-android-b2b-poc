package com.example.fe_app_android_b2b_poc.models.Expense.Post

data class CreateExpenseREQ(
    val name: String,
    val description: String,
    val amount: String,
    val ref_category_id: String,
    val ref_cost_account_id: String,
    val ref_payment_method_id: Int,
    val is_reimbursement: Boolean,
    val employees: List<Any>?,
    val attachment_id_list: List<Any>?,
)