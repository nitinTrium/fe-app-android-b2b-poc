package com.example.fe_app_android_b2b_poc.ui.components

import android.graphics.drawable.shapes.Shape
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.GroupAdd
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Translate
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.fe_app_android_b2b_poc.R

import com.example.fe_app_android_b2b_poc.utils.TokenManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

@Composable
fun NavDrawer(
    tokenManager: TokenManager,
    navController: NavHostController,
    protectedNavController: NavHostController,
) {
    ModalDrawerSheet {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(30.dp),
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = "settings",
//                    tint = colorResource(id = R.color.secondary_light)
                )
                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.titleMedium)
            }

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .clickable {
                        tokenManager.clearRefreshToken()
                        tokenManager.clearAccessToken()
                        navController.navigate("auth")
                    },
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(30.dp),
                    imageVector = Icons.Outlined.Logout,
                    contentDescription = "logout",
//                    tint = colorResource(id = R.color.secondary_light)
                )
                Text(
                    text = "Sign Out",
                    style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}