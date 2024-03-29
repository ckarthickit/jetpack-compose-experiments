// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

  extra.apply {
    set("compose_version", "1.0.0")
  }

  repositories {
    google()
    mavenCentral()
  }
  dependencies {
    classpath("com.android.tools.build:gradle:7.0.1")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")

    // NOTE: Do not place your application dependencies here; they belong
    // in the individual module build.gradle files
  }
}

tasks.register("clean", type= Delete::class) {
  delete(rootProject.buildDir)
}