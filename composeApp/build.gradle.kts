import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ktor)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.kotlin.serialization)
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.ashish.db")
        }
    }
}

kotlin {

    androidTarget {
        // Enable KSP for this target
        
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
            // For Compose on Android
            implementation(libs.koin.compose)

            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.koin.android)
            implementation(libs.koin.compose.viewmodel)

            implementation(libs.lifecycle.viewmodel)
            implementation(libs.navigation.compose)


            //sql delight
            implementation(libs.android.driver)
            // KSP processor for Android target
        }
        iosMain.dependencies {


            //sql delight
            implementation(libs.native.driver)
        }
        commonMain.dependencies {

//            implementation("app.cash.sqldelight:android-driver:2.2.1")
//            implementation("app.cash.sqldelight:coroutines-extensions:2.2.1")

            implementation("app.cash.sqldelight:runtime:2.2.1")
            implementation("app.cash.sqldelight:coroutines-extensions:2.2.1")

//            implementation(libs.koin.core)

            implementation(libs.runtime)

            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            // ✅ Koin core is MULTIPLATFORM
            api(libs.koin.core)

//            implementation(libs.ktor.server.core)
//            implementation(libs.ktor.server.netty)
//            implementation(libs.ktor.server.config.yaml)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.cio)
//            implementation(libs.logback.classic)

//            implementation(compose.runtime)
//            implementation(compose.foundation)
//            implementation(compose.material3)
//            implementation(compose.ui)
//            implementation(compose.components.resources)
//            implementation(compose.components.uiToolingPreview)
//            implementation(libs.androidx.lifecycle.viewmodelCompose)
//            implementation(libs.androidx.lifecycle.runtimeCompose)
//
//            api(libs.koin.core)
//            implementation(libs.koin.android)
            implementation(libs.koin.compose.viewmodel)
//            implementation(libs.lifecycle.viewmodel)
//            implementation(libs.navigation.compose)
        }
        commonTest.dependencies {
            implementation(libs.koin.test)
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.ashish.multiplateformapp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.ashish.multiplateformapp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

