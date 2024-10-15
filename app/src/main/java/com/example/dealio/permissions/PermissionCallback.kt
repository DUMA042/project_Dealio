package com.example.dealio.permissions



interface PermissionCallback {
    fun onPermissionGranted()
    fun shouldShowRationale()
    fun onPermissionDenied()
}
