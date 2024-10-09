plugins {
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.secrets.gradle.plugin)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.compose.compiler)







}

android {
    namespace = "com.example.dealio"
    compileSdk = 34

    buildFeatures {
        buildConfig=true
    }

    defaultConfig {
        applicationId = "com.example.dealio"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }


    }

    buildTypes {
        debug { buildConfigField("String", "Namet", "\"It is working\"")}

        release {
            buildConfigField("String", "Namet", "\"It is working\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    //Hilt ff
    implementation(libs.hilt.android)

    //ksp
    ksp(libs.hilt.android.compiler)

    //OKHttp
    implementation(platform(libs.okhttpBom))
    implementation(libs.okhttp)
    implementation(libs.loggingInterceptor)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converterGson)

    // Moshi
    implementation(libs.moshi)
    implementation(libs.converterMoshi)
    implementation(libs.moshiKotlin)
    ksp(libs.moshiKotlinCodegen)



    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}