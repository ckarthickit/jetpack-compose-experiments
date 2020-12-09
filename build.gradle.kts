// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    //val kotlin_version by extra("1.4.21")
    repositories {
        google()
        jcenter()
    }

    dependencies {
        val buildScriptPluginsIterator = BuildScriptPlugins.values().iterator()
        //classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        while (buildScriptPluginsIterator.hasNext()) {
            classpath(buildScriptPluginsIterator.next().artifact)
        }
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", type = Delete::class) {
    delete(rootProject.buildDir)
}