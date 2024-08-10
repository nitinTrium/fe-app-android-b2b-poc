package com.example.fe_app_android_b2b_poc.views.expense

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fe_app_android_b2b_poc.R
import com.example.fe_app_android_b2b_poc.models.Expense.Get.Result
import com.example.fe_app_android_b2b_poc.ui.components.Chip
import com.example.fe_app_android_b2b_poc.ui.components.PageLoading
import com.example.fe_app_android_b2b_poc.views.vcn.VcnCard
import com.example.fe_app_android_b2b_poc.views.vcn.VcnViewModel

@Composable
fun ExpenseScreen(
    handleClick: (String) -> Unit
){
    val state: ExpenseViewModal = hiltViewModel()
    val expensesList = state.expensesList.observeAsState()

    LaunchedEffect(null){
        state.getExpenses()
    }

    if(state.isLoading.value){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PageLoading()
        }
    }else{
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ){
                items(
                    items = state.expensesList.value?.data?.results ?: emptyList(),
                    key = { expense ->
                        // Return a stable + unique key for the item
                        expense.expense_id
                    }
                ) { it ->
                    ExpenseCard(expense = it){
                        handleClick(it)
                    }
                }
            }
        }
    }
}

@Composable
fun ExpenseCard(
    expense: Result,
    handleClick: (String) -> Unit
){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 1.dp,
                color = Color("#e1e1e1".toColorInt()),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { handleClick(expense.expense_id) }
            .height(100.dp)
            .padding(15.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ){
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = expense.name,
                style = MaterialTheme.typography.titleMedium,
            )

            Chip(
                label = expense.status,
                variant = when(expense.status){
                    "Created" -> "info"
                    "Pending Approval" -> "warning"
                    "Approved" -> "success"
                    "Rejected" -> "error"
                    else -> ""
                },
            )
        }

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$"+String.format("%.2f", expense.amount.toFloat()),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = expense?.ref_category_id?.category?:"",
                style = MaterialTheme.typography.titleMedium,
//                fontWeight = FontWeight.Bold
                fontStyle = FontStyle.Italic,
                color = Color.Gray
            )
        }

    }
}