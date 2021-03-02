plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.2")

    defaultConfig {
        applicationId = "com.kartdroid.jetpackcomposeexp"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeVersion
        //kotlinCompilerVersion = Versions.kotlinPlugin
    }
}

dependencies {

    implementation(Deps.kotlinStdLib)
    implementation(Deps.AndroidX.coreKotlinExtensions)
    implementation(Deps.AndroidX.appCompat)

    implementation(Deps.AndroidX.Compose.Foundation)
    implementation(Deps.AndroidX.Compose.UI)
    implementation(Deps.AndroidX.Compose.UITooling)
    implementation(Deps.AndroidX.Compose.Material)
    implementation(Deps.AndroidX.Compose.Runtime)
    //implementation(Deps.AndroidX.Compose.Compiler)

    implementation(Deps.AndroidX.Activity.Compose)

    implementation(Deps.googleMaterialDesign)
    implementation(Deps.AndroidX.lifecycle)
    testImplementation(Deps.Junit.junit4)
    androidTestImplementation(Deps.AndroidX.junitExt)
    androidTestImplementation(Deps.AndroidX.espressoExt)
}