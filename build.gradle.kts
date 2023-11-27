plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id ("com.android.library") version "8.0.1" apply false
//    id("com.google.devtools.ksp") version("1.8.10-1.0.9")
    kotlin("jvm") version "1.9.20"
    kotlin("plugin.serialization") version "1.9.20"
    id("com.google.dagger.hilt.android") version "2.46.1" apply false
}