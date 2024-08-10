package com.example.fe_app_android_b2b_poc.models.Vcn

data class CreateVcnREQ(
    val amount: Int,
    val ref_spend_wallet_id: String,
    val ref_vendor_id_list: List<String>
)