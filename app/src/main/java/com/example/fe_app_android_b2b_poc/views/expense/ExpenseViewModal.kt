package com.example.fe_app_android_b2b_poc.views.expense

import android.app.Application
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fe_app_android_b2b_poc.models.Category.Get.CategoryRES
import com.example.fe_app_android_b2b_poc.models.CostAccount.Get.CostAccountRES
import com.example.fe_app_android_b2b_poc.models.Expense.Details.Get.ExpenseDetailsRES
import com.example.fe_app_android_b2b_poc.models.Expense.Get.ExpensesRES
import com.example.fe_app_android_b2b_poc.models.Expense.Post.CreateExpenseREQ
import com.example.fe_app_android_b2b_poc.models.Expense.Post.SendToApproverREQ
import com.example.fe_app_android_b2b_poc.repository.CategoryRepository
import com.example.fe_app_android_b2b_poc.repository.CostAccountRepository
import com.example.fe_app_android_b2b_poc.repository.ExpenseRepository
import com.example.fe_app_android_b2b_poc.repository.ResourceRepository
import com.example.fe_app_android_b2b_poc.views.login.FormDataSet
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

var FormData = mapOf(
    "name" to "",
    "description" to "",
    "amount" to "",
    "is_reimbursement" to false,
    "category" to "",
    "categoryId" to "",
    "costAccount" to "",
    "costAccountId" to "",
    "paymentMethodId" to 3,
    "employees" to emptyList<Any>(),
    "imagesList" to emptyList<Any>(),
)

@HiltViewModel
class ExpenseViewModal @Inject constructor(
    private val expenseRepository: ExpenseRepository,
    private val categoryRepository: CategoryRepository,
    private val costAccountRepository: CostAccountRepository,
    private val resourceRepository: ResourceRepository
): ViewModel() {

    val formData = MutableLiveData(FormDataSet)

    private val _receipt: MutableState<Bitmap?> = mutableStateOf(null)
    val receipt: State<Bitmap?> get() = _receipt

    private val _imageUri: MutableState<Uri?> = mutableStateOf(null)
    val imageUri: State<Uri?> get() = _imageUri

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _isButtonLoading: MutableState<Boolean> = mutableStateOf(false)
    val isButtonLoading: State<Boolean> get() = _isButtonLoading

    private val _expenseCreated: MutableState<Boolean> = mutableStateOf(false)
    val expenseCreated: State<Boolean> get() = _expenseCreated

    private val _expensesList = MutableLiveData<ExpensesRES>()
    val expensesList: LiveData<ExpensesRES>
        get() = _expensesList

    private val _expenseDetails = MutableLiveData<ExpenseDetailsRES>()
    val expenseDetails: LiveData<ExpenseDetailsRES>
        get() = _expenseDetails

    private val _categoriesList = MutableLiveData<CategoryRES>()
    val categoriesList: LiveData<CategoryRES>
        get() = _categoriesList

    private val _costAccountsList = MutableLiveData<CostAccountRES>()
    val costAccountsList: LiveData<CostAccountRES>
        get() = _costAccountsList

    init {

    }

    // ---------------- onChange ----------------
    fun onValueChanged(name: String, value: String) {
        val temp = formData.value?.toMutableMap()
        temp?.set(name, value)
        formData.value = temp
    }

    fun handleReceiptUpdate(uri: Uri?) {
        Log.d("TEST", uri.toString())
        _imageUri.value = uri
    }

    // ================ API calls ================
    fun getExpenses(){
        viewModelScope.launch {
            _isLoading.value = true
            val response = expenseRepository.getExpenses()
            if(response != null){
                _expensesList.value = response
            }
            _isLoading.value = false
        }
    }

    fun getCategoriesList() {
        viewModelScope.launch {
            val response = categoryRepository.getCategories()
            if(response != null){
                _categoriesList.value = response
            }
        }
    }

    fun getCostAccountsList() {
        viewModelScope.launch {
            val response = costAccountRepository.getCostAccounts()
            if(response != null){
                _costAccountsList.value = response
            }
        }
    }

    fun getExpenseDetails(id: String){
        viewModelScope.launch {
            _isLoading.value = true
            val response = expenseRepository.getExpenseDetails(id)
            if(response != null){
                _expenseDetails.value = response
            }
            _isLoading.value = false
        }
    }

    fun sendToApprover(id: String) {
        viewModelScope.launch {
            _isButtonLoading.value = true
            val request = SendToApproverREQ(
                action = "Submitted",
                expense_id = id
            )
            val response = expenseRepository.sendToApprover(request);
            getExpenseDetails(id)
            _isButtonLoading.value = false
        }
    }

    private suspend fun uploadReceipts(): String?{
        _imageUri.value?.let {
            bitmapToMultipart() }?.let {
                val response = resourceRepository.uploadFiles(it)
                return response?.data?.file_path
            }

        return null
    }

    fun createExpense() {
        viewModelScope.launch {
            _isLoading.value = true
            _expenseCreated.value = false
            var receipt_path = "";
            if(_imageUri.value !== null){
                receipt_path = uploadReceipts()!!
            }
            val request = CreateExpenseREQ(
                name = formData.value?.get("name") ?: "",
                description = formData.value?.get("description") ?: "",
                amount = formData.value?.get("amount") ?: "",
                ref_category_id = formData.value?.get("categoryId") ?: "",
                ref_cost_account_id = formData.value?.get("costAccountId") ?: "",
                ref_payment_method_id = 3,
                is_reimbursement = false,
                employees = emptyList(),
                attachment_id_list = if (receipt_path !== "") listOf(receipt_path) else emptyList()
            )
            val response = expenseRepository.createExpense(request);
            _isLoading.value = false
            _expenseCreated.value = true
        }
    }

    fun bitmapToMultipart(): MultipartBody.Part {
//        val bos = ByteArrayOutputStream()
//        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 50 /*ignored for PNG*/, bos)
//        val bitmapdata = bos.toByteArray()
//        Log.i("TEST", "bitmapToMultipart: ${Base64.encodeToString(bitmapdata, Base64.NO_WRAP)}")
//
//        val file: RequestBody = bitmapdata.toRequestBody("image/jpeg".toMediaTypeOrNull(), 0, bitmapdata.size)
//        return MultipartBody.Part.createFormData("new_file", "adsf", file)

//        val stream = ByteArrayOutputStream()
//        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream)
//        val byteArray = stream.toByteArray()
//        val file: RequestBody = byteArray.toRequestBody("image/*".toMediaTypeOrNull(), 0, byteArray.size)
//        return MultipartBody.Part.createFormData("new_file", "photo", file)

        val file = File(_imageUri.value?.path!!)
        val requestFile = RequestBody.create("image/jpeg".toMediaTypeOrNull(), file)
        return MultipartBody.Part.createFormData("new_file", file.name, requestFile)
    }

}