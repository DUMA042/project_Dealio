package com.example.dealio.permissions



import android.content.Context
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import com.example.dealio.MainActivity
import com.example.dealio.MainActivity.Companion


class PermissionManager(private val context: Context) {


    companion object {
        private const val TAG = "vvv"
    }


    fun checkAndRequestPermission(
        permission: String,
        launcher: ActivityResultLauncher<String>,
        callback: PermissionCallback
    ) {
        if(PermissionUtils.isPermissionGranted(context,permission)){
            callback.onPermissionGranted()
        }

        else {
            launcher.launch(permission)
        }
    }
}

