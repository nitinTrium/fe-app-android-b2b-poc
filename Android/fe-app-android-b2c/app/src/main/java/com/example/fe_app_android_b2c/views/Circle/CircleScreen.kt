package com.example.fe_app_android_b2c.views.Circle

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fe_app_android_b2c.ui.components.ButtonPrimary

@Composable
fun CircleScreen(
    
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(bottom = 15.dp),
                text = "Circle",
                style = MaterialTheme.typography.titleLarge
            )
            Row(modifier = Modifier.width(180.dp)) {
                ButtonPrimary(
                    content = {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
                        Text(
                            text = "Create Circle",
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    onClick = { }
                )
            }
        }

        Row (
            modifier = Modifier
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "8CM2Y7",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(end = 10.dp)
            )
            Icon(imageVector = Icons.Outlined.ContentCopy, contentDescription = "copy-btn")
        }

    }

//        Row(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(15.dp),
//        horizontalArrangement = Arrangement.Center
//    ){
//        Text(text = "Circle Code: ", style = MaterialTheme.typography.titleLarge)
//        Text(text = "8CM2Y7", style = MaterialTheme.typography.titleLarge)
//    }
}