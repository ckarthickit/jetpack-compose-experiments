object Versions {
    const val androidBuildToolsPlugin = "4.2.0-alpha10"
    const val kotlinPlugin = "1.4.0"

    const val composeVersion = "1.0.0-alpha02"

    const val timberLibraryVersion = "4.7.1"
}


enum class BuildScriptPlugins private constructor(val artifact: String) {
    ANDROID_BUILD_TOOLS("com.android.tools.build:gradle:${Versions.androidBuildToolsPlugin}"),
    KOTLIN("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinPlugin}"),
}

object Deps {
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinPlugin}"
    const val googleMaterialDesign = "com.google.android.material:material:1.2.1"
    const val timberLib = "com.jakewharton.timber:timber:${Versions.timberLibraryVersion}"

    object AndroidX {
        const val coreKotlinExtensions = "androidx.core:core-ktx:1.3.1"
        const val appCompat = "androidx.appcompat:appcompat:1.2.0"
        const val contraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.0-alpha07"

        const val composeUI = "androidx.compose.ui:ui:${Versions.composeVersion}"
        const val composeMaterial = "androidx.compose.material:material:${Versions.composeVersion}"
        const val uiTooling = "androidx.ui:ui-tooling:${Versions.composeVersion}"

        // Testing Libraries
        const val junitExt = "androidx.test.ext:junit:1.1.2"
        const val espressoExt = "androidx.test.espresso:espresso-core:3.3.0"
    }

    object Junit {
        const val junit4 = "junit:junit:4.13"

    }
}