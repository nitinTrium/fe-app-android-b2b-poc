package com.example.fe_app_android_b2b_poc.views.expense

import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.fe_app_android_b2b_poc.ui.components.BackTitleBar
import com.example.fe_app_android_b2b_poc.ui.components.LoadingButtonPrimary
import com.example.fe_app_android_b2b_poc.ui.components.SelectInputField
import com.example.fe_app_android_b2b_poc.ui.components.TextInputField
import java.io.File
import java.io.FileOutputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateExpenseScreen(
    handleClose: () -> Unit
){
    val state: ExpenseViewModal = hiltViewModel()
    val formData = state.formData.observeAsState()
    val categoriesList = state.categoriesList.observeAsState()
    val costAccountList = state.costAccountsList.observeAsState()

    when(state.expenseCreated.value){
        true -> handleClose()
        else -> Unit
    }

    LaunchedEffect(null){
        state.getCategoriesList()
        state.getCostAccountsList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        BackTitleBar("Create expense") {
            handleClose()
        }


        TextInputField(
            modifier = Modifier
                .fillMaxWidth(),
            readOnly = state.isLoading.value,
            text = formData.value!!["amount"] ?: "",
            onChange = {
                if (it.isDigitsOnly()) state.onValueChanged("amount", it)
            },
            placeHolder = "Amount",
            type = "number"
        )

        TextInputField(
            modifier = Modifier
                .fillMaxWidth(),
            readOnly = state.isLoading.value,
            text = formData.value!!["name"] ?: "",
            onChange = {
                state.onValueChanged("name", it)
            },
            placeHolder = "Merchant",
        )

        TextInputField(
            modifier = Modifier
                .fillMaxWidth(),
            readOnly = state.isLoading.value,
            text = formData.value!!["description"] ?: "",
            onChange = {
                state.onValueChanged("description", it)
            },
            placeHolder = "Description",
        )

        var isCategorySelectExpanded by remember {
            mutableStateOf(false)
        }

        var isCostAccountSelectExpanded by remember {
            mutableStateOf(false)
        }

        SelectInputField(
            enabled = true,
            text = formData.value!!["category"] ?: "",
            placeHolder = "Category",
            isExpanded = isCategorySelectExpanded,
            handleClose = { isCategorySelectExpanded = false },
            handleOpen = { isCategorySelectExpanded = !isCategorySelectExpanded }
        ){
            categoriesList.value?.data?.results?.forEachIndexed { index, category ->
                DropdownMenuItem(
                    text = { Text(text = category.category) },
                    onClick = {
                        state.onValueChanged("category", category.category)
                        state.onValueChanged("categoryId", category.category_id)
                        isCategorySelectExpanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }

        SelectInputField(
            enabled = true,
            text = formData.value!!["costAccount"] ?: "",
            placeHolder = "Cost Account",
            isExpanded = isCostAccountSelectExpanded,
            handleClose = { isCostAccountSelectExpanded = false },
            handleOpen = { isCostAccountSelectExpanded = !isCostAccountSelectExpanded }
        ){
            costAccountList.value?.data?.results?.forEachIndexed { index, costAccount ->
                DropdownMenuItem(
                    text = { Text(text = costAccount.ref_cost_object_id.name + " > " + costAccount.name) },
                    onClick = {
                        state.onValueChanged("costAccount", costAccount.ref_cost_object_id.name + " > " + costAccount.name)
                        state.onValueChanged("costAccountId", costAccount.id)
                        isCostAccountSelectExpanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }

        CapturePhoto(){
            state.handleReceiptUpdate(it)
        }

        if(state.imageUri.value !== null){
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentAlignment = Alignment.Center
            ){
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(135.dp),
                    painter = rememberImagePainter(state.imageUri.value),
                    contentDescription = "Expense receipt",
                )
            }

        }

        LoadingButtonPrimary(
            onClick = { state.createExpense() },
            enabled = (formData.value!!["name"] ?: "").isNotEmpty() &&
                    (formData.value!!["description"] ?: "").isNotEmpty() &&
                    (formData.value!!["amount"] ?: "").isNotEmpty() &&
                    (formData.value!!["categoryId"] ?: "").isNotEmpty() &&
                    (formData.value!!["costAccountId"] ?: "").isNotEmpty() ,
            label = "Create",
            loading = state.isLoading.value
        )
    }
}


@Composable
fun CapturePhoto(
    onImageCaptured: (Uri?) -> Unit
) {
//    val context = LocalContext.current
//    val imageCaptureLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.TakePicturePreview()
//    ) { bitmap ->
//        bitmap?.let {
//            onImageCaptured(it)
//        }
//    }
//
//    Button(onClick = {
//        imageCaptureLauncher.launch(null)
//    }) {
//        Text(text = "Capture Receipt")
//    }

    val context = LocalContext.current
    val takePictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        bitmap?.let {
            val file = File(context.cacheDir, "image.jpg")
            val outputStream = FileOutputStream(file)
            rotateBitmap(it, 90f).compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            onImageCaptured(Uri.fromFile(file))
            outputStream.close()
        }
    }

    Button(onClick = {
        takePictureLauncher.launch(null)
    }) {
        Text("Capture Receipt")
    }
}

fun rotateBitmap(source: Bitmap, angle: Float): Bitmap {
    val matrix = Matrix()
    matrix.postRotate(angle)
    return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
}