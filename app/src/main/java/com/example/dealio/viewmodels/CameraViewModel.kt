package com.example.dealio.viewmodels

import androidx.camera.view.LifecycleCameraController
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealio.repositories.CameraRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CameraViewModel @Inject constructor(private val cameraRepository: CameraRepository) : ViewModel() {

    private val _permistionState = mutableStateOf<Boolean>(false)
    val permistionState: State<Boolean> get() = _permistionState



    private val _showRationaleDialog = mutableStateOf(false)
    val showRationaleDialog get() = _showRationaleDialog



    private val _takePhoto = mutableStateOf<Boolean>(false)
    val takePhoto: State<Boolean> get() = _takePhoto




    fun updatePermistionState(newValue: Boolean = false){
        _permistionState.value= newValue
    }


    fun updateTakePhoto(newOption: Boolean = true){
        _takePhoto.value= newOption
    }

    fun updateShowRational(newOption: Boolean = true){
        _showRationaleDialog.value= newOption
    }



    fun onTakePhoto(
        controller: LifecycleCameraController
    ) {
        viewModelScope.launch {
            cameraRepository.takePhoto(controller)
        }
    }
}