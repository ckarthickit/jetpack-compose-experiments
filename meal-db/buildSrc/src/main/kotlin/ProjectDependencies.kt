object Versions {
    const val androidBuildToolsPlugin = "7.0.2"
    const val kotlinPlugin = "1.5.21"

    const val composeVersion = "1.0.0"
    const val timberLibraryVersion = "4.7.1"
}

enum class BuildScriptPlugins(val artifact: String) {
    ANDROID_BUILD_TOOLS("com.android.tools.build:gradle:${Versions.androidBuildToolsPlugin}"),
    KOTLIN("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinPlugin}"),
}

object Deps {
    object Google {
        val Material = "com.google.android.material:material:1.4.0"
    }
    object AndroidX {

        const val CoreKTX = "androidx.core:core-ktx:1.6.0"
        const val AppCompat = "androidx.appcompat:appcompat:1.3.1"

        object Compose {
            const val Foundation = "androidx.compose.foundation:foundation:${Versions.composeVersion}"
            const val UITooling = "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"
            const val UI = "androidx.compose.ui:ui:${Versions.composeVersion}"
            const val MaterialUI = "androidx.compose.material:material:${Versions.composeVersion}"

            //Compose  Runtime  => has getValue , setValue delegates and mutableStateOf APIs
            const val Runtime = "androidx.compose.runtime:runtime:${Versions.composeVersion}"
            //Compose Runtime LiveDate => Has observeAsState
            const val RuntimeLiveData = "androidx.compose.runtime:runtime-livedata:${Versions.composeVersion}"
        }

        object Test {
            const val JunitExt = "androidx.test.ext:junit:1.1.3"
            const val EspressoCore = "androidx.test.espresso:espresso-core:3.4.0"
        }

    }
    object Junit {
        const val Junit = "junit:junit:4.13.2"
    }
}

