// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    alias(libs.plugins.android.application) apply false
    // Safe Args: generates classes for passing data between screens[cite: 74]
    id("androidx.navigation.safeargs.kotlin") version "2.9.8" apply false
    id("com.google.devtools.ksp") version "2.2.10-2.0.2"
}