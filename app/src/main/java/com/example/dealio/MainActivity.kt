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
import com.google.mlkit.vision.barcode.BarcodeScanner

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import android.Manifest
import android.util.Log
import androidx.activity.result.ActivityResultLauncher

import androidx.activity.viewModels

import androidx.compose.runtime.getValue

import com.example.dealio.permissions.PermissionCallback
import com.example.dealio.permissions.DealiopermissionHandler
import com.example.dealio.permissions.PermissionManager

import com.example.dealio.permissions.ShowRationaleDialog
import com.example.dealio.uiLayout.QrcodeResultUI.QrCodeResultScreen
import com.example.dealio.uiLayout.cameraUI.CameraPreviewWithBarcodeScanner
import com.example.dealio.uiLayout.cameraUI.CameraScreen
import com.example.dealio.viewmodels.CameraResultViewModel
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val cameraResultViewModel:CameraResultViewModel by viewModels()


    private lateinit var permissionManager: PermissionManager

    @Inject
     lateinit var dealiopermissionHandler: DealiopermissionHandler

    private lateinit var permissionLauncher: ActivityResultLauncher<String>

    companion object {
        private const val TAG = "ttt"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()




        permissionManager = PermissionManager(this)

        permissionLauncher = dealiopermissionHandler.requestPermission(
            Manifest.permission.CAMERA,
            onPermissionGranted = {
                Toast.makeText(this, "Camera permission granted.", Toast.LENGTH_SHORT).show()
                cameraResultViewModel.updateQrCodeValue(null)
            },
            onPermissionDenied = {
                Toast.makeText(this, "Camera permission not granted.", Toast.LENGTH_SHORT).show()
            },
            onRationaleNeeded = {
                cameraResultViewModel.updateShowRational(true)
            }
        )



        setContent {
            DealioTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val qrCodeValue by cameraResultViewModel.qrCodeValue
                    val takephoto by cameraResultViewModel.takePhoto

                    val toshowRational by  cameraResultViewModel.showRationaleDialog



 //-----------------------------------------------------------------------------------

                    val cameraCallback=object:PermissionCallback {
                        override fun onPermissionGranted() {
                            cameraResultViewModel.updatePermistionState(true)
                        }

                        override fun onPermissionDenied() {
                            Toast.makeText(
                                this@MainActivity,
                                "Camera permission denied.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun onShowRational() {
                            cameraResultViewModel.updateShowRational(true)
                        }
                    }
//-----------------------------------------------------------------------------------

                    if(toshowRational){
                        ShowRationaleDialog(
                            onDismiss = { cameraResultViewModel.updateShowRational(false) },
                            onConfirm = {
                                cameraResultViewModel.updateShowRational(true) // Dismiss the dialog
                                permissionManager.checkAndRequestPermission( // Retry permission
                                    Manifest.permission.CAMERA,
                                    permissionLauncher,
                                    cameraCallback
                                )
                            }
                        )
                    }


                    Log.e(TAG, "to_show is = ($toshowRational)", )

                    if(takephoto){
                        CameraScreen(this)
                    }

                    if (qrCodeValue == null) {
                        CameraPreviewWithBarcodeScanner(
                            modifier = Modifier.padding(innerPadding),
                            onQrCodeDetected = { rawValue ->
                                cameraResultViewModel.updateQrCodeValue(rawValue)
                            }
                        )
                    }
                    else{
                        QrCodeResultScreen(
                            qrCodeValue = qrCodeValue?:"Waiting To Scan",
                            takePhoto = {cameraResultViewModel.updateTakePhoto(true)},
                            onRestartCamera = {
                                permissionManager.checkAndRequestPermission(
                                    Manifest.permission.CAMERA,
                                    permissionLauncher,
                                    cameraCallback
                                )
                            },
                            modifier = Modifier.padding(innerPadding)
                        )
                    }

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