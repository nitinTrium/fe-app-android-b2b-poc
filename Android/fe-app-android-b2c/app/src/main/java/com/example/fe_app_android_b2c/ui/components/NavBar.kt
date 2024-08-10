package com.example.fe_app_android_b2c.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController

import com.example.fe_app_android_b2c.NavItemState

@Composable
fun NavBar(
    items: List<NavItemState>,
    navigation: NavHostController
) {
    var bottomNavState by rememberSaveable {
        mutableStateOf(0)
    }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = bottomNavState == index,
                onClick = {
                    if ( bottomNavState !=  index){
                        bottomNavState = index
                        navigation.navigate(item.route)
                    }
                },
                label = {
                    Text(
                        text = item.title,
                        fontWeight = if (bottomNavState == index) FontWeight.Bold
                        else FontWeight.Normal,
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                icon = {
                    Icon(
                        imageVector = if (bottomNavState == index) item.selectedIcon
                        else item.unSelectedIcon,
                        contentDescription = item.title)
                }
            )
        }
    }
}