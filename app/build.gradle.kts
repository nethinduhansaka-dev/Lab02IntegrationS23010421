plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.s23010421.vpnhansaka"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.s23010421.vpnhansaka"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Google Maps - Core Dependencies (ESSENTIAL)
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // Optional Maps Utils (you already have these)
    implementation("com.google.maps.android:android-maps-utils:3.10.0")
    implementation("com.google.maps.android:maps-utils-ktx:5.1.1")
}
