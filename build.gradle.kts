// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        jcenter()
    }

    dependencies {
        val buildScriptPluginsIterator = BuildScriptPlugins.values().iterator()
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