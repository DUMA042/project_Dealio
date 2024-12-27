package com.example.dealio.viewmodels


import androidx.camera.view.LifecycleCameraController
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.dealio.repositories.CameraRepository
import com.example.dealio.uiStates.QrUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


@HiltViewModel
class CameraResultViewModel @Inject constructor( private val cameraRepository: CameraRepository) : ViewModel() {

    private val _permistionState = mutableStateOf<Boolean>(false)
    val permistionState: State<Boolean> get() = _permistionState



    private val _showRationaleDialog = mutableStateOf(false)
    val showRationaleDialog get() = _showRationaleDialog

    private val _qrCodeValue = mutableStateOf<String?>(null)
    val qrCodeValue: State<String?> get() = _qrCodeValue

    private val _takePhoto = mutableStateOf<Boolean>(false)
    val takePhoto: State<Boolean> get() = _takePhoto

//
//    private val _uiState = MutableStateFlow<QrUiState>(QrUiState.Loading)
//    val uiState: StateFlow<QrUiState> = _uiState
//



    fun updatePermistionState(newValue: Boolean = false){
        _permistionState.value= newValue
    }


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