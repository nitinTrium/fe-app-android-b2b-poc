package com.example.fe_app_android_b2b_poc.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.fe_app_android_b2b_poc.R

@Composable
fun TextInputField(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    text: String,
    placeHolder: String = "",
    type: String = "",
    clickable: () -> Unit = {},
    onChange: (onChange: String) -> Unit
){
    OutlinedTextField(
        value = text,
        enabled = enabled,
        readOnly = readOnly,
        onValueChange = {
            onChange(it) },
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                clickable()
            },
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = colorResource(id = R.color.purple_700),
            unfocusedContainerColor = colorResource(id = R.color.white),
            focusedContainerColor = colorResource(id = R.color.white),
            unfocusedTextColor = colorResource(id = R.color.dark_grey),
            focusedTextColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.light_grey),
            focusedBorderColor = colorResource(id = R.color.primary),
        ),
        textStyle = MaterialTheme.typography.labelLarge,
        placeholder = {
            Text(
                text = placeHolder,
                style = MaterialTheme.typography.labelLarge,
                color = colorResource(id = R.color.light_grey)
            )
        },
        keyboardOptions = if(type === "number") KeyboardOptions(keyboardType = KeyboardType.Number)
            else KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}

@Composable
fun PasswordInputField(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    text: String,
    placeHolder: String = "",
    visibility: MutableState<Boolean> = remember { mutableStateOf(false) },
    onChange: (onChange: String) -> Unit
){
    OutlinedTextField(
        value = text,
        enabled = enabled,
        readOnly = readOnly,
        onValueChange = {
            onChange(it) },
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = colorResource(id = R.color.purple_700),
            unfocusedContainerColor = colorResource(id = R.color.white),
            focusedContainerColor = colorResource(id = R.color.white),
            unfocusedTextColor = colorResource(id = R.color.dark_grey),
            focusedTextColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.light_grey),
            focusedBorderColor = colorResource(id = R.color.primary),
        ),
        textStyle = MaterialTheme.typography.labelLarge,
        trailingIcon = {
              Icon(
                  modifier = Modifier
                      .size(25.dp)
                      .clickable {
                          visibility.value = !visibility.value
                      },
                  imageVector = if (visibility.value) {
                      Icons.Outlined.VisibilityOff
                  } else {
                      Icons.Outlined.Visibility
                  },
                  contentDescription = "show-password",
//                  tint = if (visibility.value) titleColor else fadedTextColor
              )
//            Icon(
//                painter = painterResource(id = trailingIcon),
//                contentDescription = "",
//                modifier = Modifier
//                    .size(25.dp)
//                    .clickable {
//                        visibility.value = !visibility.value
//                    },
//                tint = if (visibility.value) titleColor else fadedTextColor
//            )
        },
        visualTransformation = if (visibility.value) VisualTransformation.None else PasswordVisualTransformation(),
        placeholder = {
            Text(
                text = placeHolder,
                style = MaterialTheme.typography.labelLarge,
                color = colorResource(id = R.color.light_grey)
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectInputField(
    modifier: Modifier = Modifier,
    isExpanded: Boolean = false,
    handleClose: () -> Unit,
    handleOpen: () -> Unit,
    enabled: Boolean = true,
    text: String,
    placeHolder: String = "",
    renderList: @Composable () -> Unit,
) {
    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { handleOpen() }
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {

            },
            readOnly = true,
            enabled = enabled,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
            modifier = modifier
                .menuAnchor()
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = colorResource(id = R.color.purple_700),
                unfocusedContainerColor = colorResource(id = R.color.white),
                focusedContainerColor = colorResource(id = R.color.white),
                unfocusedTextColor = colorResource(id = R.color.dark_grey),
                focusedTextColor = colorResource(id = R.color.black),
                unfocusedBorderColor = colorResource(id = R.color.light_grey),
                focusedBorderColor = colorResource(id = R.color.primary),
            ),
            textStyle = MaterialTheme.typography.labelLarge,
            placeholder = {
                Text(
                    text = placeHolder,
                    style = MaterialTheme.typography.labelLarge,
                    color = colorResource(id = R.color.light_grey)
                )
            }
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {  handleClose() }) {
            renderList()
        }
    }
}
