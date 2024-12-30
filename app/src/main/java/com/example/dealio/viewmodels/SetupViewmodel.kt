package com.example.dealio.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealio.uiStates.SetupUiState
import com.example.dealio.uiStates.SetupUiState.Loading
import com.example.dealio.uiStates.SetupUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SetupViewmodel @Inject constructor() : ViewModel() {

    private val dataFlow = flow {
        delay(5_000) // Simulate a 10-second delay
        emit("To do and delete") // Emit success with data
    }



    val uiState: StateFlow<SetupUiState> = dataFlow.map {
        Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = Loading,
        started = SharingStarted.WhileSubscribed(5_000),
    )


}