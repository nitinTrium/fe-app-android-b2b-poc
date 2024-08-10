package com.example.fe_app_android_b2b_poc.views.expense

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.fe_app_android_b2b_poc.R
import com.example.fe_app_android_b2b_poc.models.Expense.Details.Get.ExpenseAttachment
import com.example.fe_app_android_b2b_poc.models.Expense.Details.Get.ExpenseEmployee
import com.example.fe_app_android_b2b_poc.ui.components.BackTitleBar
import com.example.fe_app_android_b2b_poc.ui.components.Chip
import com.example.fe_app_android_b2b_poc.ui.components.LoadingButtonPrimary
import com.example.fe_app_android_b2b_poc.ui.components.PageLoading

@Composable
fun ExpenseDetailsScreen (
    id: String,
    handleClose: () -> Unit
) {
    val state: ExpenseViewModal = hiltViewModel()
    val expenseDetails = state.expenseDetails.observeAsState()

    LaunchedEffect(id){
        state.getExpenseDetails(id)
    }
    val expenseDetailsScrollState = rememberScrollState()
    Column(
        modifier = Modifier
//            .fillMaxSize()
            .verticalScroll(expenseDetailsScrollState)
            .padding(15.dp),
    ) {
        BackTitleBar("Expense details") {
            handleClose()
        }

        if (state.isLoading.value) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PageLoading()
            }
        } else {
            Column (
                modifier = Modifier
                    .fillMaxSize()
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "$"+String.format("%.2f", expenseDetails.value?.data?.amount?.toFloat()),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Chip(
                        label = expenseDetails.value?.data?.status.toString(),
                        variant = when(expenseDetails.value?.data?.status.toString()){
                            "Created" -> "info"
                            "Pending Approval" -> "warning"
                            "Approved" -> "success"
                            "Rejected" -> "error"
                            else -> ""
                        },
                    )
                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 30.dp),
                    text = expenseDetails.value?.data?.name.toString(),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )

                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 30.dp),
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp),
                        text = "Description",
                        style = MaterialTheme.typography.labelSmall,
                        color = colorResource(id = R.color.grey)
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = expenseDetails.value?.data?.description.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 30.dp),
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp),
                        text = "Category",
                        style = MaterialTheme.typography.labelSmall,
                        color = colorResource(id = R.color.grey)
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text =  expenseDetails.value?.data?.ref_category_id?.category.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }


                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 30.dp),
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp),
                        text = "Cost Account",
                        style = MaterialTheme.typography.labelSmall,
                        color = colorResource(id = R.color.grey)
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text =  expenseDetails.value?.data?.ref_cost_account_id?.name.toString() + " > " +
                                expenseDetails.value?.data?.ref_cost_account_id?.ref_cost_object_id?.name.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 30.dp),
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 0.dp),
                        text = "Additional employees",
                        style = MaterialTheme.typography.labelSmall,
                        color = colorResource(id = R.color.grey)
                    )
                    val expenseEmployeesScrollState = rememberScrollState()
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 15.dp)
                            .horizontalScroll(expenseEmployeesScrollState),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ){
                        expenseDetails.value?.data?.expense_employees?.forEach {
                            employee: ExpenseEmployee ->
                            Chip(
                                label = employee.ref_employee_id.employee_name
                            )
                        }
                    }
                }

                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 30.dp),
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 0.dp),
                        text = "Receipt",
                        style = MaterialTheme.typography.labelSmall,
                        color = colorResource(id = R.color.grey)
                    )
                    val receiptsScrollState = rememberScrollState()
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 15.dp)
                            .horizontalScroll(receiptsScrollState),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ){
                        expenseDetails.value?.data?.expense_attachments?.forEach {
                                receipt: ExpenseAttachment? ->
                            Image(
                                modifier = Modifier.size(200.dp),
                                painter = rememberAsyncImagePainter(receipt?.attachment_path),
                                contentDescription = "ExpenseReceipts"
                            )
                        }
                    }
                }
            }
        }
    }


    if(expenseDetails.value?.data?.status.toString() == "Created"){
        Box (
            modifier = Modifier
                .padding(15.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ){
            LoadingButtonPrimary(
                onClick = { state.sendToApprover(expenseDetails.value?.data?.expense_id.toString()) },
                label = "Send to approver",
                loading = state.isButtonLoading.value
            )

        }
    }
}