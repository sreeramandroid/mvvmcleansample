plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.rs.tmobiledemosample"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.rs.tmobiledemosample"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs = freeCompilerArgs + "-Xjvm-default=all"
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
    implementation(libs.accompanist.navigation.animation)
    implementation(libs.androidx.navigation.compose)

    //chart libraries
    implementation(libs.charts.android)
    implementation(libs.ycharts)


    // Dagger - Hilt
    implementation(libs.hilt.android)

    implementation(libs.androidx.foundation.android)
    implementation(libs.core)
    implementation(libs.androidx.datastore.core.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)
    kspAndroidTest(libs.hilt.android.compiler)

    //retrofit related libraries
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)

    //room db dependencies
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    implementation(libs.compose.material)

    //fcm push notification
    implementation(platform(libs.firebase.bom)) // Firebase BoM
    // Add Firebase Analytics using alias
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.messaging.ktx)

    //datastore preferences
    implementation(libs.datastore.preferences)

    //maps library
    implementation(libs.maps.compose)


    // runtime permissions
    implementation(libs.accompanist.permissions)

    // Coroutines test
    testImplementation(libs.coroutine.test)
    // Turbine for testing Kotlin Flows
    testImplementation(libs.turbine)
    // Mockito libraries
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.mockito.kotlin)
    // MockK
    testImplementation(libs.mockk)
    testImplementation(libs.core.testing) // For unit tests in src/test/java

    testImplementation(libs.bytebuddy)
    testImplementation(libs.bytebuddy.agent)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}