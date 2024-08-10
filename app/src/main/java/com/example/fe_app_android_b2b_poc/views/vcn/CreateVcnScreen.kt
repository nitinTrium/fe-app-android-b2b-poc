package com.example.fe_app_android_b2b_poc.views.vcn

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuBoxScope
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fe_app_android_b2b_poc.ui.components.BackTitleBar
import com.example.fe_app_android_b2b_poc.ui.components.LoadingButtonPrimary
import com.example.fe_app_android_b2b_poc.ui.components.SelectInputField
import com.example.fe_app_android_b2b_poc.ui.components.TextInputField
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateVcnScreen(
    handleClose: () -> Unit
) {
    val state: VcnViewModel = hiltViewModel()
    val formData = state.formData.observeAsState()
    val spendWalletList = state.spendWalletsList.observeAsState()

    LaunchedEffect(null){
        state.getSpendWallets()
    }

    when(state.vcnCreated.value){
        true -> handleClose()
        else -> Unit
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        BackTitleBar("Create virtual card") {
            handleClose()
        }

        var isSpendWalletSelectExpanded by remember {
            mutableStateOf(false)
        }

        SelectInputField(
            enabled = true,
            text = formData.value!!["spendWallet"] ?: "",
            placeHolder = "Spend wallet",
            isExpanded = isSpendWalletSelectExpanded,
            handleClose = { isSpendWalletSelectExpanded = false },
            handleOpen = { isSpendWalletSelectExpanded = !isSpendWalletSelectExpanded }
        ){
            spendWalletList.value?.data?.results?.forEachIndexed { index, spendWallet ->
                DropdownMenuItem(
                    text = { Text(text = spendWallet.wallet_name) },
                    onClick = {
                        state.onValueChanged("spendWallet", spendWallet.wallet_name)
                        state.onValueChanged("spendWalletId", spendWallet.id)
                        isSpendWalletSelectExpanded = false
                              },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
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

        LoadingButtonPrimary(
            onClick = { state.createVcn() },
            enabled = (formData.value!!["amount"] ?: "").isNotEmpty() &&
                    (formData.value!!["spendWallet"] ?: "").isNotEmpty(),
            label = "Create",
            loading = state.isLoading.value
        )
    }
}
