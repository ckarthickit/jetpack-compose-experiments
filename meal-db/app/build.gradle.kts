plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.trade.composemealdb"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            this.isMinifyEnabled =  false
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
        useIR = true
    }
}

dependencies {
    implementation(Deps.AndroidX.CoreKTX)
    implementation(Deps.AndroidX.AppCompat)

    implementation(Deps.Google.Material)

    implementation(Deps.AndroidX.Compose.Foundation)
    implementation(Deps.AndroidX.Compose.UITooling)
    implementation(Deps.AndroidX.Compose.UI)
    implementation(Deps.AndroidX.Compose.MaterialUI)
    implementation(Deps.AndroidX.Compose.Runtime)
    implementation(Deps.AndroidX.Compose.RuntimeLiveData)

    testImplementation(Deps.Junit.Junit)
    androidTestImplementation(Deps.AndroidX.Test.JunitExt)
    androidTestImplementation(Deps.AndroidX.Test.EspressoCore)
}