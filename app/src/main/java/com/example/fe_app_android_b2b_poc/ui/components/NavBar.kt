package com.example.fe_app_android_b2b_poc.ui.components

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx. compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fe_app_android_b2b_poc.CurrentRoute
import com.example.fe_app_android_b2b_poc.NavItemState

@Composable
fun NavBar(
    items: List<NavItemState>,
    navigation: NavHostController
) {
    var bottomNavState by rememberSaveable {
        mutableStateOf(0)
    }

    val currentItem = items.find{ it ->
        it.route.equals(CurrentRoute(navController = navigation).toString())
    }

    if (currentItem != null) {
        bottomNavState = currentItem.position
    }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = bottomNavState == index,
                onClick = {
                    if ( bottomNavState !=  index){
                        bottomNavState = index
                        navigation.navigate(item.route){
                            popUpTo("home"){
                                if(item.route === "home"){
                                    inclusive = true
                                }
                            }
                        }
                    }
                },
                label = {
                    Text(
                        text = item.title,
                        fontWeight = if (bottomNavState == index) FontWeight.Bold
                        else FontWeight.SemiBold,
                        style = MaterialTheme.typography.labelMedium,
                        color = if (bottomNavState == index) Color.Black
                        else Color.DarkGray
                    )
                },
                icon = {
                    Icon(
                        imageVector = if (bottomNavState == index) item.selectedIcon
                        else item.unSelectedIcon,
                        contentDescription = item.title,
                        modifier = if (index == 2) {
                            Modifier.size(38.dp)
                        } else {
                            Modifier
                        },
                        tint = if (bottomNavState == index) Color.Black
                        else Color.DarkGray
                    )

                }
            )
        }
    }
}