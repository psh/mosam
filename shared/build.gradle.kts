plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // database - SQLDelight
                api(libs.sqldelight.primitive.adapters)
                api(libs.sqldelight.coroutines.extensions)

                // networking - Ktor
                api(libs.ktor.client.core)

                // di - Koin
                api(libs.koin.core)
                api(libs.koin.coroutines)

                // logging
                api(libs.kermit)
                api(libs.kermit.koin)
            }
        }

        val androidMain by getting {
            dependencies {
                // networking - Ktor Okhttp engine
                api(libs.gson)
                api(libs.ktor.client.okhttp)
                api(libs.logging.interceptor)

                // database - SQLDelight
                api(libs.sqldelight.android.driver)

                // di - Koin
                api(libs.koin.android)
            }
        }
    }
}

android {
    namespace = "com.sidharth.mosam.shared"
    compileSdk = 34
    defaultConfig {
        minSdk = 26
    }
}
