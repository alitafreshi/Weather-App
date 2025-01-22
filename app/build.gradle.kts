plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlix.serialization.plugin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android.plugin)
}

android {
    namespace = "com.tafreshiali.weatherapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.tafreshiali.weatherapp"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.bundles.compose.navigation.type.safe)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //networking
    implementation(platform(libs.okhttp.bom))
    implementation(libs.bundles.network)
    debugImplementation(libs.bundles.network.debug)

    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    //google play location services
    implementation(libs.google.play.location.services)

    //coil
    implementation(libs.bundles.compose.coil)
}

kapt {
    correctErrorTypes = true
}