plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "by.drinevskiy.converter.android"
    compileSdk = 35
    defaultConfig {
        applicationId = "by.drinevskiy.converter.android"
        minSdk = 30
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    flavorDimensions += listOf("pricing")

    productFlavors {
        create("free") {
            applicationIdSuffix = ".free"
            dimension = "pricing"
//                  resValue("string","app_name","ProductFlavors-Free")
        }
        create("paid") {
            applicationIdSuffix = ".paid"
            dimension = "pricing"
            //       resValue("string", "app_name", "ProductFlavors-Paid")
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = true
//            isShrinkResources = true
            isDebuggable = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
        }
        debug {
            isMinifyEnabled = false
//            isShrinkResources = false
            isDebuggable = true
        }
//        getByName("release") {
//            isMinifyEnabled = false
//        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    viewBinding {
        enable = true
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.material)
    implementation(libs.androidx.fragment)
//    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.livedata)
//    implementation(libs.androidx.)
    debugImplementation(libs.compose.ui.tooling)
}