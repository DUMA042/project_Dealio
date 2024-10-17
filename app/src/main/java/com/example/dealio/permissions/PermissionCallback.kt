package com.example.dealio.permissions



interface PermissionCallback {
    fun onPermissionGranted()
//    fun shouldShowRational()
    fun onPermissionDenied()
}
