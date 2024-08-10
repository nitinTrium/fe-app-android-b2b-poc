package com.example.fe_app_android_b2b_poc.ui.components

import android.graphics.ColorSpace.Rgb
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Notifications
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
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.fe_app_android_b2b_poc.CurrentRoute
import com.example.fe_app_android_b2b_poc.NavItemState
import com.example.fe_app_android_b2b_poc.R
import com.example.fe_app_android_b2b_poc.views.profile.ProfileViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    scope: CoroutineScope,
    drawerState: DrawerState,
    items: List<NavItemState>,
    navigation: NavHostController,
//    handleNotification: () -> Unit
) {

    val currentItem = items.find{ it ->
        it.route.equals(CurrentRoute(navController = navigation).toString())
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(
                colorResource(id = R.color.primary),
                shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
            )
            .padding(horizontal = 15.dp, vertical = 10.dp)
    ) {
        when (currentItem?.route) {
            "home" -> {
                HomeScreenTopBar() {
                    navigation.navigate("notification")
                }
            }
            "vcn" -> {
                VcnScreenTopBar() {
                    navigation.navigate("create-vcn")
                }
            }
            "expense" -> {
                ExpenseScreenTopBar() {
                    navigation.navigate("create-expense")
                }
            }
            "profile" -> {
                AccountScreenTopBar() {
                    navigation.navigate("notification")
                }
            }
        }
    }
}

@Composable
fun HomeScreenTopBar(
    handleNotification: () -> Unit
){
    val state: ProfileViewModel = hiltViewModel()
    val profile = state.profile.observeAsState()

    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
        ) {
            Text(
                text = "Hello",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
            Text(
                text =
                    (profile.value?.data?.action?.FirstName ?: "") + " " +
                            (profile.value?.data?.action?.LastName ?: ""),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Icon(
            modifier = Modifier
                .padding(end = 10.dp)
                .size(30.dp)
                .clickable { handleNotification() },
            imageVector = Icons.Outlined.Notifications,
            contentDescription = "notifications",
            tint = Color.White
        )
    }
}

@Composable
fun VcnScreenTopBar(
    handleCreateVcn: () -> Unit
){
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
        ) {
            Text(
                text = "Cards",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Icon(
            modifier = Modifier
                .padding(end = 10.dp)
                .size(30.dp)
                .clickable { handleCreateVcn() },
            imageVector = Icons.Outlined.Add,
            contentDescription = "create-card",
            tint = Color.White
        )
    }
}

@Composable
fun ExpenseScreenTopBar(
    handleCreateExpense: () -> Unit
){
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
        ) {
            Text(
                text = "Expense",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Icon(
            modifier = Modifier
                .padding(end = 10.dp)
                .size(30.dp)
                .clickable { handleCreateExpense() },
            imageVector = Icons.Outlined.Add,
            contentDescription = "create-card",
            tint = Color.White
        )
    }
}

@Composable
fun AccountScreenTopBar(
    handleNotification: () -> Unit
){
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
        ) {
            Text(
                text = "Account",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
//        Icon(
//            modifier = Modifier
//                .padding(end = 10.dp)
//                .size(30.dp)
//                .clickable { handleNotification() },
//            imageVector = Icons.Outlined.Add,
//            contentDescription = "create-card",
//            tint = Color.White
//        )
    }
}