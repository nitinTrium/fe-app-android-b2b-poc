package com.example.fe_app_android_b2b_poc.views.vcn

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fe_app_android_b2b_poc.models.SpendWallet.Get.SpendWalletRES
import com.example.fe_app_android_b2b_poc.models.Vcn.CreateVcnREQ
import com.example.fe_app_android_b2b_poc.models.Vcn.Details.VcnDetailsRES
import com.example.fe_app_android_b2b_poc.models.Vcn.Result
import com.example.fe_app_android_b2b_poc.repository.SpendWalletRepository
import com.example.fe_app_android_b2b_poc.repository.VcnRepository
import com.example.fe_app_android_b2b_poc.views.login.FormDataSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

var FormData = mapOf(
    "spendWallet" to "",
    "spendWalletId" to "",
    "amount" to ""
)

//
//"amount": parseFloat(formData.Amount || ""),
//"ref_spend_wallet_id": formData.SpendWalletId || "",

@HiltViewModel
class VcnViewModel @Inject constructor(
    private val vcnRepository: VcnRepository,
    private val spendWalletRepository: SpendWalletRepository
): ViewModel()  {

    val formData = MutableLiveData(FormDataSet)

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _vcnCreated: MutableState<Boolean> = mutableStateOf(false)
    val vcnCreated: State<Boolean> get() = _vcnCreated

    private val _vcnList = MutableLiveData<List<Result>>()
    val vcnList: LiveData<List<Result>>
        get() = _vcnList

    private val _vcnDetails = MutableLiveData<VcnDetailsRES>()
    val vcnDetails: LiveData<VcnDetailsRES>
        get() = _vcnDetails

    private val _spendWalletsList = MutableLiveData<SpendWalletRES>()
    val spendWalletsList: LiveData<SpendWalletRES>
        get() = _spendWalletsList

    init{
//        getSpendWallets()
    }

    // ---------------- onChange ----------------
    fun onValueChanged(name: String, value: String) {
        val temp = formData.value?.toMutableMap()
        temp?.set(name, value)
        formData.value = temp
    }

    // ================ API calls ================
    fun getVcns(){
        viewModelScope.launch {
            _isLoading.value = true
            val response = vcnRepository.getVcns()
            if(response != null){
                _vcnList.value = response
            }
            _isLoading.value = false
        }
    }

    fun getVcnDetails(id: String){
        viewModelScope.launch {
            _isLoading.value = true
            val response = vcnRepository.getVcnDetails(id)
            if(response != null){
                _vcnDetails.value = response
            }
            _isLoading.value = false
        }
    }

    fun getSpendWallets(){
        viewModelScope.launch {
            val response = spendWalletRepository.getSpendWallet()
            if(response != null){
                _spendWalletsList.value = response
            }
        }
    }

    fun createVcn() {
        viewModelScope.launch {
            _isLoading.value = true
            _vcnCreated.value = false
            val request = CreateVcnREQ(
                amount = formData.value?.get("amount")?.toInt() ?: 0,
                ref_spend_wallet_id = formData.value?.get("spendWalletId") ?: "",
                ref_vendor_id_list = listOf()
            )
            val response = vcnRepository.createVcn(request);
            _isLoading.value = false
            _vcnCreated.value = true
        }
    }
}