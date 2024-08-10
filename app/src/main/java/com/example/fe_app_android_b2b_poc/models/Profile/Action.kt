package com.example.fe_app_android_b2b_poc.models.Profile

data class Action(
    val Company_Employees: List<CompanyEmployee>,
    val FirstName: String,
    val IsPrimaryEmailVerified: Boolean,
    val IsPrimaryMobileVerified: Boolean,
    val LastName: String,
    val PrimaryEmail: String,
    val PrimaryMobile: String
)