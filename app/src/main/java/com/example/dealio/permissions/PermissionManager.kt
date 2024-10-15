package com.example.dealio.permissions



import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat

class PermissionManager(private val context: Context) {

    fun checkAndRequestPermission(
        permission: String,
        launcher: ActivityResultLauncher<String>,
        callback: PermissionCallback
    ) {
        if (ActivityCompat.checkSelfPermission(
                context,
                permission
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED
        ) {
            callback.onPermissionGranted()
        } else {
            launcher.launch(permission)
        }
    }
}
