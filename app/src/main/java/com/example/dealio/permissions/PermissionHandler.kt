package com.example.dealio.permissions



import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
class PermissionHandler(private val context: Context, private val activity: Activity) {

    fun handlePermissionResult(
        requestCode: Int,
        grantResults: IntArray,
        permission: String,
        callback: PermissionCallback
    ) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                callback.onPermissionGranted()
            } else {
                callback.onPermissionDenied()
            }
        }
    }

    companion object {
        const val PERMISSION_REQUEST_CODE = 1001
    }

    fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri: Uri = Uri.fromParts("package", activity.packageName, null)
        intent.data = uri
        activity.startActivityForResult(intent, PERMISSION_REQUEST_CODE)
    }
}
