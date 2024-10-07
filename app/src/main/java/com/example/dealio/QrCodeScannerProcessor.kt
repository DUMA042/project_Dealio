package com.example.dealio

import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.lifecycle.LifecycleOwner
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage


class QrCodeScannerProcessor(
    private val lifecycleOwner: LifecycleOwner
): ImageAnalysis.Analyzer {

    private val options =
        BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_QR_CODE, Barcode.FORMAT_AZTEC)
            .build()

    private var scanner: BarcodeScanner? = BarcodeScanning.getClient(options)
    private var isProcessingCode = false


    fun detectInImage(image: InputImage): Task<List<Barcode>>? {
        return scanner?.process(image)
    }


    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        if (!isProcessingCode) {
            val mediaImage = imageProxy.image
            mediaImage?.let {
               isProcessingCode = true
                detectInImage(
                    InputImage.fromMediaImage(
                        it,
                        imageProxy.imageInfo.rotationDegrees
                    )
                )
                    ?.addOnSuccessListener {results ->
                        onSuccess(results)
                    }
                    ?.addOnFailureListener { e->
                        onFailure(e)
                    }
                    ?.addOnCompleteListener {
                        imageProxy.image?.close()
                        imageProxy.close()
                    }
            }
        }
    }


   fun onSuccess(barcodes: List<Barcode>) {


       // Task completed successfully
       // [START_EXCLUDE]
       // [START get_barcodes]
       for (barcode in barcodes) {
           val bounds = barcode.boundingBox
           val corners = barcode.cornerPoints

           val rawValue = barcode.rawValue

           val valueType = barcode.valueType
           // See API reference for complete list of supported types
           Log.e(TAG, "Barcode detection ok! ($valueType)", )

       }


    }

     fun onFailure(e: Exception) {
        Log.e(TAG, "Barcode detection failed!", e)
    }



    companion object {
        private const val TAG = "BarcodeProcessor"
    }





}