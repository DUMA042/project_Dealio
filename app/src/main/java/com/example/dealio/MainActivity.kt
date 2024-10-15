package com.example.dealio

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.camera.mlkit.vision.MlKitAnalyzer
import androidx.camera.view.CameraController.COORDINATE_SYSTEM_VIEW_REFERENCED
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.dealio.databinding.ActivityMainBinding
import com.example.dealio.ui.theme.DealioTheme
import dagger.hilt.android.AndroidEntryPoint
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import android.Manifest
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.dealio.QrCodeScannerProcessor.Companion
import com.example.dealio.uiLayout.QrcodeResultUI.QrCodeResultScreen
import com.example.dealio.uiLayout.cameraUI.CameraPreviewWithBarcodeScanner


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var cameraExecutor: ExecutorService
    private lateinit var barcodeScanner: BarcodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        cameraExecutor = Executors.newSingleThreadExecutor()

        setContent {
            DealioTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var qrCodeValue by remember { mutableStateOf<String?>(null) }
                    if (allPermissionsGranted()) {
                        if (qrCodeValue == null) {
                            CameraPreviewWithBarcodeScanner(
                                modifier = Modifier.padding(innerPadding),
                                onQrCodeDetected = { rawValue ->
                                    qrCodeValue = rawValue
                                }
                            )
                        } else {
                            QrCodeResultScreen(
                                qrCodeValue = qrCodeValue!!,
                                onRestartCamera = { qrCodeValue = null }
                            )
                        }
                    } else {
                        ActivityCompat.requestPermissions(
                            this@MainActivity,
                            REQUIRED_PERMISSIONS,
                            REQUEST_CODE_PERMISSIONS
                        )
                    }
                }
            }
        }
    }



    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
        barcodeScanner.close()
    }

    companion object {
        private const val TAG = "CameraX-MLKit"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                setContent {
                    DealioTheme {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            CameraPreviewWithBarcodeScanner(modifier = Modifier.padding(innerPadding)) {
                                // handle QR code scanning result here
                            }
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Permissions not granted by the user.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
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