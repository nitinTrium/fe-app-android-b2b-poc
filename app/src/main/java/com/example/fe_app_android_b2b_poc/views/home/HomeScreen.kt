package com.example.fe_app_android_b2b_poc.views.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material.icons.outlined.AddCard
import androidx.compose.material.icons.outlined.Receipt
import androidx.compose.material.icons.outlined.Wallet
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fe_app_android_b2b_poc.R
import com.example.fe_app_android_b2b_poc.models.SpendWallet.Get.Result
import com.example.fe_app_android_b2b_poc.models.SpendWallet.Get.SpendWalletRES
import com.example.fe_app_android_b2b_poc.ui.components.Chip
import com.example.fe_app_android_b2b_poc.views.vcn.VcnCard
import com.example.fe_app_android_b2b_poc.views.vcn.VcnViewModel

@Composable
fun HomeScreen(
    createVcnBtn: () -> Unit,
    createExpenseBtn: () -> Unit
){
    val vcnState: VcnViewModel = hiltViewModel()
    val spendWalletList = vcnState.spendWalletsList.observeAsState()
    val vcnList = vcnState.vcnList.observeAsState()

    LaunchedEffect(null){
        vcnState.getSpendWallets()
        vcnState.getVcns()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
//            .padding(10.dp)
    ) {
        val scrollState = rememberScrollState()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp)
                .horizontalScroll(scrollState)
        ) {
            spendWalletList.value?.data?.results?.forEach {
                SpendWallet(data = it)
            }
        }

        Text(
            text = "Services",
            modifier = Modifier
                .padding(15.dp),
            style = MaterialTheme.typography.titleLarge
        )

        Row(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
                    .clickable { createVcnBtn() },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .size(40.dp),
                    imageVector = Icons.Outlined.AddBox,
                    contentDescription = "create-vcn",
                    tint = colorResource(id = R.color.primary)
                )
                Text(text = "Create Card", textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium)
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
                    .clickable { createExpenseBtn() },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .size(40.dp),
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "scan",
                    tint = colorResource(id = R.color.primary)
                )
                Text(text = "Create Expense", textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium)
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .size(40.dp),
                    imageVector = Icons.Outlined.AddCard,
                    contentDescription = "add-credit-card",
                    tint = colorResource(id = R.color.primary)
                )
                Text(text = "Add Credit Card", textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium)
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .size(40.dp),
                    imageVector = Icons.Outlined.Receipt,
                    contentDescription = "bill",
                    tint = colorResource(id = R.color.primary)
                )
                Text(text = "Bill", textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium)
            }
        }

        Text(
            text = "Transactions",
            modifier = Modifier
                .padding(15.dp),
            style = MaterialTheme.typography.titleLarge
        )

        Column (
            modifier = Modifier
//                            .width(390.dp)
                .fillMaxWidth()
                .padding(top = 0.dp, start = 15.dp, end = 15.dp, bottom = 15.dp)
                .shadow(
                    elevation = 0.dp,
                    shape = RoundedCornerShape(16.dp)
                )
                .background(
                    color = colorResource(id = R.color.primary_light),
                    shape = RoundedCornerShape(16.dp)
                )
                .height(200.dp)
                .padding(15.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ){
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "$850.00",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "xxxx xxxx xxxx 8629",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Text(
                        modifier = Modifier.padding(bottom = 20.dp),
                        text = "Expiry: 02/25",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Gray
                    )
                    Text(
                        text = "Linked to: James.2004",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Gray
                    )
                }
                Row(){
                    //                        Image(
                    //                            painter = painterResource(id = R.drawable.mastercard),
                    //                            contentDescription = "mastercard-logo",
                    //                            modifier = Modifier.size(60.dp)
                    //                        )
                    Image(
                        painter = painterResource(id = R.drawable.mastercard),
                        contentDescription = "mastercard-logo",
//                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .size(60.dp)
//                            .clip(CircleShape)
                    )
                }
            }
        }

    }
}

@Composable
fun SpendWallet(
    data: Result
){
    Row(
        modifier = Modifier
            .padding(15.dp, end = 0.dp)
            .width(350.dp)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(20.dp)
            )
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(16.dp)
            )
            .height(100.dp)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Available balance",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "$"+String.format("%.2f", data.available_limit.toFloat()),
                style = MaterialTheme.typography.headlineMedium
            )
        }


        Chip(
            label = data.wallet_name
        )

//        Icon(
//            modifier = Modifier
//                .padding(end = 10.dp)
//                .size(50.dp),
//            imageVector = Icons.Outlined.Wallet,
//            contentDescription = "wallet",
//            tint = Color.Gray
//        )
    }
}