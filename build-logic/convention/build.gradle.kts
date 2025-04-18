import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
    alias(libs.plugins.android.lint)
}

group = "com.example.project_Dealio.build_logic.convention"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    lintChecks(libs.androidx.lint.gradle)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = libs.plugins.dealio.android.application.compose.get().pluginId
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = libs.plugins.dealio.android.application.asProvider().get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = libs.plugins.dealio.android.library.compose.get().pluginId
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = libs.plugins.dealio.android.library.asProvider().get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidFeature") {
            id = libs.plugins.dealio.android.feature.get().pluginId
            implementationClass = "AndroidFeatureConventionPlugin"
        }

        register("hilt") {
            id = libs.plugins.dealio.hilt.get().pluginId
            implementationClass = "HiltConventionPlugin"
        }

        register("jvmLibrary") {
            id = libs.plugins.dealio.jvm.library.get().pluginId
            implementationClass = "JvmLibraryConventionPlugin"
        }


    }
}


