package com.example.dealio.uiStates

sealed class SetupUiState {
    data class Success(val temp: String) : SetupUiState()
//    data class Error(val errorMessage: String) : SetupUiState()
    object Loading : SetupUiState()



}