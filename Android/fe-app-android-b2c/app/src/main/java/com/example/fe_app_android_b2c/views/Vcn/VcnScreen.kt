package com.example.fe_app_android_b2c.views.Vcn

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fe_app_android_b2c.R
import com.example.fe_app_android_b2c.ui.components.ButtonPrimary
import com.example.fe_app_android_b2c.ui.components.ButtonPrimaryText
import com.example.fe_app_android_b2c.viewModel.VcnViewModel

@Composable
fun VcnScreen(
//    state: VcnViewModel,
    onCreateNavigate: () -> Unit
){
    val state: VcnViewModel = hiltViewModel()
    val cards = state.vcnList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                modifier = Modifier.padding(bottom = 15.dp),
                text = "Virtual Cards",
                style = MaterialTheme.typography.titleLarge
            )
            Row(modifier = Modifier.width(180.dp)) {
                ButtonPrimary(
                    content = {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
                        Text(
                            text = "Create VCN",
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    onClick = { onCreateNavigate() }
                )
            }
        }
        Log.d("CARD", cards.toString())
        cards.value.forEachIndexed { index, vcnObj ->
            Column (
                modifier = Modifier
                    .padding(bottom = 15.dp)
                    .fillMaxWidth()
                    .shadow(
                        elevation = 5.dp,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(
                        color = colorResource(id = R.color.primary_light),
                        shape = RoundedCornerShape(20.dp)
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
                        text = "$${vcnObj.Amount}",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "xxxx xxxx xxxx ${vcnObj.Last4Digits}",
                        style = MaterialTheme.typography.titleLarge
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
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "Linked to: ${vcnObj.ParentConfigurations.RealCard.Alias}",
                            style = MaterialTheme.typography.titleLarge
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
}