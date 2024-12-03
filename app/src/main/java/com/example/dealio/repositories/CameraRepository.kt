package com.example.dealio.repositories

import android.graphics.Bitmap
import androidx.camera.view.LifecycleCameraController

interface CameraRepository {

    suspend fun takePhoto(controller: LifecycleCameraController)

    suspend fun savePhoto(bitmap: Bitmap)

}