object Versions {
    const val androidBuildToolsPlugin = "7.0.2"
    const val kotlinVersion = "1.5.10"

    const val coroutinesVersion = "1.5.1"
    const val composeVersion = "1.0.0"
    const val retrofitVersion = "2.9.0"
    const val okHttpVersion = "4.3.1"
    const val moshiVersion = "1.12.0"

    const val androidXLifecycle = "2.2.0"

    const val hilt = "2.38.1"
    const val hiltLifecycle = "1.0.0"

    const val timberLibraryVersion = "4.7.1"

    const val DetektVersion = "1.18.1"
    const val SpotlessVersion = "5.15.1"
}

enum class BuildScriptPlugins(val artifact: String) {
    ANDROID_BUILD_TOOLS("com.android.tools.build:gradle:${Versions.androidBuildToolsPlugin}"),
    KOTLIN("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"),
    HILT("com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"),
}

object Deps {
    object Google {
        const val Material = "com.google.android.material:material:1.4.0"

        object Hilt {
            const val Core = "com.google.dagger:hilt-android:${Versions.hilt}"
            const val Compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
        }
    }

    object Jetbrains {
        const val Coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
        const val KotlinTest = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlinVersion}"
        const val KotlinTestJunit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlinVersion}"
    }

    object Square {

        object OkHttp {
            const val Core = "com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}"
            const val LoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}"
            const val MockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttpVersion}"
        }
        object Retrofit {
            const val Core = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
            //Retrofit Converters
            const val ConverterScalars = "com.squareup.retrofit2:converter-scalars:${Versions.retrofitVersion}"
            const val ConverterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofitVersion}"
            //const val Retrofit = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
            const val Mock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofitVersion}"
        }
        object Moshi {
            const val Core = "com.squareup.moshi:moshi:${Versions.moshiVersion}"
            const val Adapters = "com.squareup.moshi:moshi-adapters:${Versions.moshiVersion}"
            const val KotlinCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshiVersion}"
        }
    }

    object AndroidX {

        const val CoreKTX = "androidx.core:core-ktx:1.6.0"
        const val AppCompat = "androidx.appcompat:appcompat:1.3.1"

        object Hilt {
            //Hilt Extensions
            const val Lifecycle = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
            const val Compiler = "androidx.hilt:hilt-compiler:${Versions.hiltLifecycle}"
        }

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

        object Activity {
            const val Compose = "androidx.activity:activity-compose:1.3.0"
        }

        object Lifecycle {
            val viewModelKTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidXLifecycle}"
            val liveDataKTX = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidXLifecycle}"
        }
        const val fragmentKTX = "androidx.fragment:fragment-ktx:1.3.5"

        object Test {
            const val JunitExt = "androidx.test.ext:junit:1.1.3"
            const val EspressoCore = "androidx.test.espresso:espresso-core:3.4.0"
        }

    }

    object Junit {
        const val Junit = "junit:junit:4.13.2"
    }
}

