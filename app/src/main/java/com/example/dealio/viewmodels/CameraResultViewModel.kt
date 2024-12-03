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
class CameraResultViewModel @Inject constructor( private val cameraRepository: CameraRepository) : ViewModel() {

    private val _showRationaleDialog = mutableStateOf(false)
    val showRationaleDialog get() = _showRationaleDialog

    private val _qrCodeValue = mutableStateOf<String?>("Waiting to Scan...pp")
    val qrCodeValue: State<String?> get() = _qrCodeValue

    private val _takePhoto = mutableStateOf<Boolean>(false)
    val takePhoto: State<Boolean> get() = _takePhoto

    fun updateTakePhoto(newOption: Boolean = true){
        _takePhoto.value= newOption
    }

    fun updateShowRational(newOption: Boolean = true){
        _showRationaleDialog.value= newOption
    }

    fun updateQrCodeValue(newValue: String?) {
        _qrCodeValue.value = newValue
    }


    fun onTakePhoto(
        controller: LifecycleCameraController
    ) {
        viewModelScope.launch {
            cameraRepository.takePhoto(controller)
        }
    }
}