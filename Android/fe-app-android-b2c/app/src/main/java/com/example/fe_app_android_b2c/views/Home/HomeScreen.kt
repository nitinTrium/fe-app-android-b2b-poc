package com.example.fe_app_android_b2c.views.Home

import androidx.compose.ui.text.font.FontStyle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.GroupAdd
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material.icons.outlined.AddCard
import androidx.compose.material.icons.outlined.GroupAdd
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fe_app_android_b2c.R
import com.example.fe_app_android_b2c.ui.components.ButtonPrimary
import com.example.fe_app_android_b2c.viewModel.RealCardViewModel
import com.example.fe_app_android_b2c.viewModel.VcnViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@Composable
fun HomeScreen(
    createVcnBtn: () -> Unit
) {
    val realCardViewModel: RealCardViewModel = hiltViewModel();
    val realCardsList = realCardViewModel.realCardList.collectAsState()

    val vcnViewModel: VcnViewModel = hiltViewModel()
    val vcnsList = vcnViewModel.vcnList.collectAsState()

    Column (
        modifier = Modifier
            .fillMaxSize(),
//        horizontalArrangement = Arrangement.Center,
//        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .padding(top = 25.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
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
                    imageVector = Icons.Filled.QrCodeScanner,
                    contentDescription = "scan",
                    tint = colorResource(id = R.color.secondary_light)
                    )
                Text(text = "Scan QR", textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium)
            }
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
                    tint = colorResource(id = R.color.secondary_light)
                )
                Text(text = "Create VCN", textAlign = TextAlign.Center,
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
                    tint = colorResource(id = R.color.secondary_light)
                )
                Text(text = "Add Card", textAlign = TextAlign.Center,
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
                    imageVector = Icons.Outlined.GroupAdd,
                    contentDescription = "create-circle",
                    tint = colorResource(id = R.color.secondary_light)
                )
                Text(text = "Create Circle", textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium)
            }
        }
        Text(
            modifier = Modifier
                .padding(15.dp),
            text = "Virtual Cards",
            style = MaterialTheme.typography.titleLarge
        )
        val scrollState = rememberScrollState()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState)
                .padding(horizontal = 15.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            vcnsList.value.forEachIndexed { index, vcnObj ->
//                if( index == 0){
                    Column (
                        modifier = Modifier
//                            .width(390.dp)
                            .width(390.dp)
                            .padding( end = 15.dp, bottom = 15.dp)
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
//                }
            }
        }
        Text(
            modifier = Modifier
                .padding(15.dp),
            text = "Credit Cards",
            style = MaterialTheme.typography.titleLarge
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
        ) {
            realCardsList.value.forEachIndexed{ index, item ->
                if (index == 0) {
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(
                                elevation = 5.dp,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .background(
                                color = colorResource(id = R.color.white),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .height(150.dp)
                            .padding(15.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ){
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "${item.Alias}",
                                style = MaterialTheme.typography.titleLarge
                            )
                            Row(){
                                Text(
                                    text = "xxxx ${item.Last4Digits}",
                                    style = MaterialTheme.typography.titleLarge
                                )
                            }
                        }
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = "${item.Currency}",
                                style = MaterialTheme.typography.titleLarge,
                                fontStyle = FontStyle.Italic
                            )
//                    Text(
//                        text = "${item.Network}",
//                        style = MaterialTheme.typography.titleLarge
//                    )
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
//        Text(
//            modifier = Modifier.padding(15.dp),
//            text = "Circle",
//            style = MaterialTheme.typography.titleLarge
//        )
    }
}