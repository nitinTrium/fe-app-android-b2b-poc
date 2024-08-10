package com.example.fe_app_android_b2c.views.RealCard

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fe_app_android_b2c.R
import com.example.fe_app_android_b2c.models.BaseResponse
import com.example.fe_app_android_b2c.ui.components.ButtonPrimary
import com.example.fe_app_android_b2c.utils.NetworkResult
import com.example.fe_app_android_b2c.viewModel.RealCardViewModel


@Composable
fun RealCardScreen(

) {
    val state: RealCardViewModel = hiltViewModel()
    val cards = state.realCardList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
//                .height(70.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                modifier = Modifier.padding(bottom = 15.dp),
                text = "Real Cards",
                style = MaterialTheme.typography.titleLarge
            )
            Row(modifier = Modifier.width(180.dp)){
                ButtonPrimary(
                    content = {
                        Icon(imageVector = Icons.Filled.Add,
                            contentDescription = "add")
                        Text(text = "Add Real Card",
                            style = MaterialTheme.typography.titleMedium)
                    },
                    onClick = { /*TODO*/ }
                )
            }
        }
        cards.value.forEachIndexed{ index, item ->
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