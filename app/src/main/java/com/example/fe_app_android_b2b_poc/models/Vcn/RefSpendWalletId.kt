package com.example.fe_app_android_b2b_poc.models.Vcn

data class RefSpendWalletId(
    val available_limit: String,
    val currency: String,
    val id: String,
    val is_active: Boolean,
    val limit: String,
    val ref_company_id: RefCompanyId,
    val ref_employee_id: Any,
    val ref_frequency_id: RefFrequencyId,
    val ref_real_card_id: RefRealCardId,
    val ref_vendor_id: RefVendorId,
    val wallet_alias: String,
    val wallet_name: String
)