package com.example.dealio.uiLayout.cameraUI



import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dealio.permissions.PermissionManager
import com.example.dealio.permissions.PermissionCallback
import com.example.dealio.permissions.ShowRationaleDialog
import com.example.dealio.uiLayout.QrcodeResultUI.NewQrCodeResultScreen
import com.example.dealio.uiLayout.QrcodeResultUI.QrCodeResultScreen
import com.example.dealio.viewmodels.ScannerViewModel


@Composable
fun ScanningUI(permissionManager: PermissionManager, callback: PermissionCallback, scannerViewModel: ScannerViewModel=viewModel(), modifier: Modifier=Modifier){
    //If this is where the viewmodel will be built then the callback has to be build in this layer.
    val context = LocalContext.current
    val permission = android.Manifest.permission.CAMERA

    val toShowrational by  scannerViewModel.showRationaleDialog
    val showQRcode by scannerViewModel.qrCodeValue

    val permistionState by scannerViewModel.permistionState

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
            onDismiss = { scannerViewModel.updateShowRational(false) },
            onConfirm = {
                scannerViewModel.updateShowRational(false) // Dismiss the dialog
                permissionManager.checkAndRequestPermission(
                    permission,
                    callback
                )
            }
        )

    }

    //Be very carefull to manage the state
    if(permistionState){
        if(showQRcode==null)
        CameraPreviewWithBarcodeScanner(onQrCodeDetected = {scannerViewModel.updateQrCodeValue(it)})
        else{
            QrCodeResultScreen(showQRcode?:"Waiting To Scan",{scannerViewModel.updateQrCodeValue(null)})
        }
    }else{
     NewQrCodeResultScreen(showQRcode?:"Waiting To Scan")


    }




}