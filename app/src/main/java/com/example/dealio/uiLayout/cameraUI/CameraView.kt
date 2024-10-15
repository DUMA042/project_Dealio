package com.example.dealio.uiLayout.cameraUI




import androidx.camera.view.PreviewView
import androidx.camera.mlkit.vision.MlKitAnalyzer
import androidx.camera.view.CameraController.COORDINATE_SYSTEM_VIEW_REFERENCED
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat


@Composable
fun CameraPreviewWithBarcodeScanner(
    modifier: Modifier = Modifier,
    onQrCodeDetected: (String) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current

    val cameraController = remember {
        LifecycleCameraController(context)
    }

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { ctx ->
            PreviewView(ctx).apply {
                val options = BarcodeScannerOptions.Builder()
                    .setBarcodeFormats(com.google.mlkit.vision.barcode.common.Barcode.FORMAT_QR_CODE)
                    .build()
                val barcodeScanner = BarcodeScanning.getClient(options)

                cameraController.setImageAnalysisAnalyzer(
                    ContextCompat.getMainExecutor(ctx),
                    MlKitAnalyzer(
                        listOf(barcodeScanner),
                        COORDINATE_SYSTEM_VIEW_REFERENCED,
                        ContextCompat.getMainExecutor(ctx)
                    ) { result: MlKitAnalyzer.Result? ->
                        val barcodeResults = result?.getValue(barcodeScanner)
                        if (!barcodeResults.isNullOrEmpty()) {
                            val barcode = barcodeResults.first().rawValue
                            onQrCodeDetected(barcode ?: "")
                        }
                    }
                )

                cameraController.bindToLifecycle(lifecycleOwner)
                this.controller = cameraController
            }
        }
    )
}
