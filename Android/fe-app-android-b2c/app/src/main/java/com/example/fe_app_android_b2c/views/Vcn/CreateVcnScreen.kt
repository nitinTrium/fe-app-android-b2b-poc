package com.example.fe_app_android_b2c.views.Vcn

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fe_app_android_b2c.ui.components.ButtonPrimary
import com.example.fe_app_android_b2c.ui.components.ButtonPrimaryText
import com.example.fe_app_android_b2c.ui.components.NumberInputField
import com.example.fe_app_android_b2c.ui.components.TextInputField
import com.example.fe_app_android_b2c.viewModel.VcnViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CreateVcnScreen(
//    state: VcnViewModel,
    onClose: () -> Unit
) {
    val state: VcnViewModel = hiltViewModel()

    val amount = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp)
                .clickable {
                    onClose()
                },
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                modifier = Modifier.padding(end = 10.dp),
                imageVector = Icons.Filled.Close,
                contentDescription = "close"
            )
            Text(
                text = "Create virtual card",
                style = MaterialTheme.typography.titleLarge
            )
        }

        val pattern = remember { Regex("^\\d+\$") }
        NumberInputField(
            modifier = Modifier.padding(bottom = 20.dp),
            text = amount.value,
            placeHolder = "Amount",
            onChange= { if (it.isEmpty() || it.matches(pattern)) { amount.value = it }}
        )
        TextInputField(
            modifier = Modifier.padding(bottom = 20.dp),
            text = description.value,
            placeHolder = "Description",
            onChange = { description.value = it }
        )
        ButtonPrimaryText(
            label = "Create",
            onClick = {
//                GlobalScope.launch {
                    state.createVcn(
                        amount = amount.value,
                        description = description.value
                    )
//                    delay(2000)
                    onClose()
//                }
            }
        )
    }
}