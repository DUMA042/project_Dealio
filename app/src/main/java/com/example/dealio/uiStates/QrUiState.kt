package com.example.dealio.uiStates

sealed class QrUiState {
    data class Success(val qrCodeValue: String) : QrUiState()
    data class Error(val errorMessage: String) : QrUiState()
    object Loading : QrUiState()

}