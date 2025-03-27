plugins {
    alias(libs.plugins.dealio.android.feature)
    alias(libs.plugins.dealio.android.library.compose)
}

android {
    namespace = "com.example.feature.saves"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.appcompatm)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}