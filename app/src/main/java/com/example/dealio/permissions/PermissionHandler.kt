package com.example.dealio.permissions

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.dealio.viewmodels.CameraResultViewModel

class PermissionHandler(
    private val activity: ComponentActivity,
    private val viewModel: CameraResultViewModel // You can pass other ViewModels if needed
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
                    viewModel.updateShowRational(true)
                }
                onPermissionDenied()
            }
        }
    }
}
