package com.example.dealio.di

import android.content.Context
import androidx.activity.ComponentActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object PermissionModule {

    @Provides
    fun provideComponentActivity(@ActivityContext context: Context): ComponentActivity {
        return context as ComponentActivity
    }




}
