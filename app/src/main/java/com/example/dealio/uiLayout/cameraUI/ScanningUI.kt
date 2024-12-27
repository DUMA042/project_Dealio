package com.example.dealio.uiLayout.cameraUI



import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dealio.permissions.NewPermissionManager
import com.example.dealio.permissions.PermissionCallback
import com.example.dealio.permissions.ShowRationaleDialog
import com.example.dealio.uiLayout.QrcodeResultUI.newQrCodeResultScreen
import com.example.dealio.viewmodels.CameraResultViewModel

@Composable
fun ScanningUI(permissionManager: NewPermissionManager, callback: PermissionCallback, cameraResultViewModel: CameraResultViewModel=viewModel()){
    //If this is where the viewmodel will be built then the callback has to be build in this layer.
    val context = LocalContext.current
    val permission = android.Manifest.permission.CAMERA

    val toShowrational by  cameraResultViewModel.showRationaleDialog
    val showQRcode by cameraResultViewModel.qrCodeValue
    val permistionState by cameraResultViewModel.permistionState

/**Check if code would enforce the user to grant the permission at any point in time before letting them use the camera
 (May have been solve by having the "  if(showQRcode!=null){")**/

    LaunchedEffect(Unit) {
        permissionManager.checkAndRequestPermission(
            permission,
            callback
        )
    }




    if(toShowrational){
        ShowRationaleDialog(
            onDismiss = { cameraResultViewModel.updateShowRational(false) },
            onConfirm = {
                cameraResultViewModel.updateShowRational(true) // Dismiss the dialog
                permissionManager.checkAndRequestPermission(
                    permission,
                    callback
                )
            }
        )

    }

    //Be very carefull to manage the state
    if(permistionState){
        CameraPreviewWithBarcodeScanner(onQrCodeDetected = {cameraResultViewModel.updateQrCodeValue(it)})
    }else{
    // newQrCodeResultScreen(showQRcode?:"Waiting To Scan",,)


    }




}