package com.example.fe_app_android_b2b_poc.models.Expense.Get

data class Result(
    val amount: Int,
    val created_at: String,
    val description: String,
    val expense_id: String,
    val is_reimbursement: Boolean,
    val name: String,
    val paid_id_external: Any,
    val paid_via: Any,
    val ref_category_id: RefCategoryId,
    val ref_company_id: String,
    val ref_cost_account_id: RefCostAccountId,
    val ref_employee_id: RefEmployeeId,
    val ref_payment_method_id: RefPaymentMethodId,
    val ref_vcn_id: RefVcnId,
    val reimbursement_amount: Int,
    val status: String,
    val updated_at: String
)