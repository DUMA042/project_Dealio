package com.example.dealio.di

import com.example.dealio.repositories.CameraRepository
import com.example.dealio.repositories.ImplCameraRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CameraRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCameraRepository(
        cameraRepositoryImpl: ImplCameraRepository
    ): CameraRepository

}