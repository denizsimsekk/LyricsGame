plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization") version "2.0.21"
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.lyricsgame"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.lyricsgame"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

val nav_version = "2.9.0"
val retrofit_version = "2.9.0"
dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation(libs.androidx.foundation.layout.android)
    implementation(libs.androidx.foundation.layout.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation("com.google.dagger:hilt-android:2.56.2")
    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.1")
    ksp("com.google.dagger:hilt-android-compiler:2.56.2")
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    implementation (libs.converter.gson)
    implementation (libs.retrofit)
    implementation ("com.google.code.gson:gson:2.13.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation ("androidx.compose.runtime:runtime-livedata:1.7.8")

    implementation("io.coil-kt:coil:2.6.0")            // for ImageView
    implementation("io.coil-kt:coil-compose:2.6.0")   // for Jetpack Compose

}