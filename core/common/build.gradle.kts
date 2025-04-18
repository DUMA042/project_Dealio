plugins {
    alias(libs.plugins.dealio.jvm.library)
    alias(libs.plugins.dealio.hilt)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}