package com.example.fe_app_android_b2b_poc.api

import com.example.fe_app_android_b2b_poc.models.Expense.Post.CreateExpenseREQ
import com.example.fe_app_android_b2b_poc.models.Expense.Post.SendToApproverREQ
import com.example.fe_app_android_b2b_poc.models.Vcn.CreateVcnREQ
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ExpenseAPI {
    @GET("virtual-cards/exp/v1/expense/")
    suspend fun getExpenses(
        @Query("c_id") companyId: String = "df1043ac-a888-4a35-b016-8cd9232360d5",
        @Query("page") page: String = "1",
        @Query("page_size") pageSize: String = "100",
    ): Response<Any>

    @GET("virtual-cards/exp/v1/expense/{id}/")
    suspend fun getExpenseDetails(
        @Path("id") id: String,
        @Query("c_id") companyId: String = "df1043ac-a888-4a35-b016-8cd9232360d5",
    ): Response<Any>

    @POST("virtual-cards/exp/v1/expense/")
    suspend fun createExpense(
        @Body createExpenseREQ: CreateExpenseREQ,
        @Query ("c_id") companyId: String = "df1043ac-a888-4a35-b016-8cd9232360d5",
    ): Response<Any>

    @POST("virtual-cards/exp/v1/expense-for-approval/")
    suspend fun sendToApprover(
        @Body sendToApproverREQ: SendToApproverREQ,
        @Query ("c_id") companyId: String = "df1043ac-a888-4a35-b016-8cd9232360d5",
    ): Response<Any>


}