plugins {
  id("com.android.application")
  id("kotlin-android")
}

android {
  compileSdk = 30
  buildToolsVersion ="30.0.3"

  defaultConfig {
    applicationId = "com.kartdroid.composecodelabs"
    minSdk = 21
    targetSdk = 30
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
    //useIR = true
  }
  buildFeatures {
    viewBinding = true
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
  }
}

dependencies {

  implementation("androidx.core:core-ktx:1.6.0")
  implementation("androidx.appcompat:appcompat:1.3.1")
  implementation("com.google.android.material:material:1.4.0")

  //Compose Libs
  val composeVersion = rootProject.extra["compose_version"] as String
  implementation("androidx.compose.foundation:foundation:$composeVersion")
  implementation("androidx.compose.ui:ui-tooling:$composeVersion")
  implementation("androidx.compose.ui:ui:$composeVersion")
  implementation("androidx.compose.material:material:$composeVersion")



  //Compose Interop Libs
  implementation("androidx.activity:activity-compose:1.3.0")

  //Compose Utils
  implementation("dev.chrisbanes.accompanist:accompanist-coil:0.6.0")

  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")

  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.3")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}