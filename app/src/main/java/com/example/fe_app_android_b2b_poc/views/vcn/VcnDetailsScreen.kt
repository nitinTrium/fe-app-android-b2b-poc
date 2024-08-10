package com.example.fe_app_android_b2b_poc.views.vcn

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fe_app_android_b2b_poc.R
import com.example.fe_app_android_b2b_poc.models.Vcn.Details.VcnDetailsRES
import com.example.fe_app_android_b2b_poc.ui.components.BackTitleBar
import com.example.fe_app_android_b2b_poc.ui.components.Chip
import com.example.fe_app_android_b2b_poc.ui.components.PageLoading
import com.example.fe_app_android_b2b_poc.ui.components.utils.VendorAvatar

@Composable
fun VcnDetailsScreen(
    id: String,
    handleClose: () -> Unit
) {
    val state: VcnViewModel = hiltViewModel()
    val vcnDetails = state.vcnDetails.observeAsState()

    LaunchedEffect(id){
        state.getVcnDetails(id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
    ) {
        BackTitleBar("Card details"){
            handleClose()
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
        }else {
            VcnCard(
                vcn = vcnDetails.value
            )
            
            Text(
                modifier = Modifier.padding(top = 20.dp, bottom = 10.dp),
                text = "Vendors",
                style = MaterialTheme.typography.titleLarge
            )

            if(vcnDetails.value?.data?.vendors?.isEmpty() == true){
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Not for any specific vendor",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }else {
                vcnDetails.value?.data?.vendors?.forEachIndexed { index, vendor ->
                    VendorAvatar(
                        label = vendor.name,
                        color = vendor.color
                    )
            }

            }
        }
    }
}

@Composable
fun VcnCard(
    vcn: VcnDetailsRES?
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
            .height(220.dp)
            .padding(15.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$"+String.format("%.2f", vcn?.data?.amount?.toFloat()),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Chip(
                label = vcn?.data?.status.toString() ?: ""
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${vcn?.data?.vcn_details?.pan?.substring(0, 4)} " +
                        "${vcn?.data?.vcn_details?.pan?.substring(4, 8)} " +
                        "${vcn?.data?.vcn_details?.pan?.substring(8, 12)} " +
                        "${vcn?.data?.vcn_details?.pan?.substring(12, 16)} ",
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                modifier = Modifier,
                text = "Expiry:" + vcn?.data?.vcn_details?.expiry.toString(),
                style = MaterialTheme.typography.titleLarge,
                fontStyle = FontStyle.Italic
            )
            Text(
                modifier = Modifier,
                text = "CVV:" + vcn?.data?.vcn_details?.cvv.toString(),
                style = MaterialTheme.typography.titleLarge,
                fontStyle = FontStyle.Italic
            )
        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier,
                text = vcn?.data?.ref_spend_wallet_id?.wallet_name.toString(),
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray
            )
            Row() {
                Image(
                    painter = painterResource(id = R.drawable.mastercard),
                    contentDescription = "mastercard-logo",
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .size(50.dp)
                )
            }
        }
    }
}