package com.example.fe_app_android_b2b_poc.repository

import com.example.fe_app_android_b2b_poc.api.ExpenseAPI
import com.example.fe_app_android_b2b_poc.models.Expense.Details.Get.ExpenseDetailsRES
import com.example.fe_app_android_b2b_poc.models.Expense.Get.ExpensesRES
import com.example.fe_app_android_b2b_poc.models.Expense.Post.CreateExpenseREQ
import com.example.fe_app_android_b2b_poc.models.Expense.Post.SendToApproverREQ
import com.google.gson.Gson
import javax.inject.Inject

class ExpenseRepository @Inject constructor(
    private val expenseAPI: ExpenseAPI
) {
    suspend fun getExpenses(): ExpensesRES?{
        val response = expenseAPI.getExpenses()
        if(response.isSuccessful && response.body() != null){
            val gson = Gson()
            val temp = gson.fromJson(gson.toJson(response.body()).toString(), ExpensesRES::class.java)
            return temp
        }
        return null
    }

    suspend fun getExpenseDetails(id: String): ExpenseDetailsRES?{
        val response = expenseAPI.getExpenseDetails(id)
        if(response.isSuccessful && response.body() != null){
            val gson = Gson()
            val temp = gson.fromJson(gson.toJson(response.body()).toString(), ExpenseDetailsRES::class.java)
            return temp
        }
        return null
    }

    suspend fun createExpense(createExpenseREQ: CreateExpenseREQ): Any{
        val response = expenseAPI.createExpense(createExpenseREQ)

        return "";
    }

    suspend fun sendToApprover(sendToApproverREQ: SendToApproverREQ): Any{
        val response = expenseAPI.sendToApprover(sendToApproverREQ)

        return "";
    }
}