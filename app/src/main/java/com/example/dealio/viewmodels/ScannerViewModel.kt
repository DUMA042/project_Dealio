package com.example.dealio.viewmodels


import androidx.camera.view.LifecycleCameraController
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.dealio.repositories.CameraRepository
import kotlinx.coroutines.launch


@HiltViewModel
class ScannerViewModel @Inject constructor() : ViewModel() {

    private val _permistionState = mutableStateOf<Boolean>(false)
    val permistionState: State<Boolean> get() = _permistionState



    private val _showRationaleDialog = mutableStateOf(false)
    val showRationaleDialog get() = _showRationaleDialog

    private val _qrCodeValue = mutableStateOf<String?>(null)
    val qrCodeValue: State<String?> get() = _qrCodeValue




    fun updatePermistionState(newValue: Boolean = false){
        _permistionState.value= newValue
    }


    fun updateShowRational(newOption: Boolean = true){
        _showRationaleDialog.value= newOption
    }

    fun updateQrCodeValue(newValue: String?) {
        _qrCodeValue.value = newValue
    }
    

}