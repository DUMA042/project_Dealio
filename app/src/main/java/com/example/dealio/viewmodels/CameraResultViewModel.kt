package com.example.dealio.viewmodels


import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State

import androidx.compose.runtime.mutableStateOf


@HiltViewModel
class CameraResultViewModel @Inject constructor() : ViewModel() {

    private val _showRationaleDialog = mutableStateOf(false)
    val showRationaleDialog get() = _showRationaleDialog

    private val _qrCodeValue = mutableStateOf<String?>("Waiting to Scan...pp")
    val qrCodeValue: State<String?> get() = _qrCodeValue

    fun updateShowRational(newOption: Boolean = true){
        _showRationaleDialog.value= newOption
    }

    fun updateQrCodeValue(newValue: String?) {
        _qrCodeValue.value = newValue
    }
}