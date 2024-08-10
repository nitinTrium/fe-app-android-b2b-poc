package com.example.fe_app_android_b2b_poc.models.Profile

data class CompanyEmployee(
    val Company: Company,
    val Department: String,
    val EmployeeCode: String,
    val EmployeeRole: String,
    val EmployeeStatus: String,
    val EmployeeType: String,
    val Guid: String
)