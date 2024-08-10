package com.example.fe_app_android_b2b_poc.models.Vcn

data class Result(
    val amount: Int,
    val created_at: String,
    val currency: String,
    val deleted_at: Any,
    val is_card_on_file: Boolean,
    val last_four_digits: String,
    val max_trans: Int,
    val network_vcn_id: String,
    val ref_company_id: String,
    val ref_employee_id: String,
    val ref_network_id: Any,
    val ref_spend_wallet_id: RefSpendWalletId,
    val status: String,
    val updated_at: String,
    val vcn_id: String,
    val vendors: List<Vendor>
)