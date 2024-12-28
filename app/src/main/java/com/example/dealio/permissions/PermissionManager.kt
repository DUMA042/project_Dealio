package com.example.dealio.permissions

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import javax.inject.Inject

class PermissionManager @Inject constructor(
    private val context: Context,
    private val dealiopermissionHandler: DealiopermissionHandler
) {
    private var permissionLauncher: ActivityResultLauncher<String>? = null

    fun registerPermissionLauncher(
        activity: ComponentActivity,
        permission: String,
        callback: PermissionCallback
    ) {
        permissionLauncher = dealiopermissionHandler.requestPermission(
            permission,
            onPermissionGranted = callback::onPermissionGranted,
            onPermissionDenied = callback::onPermissionDenied,
            onRationaleNeeded = callback::onShowRational
        )
    }

    fun checkAndRequestPermission(
        permission: String,
        callback: PermissionCallback
    ) {
        if (PermissionUtils.isPermissionGranted(context, permission)) {
            callback.onPermissionGranted()
        } else {
            permissionLauncher?.launch(permission)
        }
    }
}
