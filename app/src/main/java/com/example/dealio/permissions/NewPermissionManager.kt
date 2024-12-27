package com.example.dealio.permissions

import android.content.Context
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import javax.inject.Inject

class NewPermissionManager(private val context: Context) {

    @Inject
    lateinit var dealiopermissionHandler: DealiopermissionHandler

    companion object {
        private const val TAG = "vvv"
    }


    fun launchPermissionRequest(permission: String, callback: PermissionCallback){
        dealiopermissionHandler.requestPermission(
           permission,
            onPermissionGranted = callback::onPermissionGranted,
            onPermissionDenied =callback::onPermissionDenied,
            onRationaleNeeded = callback::onShowRational
        ).launch(permission)


    }

    fun checkAndRequestPermission(
        permission: String,
        callback: PermissionCallback
    ) {
        if(PermissionUtils.isPermissionGranted(context,permission)){
           callback::onPermissionGranted
        }

        else {
            launchPermissionRequest(permission,callback)
        }
    }




}
