package com.example.dealio.permissions



import android.content.Context
import androidx.activity.result.ActivityResultLauncher


class PermissionManager(private val context: Context) {

    fun checkAndRequestPermission(
        permission: String,
        launcher: ActivityResultLauncher<String>,
        callback: PermissionCallback
    ) {
        if(PermissionUtils.isPermissionGranted(context,permission)){
            callback.onPermissionGranted()
        }
        else if (PermissionUtils.shouldShowRationale(context,permission)){
            callback.shouldShowRationale()
        }
        else {
            launcher.launch(permission)
        }
    }
}
