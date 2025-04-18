plugins {
    alias(libs.plugins.dealio.android.library)
    alias(libs.plugins.dealio.hilt)
}

android {
    namespace = "com.example.database"
}

dependencies {
    api(projects.core.model)

    implementation(libs.kotlinx.datetime)


    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.kotlinx.coroutines.test)
}
