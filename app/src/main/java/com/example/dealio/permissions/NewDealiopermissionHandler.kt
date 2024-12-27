package com.example.dealio.permissions

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.dealio.viewmodels.CameraResultViewModel
import javax.inject.Inject

class NewDealiopermissionHandler @Inject constructor(
    private val activity: ComponentActivity,

) {


    // General method to handle permissions dynamically
    fun requestPermission(
        permission: String,
        onPermissionGranted: () -> Unit,
        onPermissionDenied: () -> Unit
    ): ActivityResultLauncher<String> {
        return activity.registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                onPermissionGranted()
            } else {
                if (PermissionUtils.shouldShowRationale(activity, permission)) {
                    //Need to do some work on the showing of Rational
                    val n=10
                }
                onPermissionDenied()
            }
        }
    }
}