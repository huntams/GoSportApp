// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navVersion}")
        classpath("com.android.tools.build:gradle:8.1.0")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}")
    }
}
plugins {
    id ("com.android.library") version "7.4.2" apply false
    id ("org.jetbrains.kotlin.plugin.serialization") version "1.8.10" apply false
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id ("com.google.dagger.hilt.android") version "${Versions.hilt}" apply false
}