plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
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
            this.isMinifyEnabled = false
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeVersion
    }
}

dependencies {

    implementation(Deps.AndroidX.CoreKTX)
    implementation(Deps.AndroidX.AppCompat)

    implementation(Deps.Google.Material)

    //Use Jetpack Compose
    implementation(Deps.AndroidX.Compose.Foundation)
    implementation(Deps.AndroidX.Compose.UITooling)
    implementation(Deps.AndroidX.Compose.UI)
    implementation(Deps.AndroidX.Compose.MaterialUI)
    implementation(Deps.AndroidX.Compose.Runtime)
    implementation(Deps.AndroidX.Compose.RuntimeLiveData)
    implementation(Deps.AndroidX.Activity.Compose)

    // Use Kotlin Coroutines
    implementation(Deps.Jetbrains.Coroutines)

    //Use Retrofit for NW Calls
    implementation(Deps.Square.Retrofit.Core)
    //Retrofit Converters
    implementation(Deps.Square.Retrofit.ConverterScalars)
    implementation(Deps.Square.Retrofit.ConverterMoshi)
    //implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    implementation(Deps.Square.Retrofit.Mock)

    //OkHttp and it's interceptors
    implementation(Deps.Square.OkHttp.Core)
    implementation(Deps.Square.OkHttp.LoggingInterceptor)
    implementation(Deps.Square.OkHttp.MockWebServer)

    //Use Moshi for Serialization / De-Serialization

    implementation(Deps.Square.Moshi.Core)
    implementation(Deps.Square.Moshi.Adapters)
    kapt(Deps.Square.Moshi.KotlinCodeGen)

    // Use the Kotlin test library.
    testImplementation(Deps.Jetbrains.KotlinTest)
    // Use the Kotlin JUnit integration.
    testImplementation(Deps.Jetbrains.KotlinTestJunit)
    testImplementation(Deps.Junit.Junit)
    androidTestImplementation(Deps.AndroidX.Test.JunitExt)
    androidTestImplementation(Deps.AndroidX.Test.EspressoCore)
}