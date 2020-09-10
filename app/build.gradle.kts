plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdkVersion(29)
    buildToolsVersion("30.0.2")

    defaultConfig {
        applicationId = "com.kartdroid.jetpackcomposeexp"
        minSdkVersion(21)
        targetSdkVersion(29)
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
        kotlinCompilerVersion = Versions.kotlinPlugin
    }
}

dependencies {

    implementation(Deps.kotlinStdLib)
    implementation(Deps.AndroidX.coreKotlinExtensions)
    implementation(Deps.AndroidX.appCompat)
    implementation(Deps.AndroidX.composeUI)
    implementation(Deps.AndroidX.composeMaterial)
    implementation(Deps.AndroidX.uiTooling)

    implementation(Deps.googleMaterialDesign)
    implementation(Deps.AndroidX.lifecycle)
    testImplementation(Deps.Junit.junit4)
    androidTestImplementation(Deps.AndroidX.junitExt)
    androidTestImplementation(Deps.AndroidX.espressoExt)
}