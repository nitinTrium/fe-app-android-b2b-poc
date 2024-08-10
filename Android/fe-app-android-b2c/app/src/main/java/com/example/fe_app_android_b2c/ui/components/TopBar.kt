package com.example.fe_app_android_b2c.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fe_app_android_b2c.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    scope: CoroutineScope,
    drawerState: DrawerState,
    onSearch: () -> Unit
) {
    var search by remember {
        mutableStateOf("")
    }

//    Row(
//        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 10.dp)
//                    .offset(x = (-16).dp)
                    .background(
                        colorResource(id = R.color.primary_light),
                        shape = RoundedCornerShape(80.dp)
                    )
                ,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.person),
                    contentDescription = "person",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .size(30.dp)
                        .clip(CircleShape)
                        .clickable {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }
                )
                TextField(
                    modifier = Modifier.clickable { onSearch() },
                    enabled = false,
                    value = search,
                    onValueChange = {it -> search = it },
                    shape = RoundedCornerShape(12.dp),
                    placeholder = {
                        Text(text = "Search friends and family")
                    },
                    colors = TextFieldDefaults.colors(
                        cursorColor = colorResource(id = R.color.primary_light),
                        unfocusedContainerColor = colorResource(id = R.color.primary_light),
                        focusedContainerColor = colorResource(id = R.color.primary_light),
                        unfocusedTextColor = colorResource(id = R.color.secondary),
                        focusedTextColor = colorResource(id = R.color.secondary_dark),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        disabledContainerColor = colorResource(id = R.color.primary_light)
                    ),
                    textStyle = MaterialTheme.typography.labelLarge,
                )
            }
//        },
//        colors = TopAppBarDefaults.topAppBarColors(
//            containerColor = colorResource(id = R.color.primary_light)),
//        navigationIcon = {
//            Row (
//                modifier = Modifier
//                    .width(1.dp)
//            ){
//
//            }
//        }
//    )
}