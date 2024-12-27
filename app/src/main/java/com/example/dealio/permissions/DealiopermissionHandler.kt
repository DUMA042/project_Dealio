package com.example.dealio.permissions

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.dealio.viewmodels.CameraResultViewModel
import javax.inject.Inject



class DealiopermissionHandler @Inject constructor(
    private val activity: ComponentActivity,
//    private val viewModel: CameraResultViewModel // You can pass other ViewModels if needed
) {


    // General method to handle permissions dynamically
    fun requestPermission(
        permission: String,
        onPermissionGranted: () -> Unit,
        onPermissionDenied: () -> Unit,
        onRationaleNeeded: () -> Unit
    ): ActivityResultLauncher<String> {
        return activity.registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                onPermissionGranted()
            } else {
                if (PermissionUtils.shouldShowRationale(activity, permission)) {
                    //will be changed
                    onRationaleNeeded()
                }
                onPermissionDenied()
            }
        }
    }
}
