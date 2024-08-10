package com.example.fe_app_android_b2c.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.fe_app_android_b2c.R

@Composable
fun NumberInputField(
    text: String,
    modifier: Modifier = Modifier,
    placeHolder: String = "",
    onChange: (onChange: String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = {
            onChange(it)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors(
            cursorColor = colorResource(id = R.color.purple_700),
            unfocusedContainerColor = colorResource(id = R.color.primary_light),
            focusedContainerColor = colorResource(id = R.color.primary),
            unfocusedTextColor = colorResource(id = R.color.secondary),
            focusedTextColor = colorResource(id = R.color.secondary_dark),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        textStyle = MaterialTheme.typography.labelLarge,
        placeholder = {
            Text(
                text = placeHolder,
                style = MaterialTheme.typography.labelSmall,
                color = colorResource(id = R.color.gray_text)
            )
        }
    )
}

@Composable
fun TextInputField(
    text: String,
    modifier: Modifier = Modifier,
    placeHolder: String = "",
    onChange: (onChange: String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = {
            onChange(it) },
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors(
            cursorColor = colorResource(id = R.color.purple_700),
            unfocusedContainerColor = colorResource(id = R.color.primary_light),
            focusedContainerColor = colorResource(id = R.color.primary),
            unfocusedTextColor = colorResource(id = R.color.secondary),
            focusedTextColor = colorResource(id = R.color.secondary_dark),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        textStyle = MaterialTheme.typography.labelLarge,
        placeholder = {
            Text(
                text = placeHolder,
                style = MaterialTheme.typography.labelSmall,
                color = colorResource(id = R.color.gray_text)
                )
        }
    )
}