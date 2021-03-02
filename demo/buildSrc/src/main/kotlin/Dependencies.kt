object Versions {
    const val androidBuildToolsPlugin = "7.0.0-alpha08"
    const val kotlinPlugin = "1.4.30"

    const val composeVersion = "1.0.0-beta01" // Jetpack Compose is in Beta!

    const val timberLibraryVersion = "4.7.1"
}


enum class BuildScriptPlugins(val artifact: String) {
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
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.0-alpha07"

        object Compose {
            const val Foundation = "androidx.compose.foundation:foundation:${Versions.composeVersion}"
            const val FoundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.composeVersion}"
            const val UI = "androidx.compose.ui:ui:${Versions.composeVersion}"
            const val UITooling = "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"
            const val Material = "androidx.compose.material:material:${Versions.composeVersion}"
            const val Compiler = "androidx.compose.compiler:compiler:${Versions.composeVersion}"
            const val Runtime = "androidx.compose.runtime:runtime:${Versions.composeVersion}"

        }

        object Activity {
            const val Compose = "androidx.activity:activity-compose:1.3.0-alpha03"
        }

        // Testing Libraries
        const val junitExt = "androidx.test.ext:junit:1.1.2"
        const val espressoExt = "androidx.test.espresso:espresso-core:3.3.0"
    }

    object Junit {
        const val junit4 = "junit:junit:4.13"

    }
}