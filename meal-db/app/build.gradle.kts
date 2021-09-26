plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("io.gitlab.arturbosch.detekt") version Versions.DetektVersion  //Static Analysis
    id("com.diffplug.spotless") version Versions.SpotlessVersion
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
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeVersion
    }
}

detekt {
    config = files("$rootDir/config/detekt/default-detekt-config.yml")
    //Optional baseline, uncomment & run gradle command detektBaseline to exclude existing issues
    //baseline = file("detekt-baseline.xml")
}

spotless {
    //To disable adding spotless as dependency to "check" - task un-comment below code.
    //enforceCheck false
    java {
        target("**/*.java")
        googleJavaFormat("1.8").aosp().reflowLongStrings()
        setEncoding("Cp1252")  // java will have Cp1252
    }

    kotlin {
        target("**/*.kt")
        // Optional user arguments can be set as such:
        //TODO: For some reasons , ktlint is not picking up editorconfig, figureout passing editorconfig File
        ktlint("0.42.1").userData(mapOf("indent_size" to "2", "continuation_indent_size" to "4"))
        trimTrailingWhitespace()
        //replaceRegex 'Remove empty lines before end of block', '\\n[\\n]+(\\s*})(?=\\n)', '\n$1'
        //replaceRegex 'Remove trailing empty comment lines.', '\n\\s*\\*(\n\\s*\\*/\n)', '$1'
    }
}


tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    this.dependsOn("detekt")
}
/*tasks.whenTaskAdded {
    if (name.contains("assemble")) {
        this.dependsOn("detekt")
    }
}*/

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

    //Hilt for DI
    implementation(Deps.Google.Hilt.Core)
    kapt(Deps.Google.Hilt.Compiler)
    // Hilt ViewModel extension
    implementation(Deps.AndroidX.Hilt.Lifecycle)
    kapt(Deps.AndroidX.Hilt.Compiler)

    // Use the Kotlin test library.
    testImplementation(Deps.Jetbrains.KotlinTest)
    // Use the Kotlin JUnit integration.
    testImplementation(Deps.Jetbrains.KotlinTestJunit)
    testImplementation(Deps.Junit.Junit)
    androidTestImplementation(Deps.AndroidX.Test.JunitExt)
    androidTestImplementation(Deps.AndroidX.Test.EspressoCore)
}