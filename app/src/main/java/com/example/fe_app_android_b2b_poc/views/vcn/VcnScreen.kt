package com.example.fe_app_android_b2b_poc.views.vcn

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fe_app_android_b2b_poc.R
import com.example.fe_app_android_b2b_poc.models.Vcn.Result
import com.example.fe_app_android_b2b_poc.ui.components.Chip
import com.example.fe_app_android_b2b_poc.ui.components.PageLoading

@Composable
fun VcnScreen(
    handleClick: (String) -> Unit
){
    val state: VcnViewModel = hiltViewModel()

    LaunchedEffect(null){
        state.getVcns()
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
                    items = state.vcnList.value ?: emptyList(),
                    key = { vcn ->
                        // Return a stable + unique key for the item
                        vcn.vcn_id
                    }
                ) { it ->
                    VcnCard(vcn = it){
//                        state.getVcnDetails(it)
                        handleClick(it)
                    }
                }
            }
        }
    }
}

@Composable
fun VcnCard(
    vcn: Result,
    handleClick: (String) -> Unit
){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { handleClick(vcn.vcn_id) }
            .height(180.dp)
            .padding(15.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ){
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$"+String.format("%.2f", vcn.amount.toFloat()),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Chip(
                label = vcn.status.toString() ?: ""
            )
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "xxxx xxxx xxxx ${vcn.last_four_digits}",
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
                    modifier = Modifier.padding(bottom = 10.dp),
                    text = vcn.ref_spend_wallet_id.wallet_name,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Gray
                )
                Text(
                    text = "Linked to " + vcn.ref_spend_wallet_id.ref_real_card_id.last_four_digits,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Gray
                )
            }
            Row(){
                Image(
                    painter = painterResource(id = R.drawable.mastercard),
                    contentDescription = "mastercard-logo",
//                        contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .size(50.dp)
//                            .clip(CircleShape)
                )
            }
        }
//
//        Row (
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text(
//                text = vcn.ref_spend_wallet_id.wallet_name,
//                style = MaterialTheme.typography.titleMedium,
//                color = Color.Gray
//            )
//        }
//
//        Row (
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text(
//                text = "Linked to " + vcn.ref_spend_wallet_id.ref_real_card_id.last_four_digits,
//                style = MaterialTheme.typography.titleMedium,
//                color = Color.Gray
//            )
//        }

//        Row (
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ){
//            Text(text = "$"+vcn.amount.toFloat().toString())
//            Text(text = vcn.last_four_digits)
//        }
    }
}
