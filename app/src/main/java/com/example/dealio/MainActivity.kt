package com.example.dealio


import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import com.example.dealio.ui.theme.DealioTheme
import dagger.hilt.android.AndroidEntryPoint

import android.Manifest
import androidx.activity.result.ActivityResultLauncher

import androidx.activity.viewModels

import com.example.dealio.permissions.PermissionCallback

import com.example.dealio.uiLayout.cameraUI.ScanningUI
import com.example.dealio.viewmodels.ScannerViewModel
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val scannerViewModel:ScannerViewModel by viewModels()


    companion object {
        private const val TAG = "ttt"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()




        setContent {
            DealioTheme {




                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    val qrCodeValue by cameraResultViewModel.qrCodeValue
//                    val takephoto by cameraResultViewModel.takePhoto
//
//                    val toshowRational by  cameraResultViewModel.showRationaleDialog



 //-----------------------------------------------------------------------------------


//-----------------------------------------------------------------------------------


//-----------------------------------------------------------------------------------
                    ScanningUI(modifier = Modifier.padding(innerPadding))
//-----------------------------------------------------------------------------------

//                    if(toshowRational){
//                        ShowRationaleDialog(
//                            onDismiss = { cameraResultViewModel.updateShowRational(false) },
//                            onConfirm = {
//                                cameraResultViewModel.updateShowRational(true) // Dismiss the dialog
//                                permissionManager.checkAndRequestPermission( // Retry permission
//                                    Manifest.permission.CAMERA,
//                                    permissionLauncher,
//                                    cameraCallback
//                                )
//                            }
//                        )
//                    }


//                    Log.e(TAG, "to_show is = ($toshowRational)", )

//                    if(takephoto){
//                        CameraScreen(this)
//                    }
//
//                    if (qrCodeValue == null) {
//                        CameraPreviewWithBarcodeScanner(
//                            modifier = Modifier.padding(innerPadding),
//                            onQrCodeDetected = { rawValue ->
//                                cameraResultViewModel.updateQrCodeValue(rawValue)
//                            }
//                        )
//                    }
//                    else{
//                        QrCodeResultScreen(
//                            qrCodeValue = qrCodeValue?:"Waiting To Scan",
////                            takePhoto = {cameraResultViewModel.updateTakePhoto(true)},
//                            onRestartCamera = {
//                                permissionManager.checkAndRequestPermission(
//                                    Manifest.permission.CAMERA,
//                                    permissionLauncher,
//                                    cameraCallback
//                                )
//                            },
//                            modifier = Modifier.padding(innerPadding)
//                        )
//                    }

                }
            }
        }
    }



    override fun onDestroy() {
        super.onDestroy()
//        barcodeScanner.close()
    }
}






@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DealioTheme {
        Greeting("Android")
    }
}