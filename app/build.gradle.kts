plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("app.cash.sqldelight")
}

val mosamApiKey : String by project

android {
    namespace = "com.sidharth.mosam"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sidharth.mosam"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "API_KEY", "\"$mosamApiKey\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(libs.android.ktx.core)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // lifecycle
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.activity.ktx)

    // database - SQLDelight
    implementation(libs.sqldelight.android.driver)
    implementation(libs.sqldelight.primitive.adapters)
    implementation(libs.sqldelight.coroutines.extensions)

    // networking - Ktor
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.gson)

    // di - Koin
    implementation(libs.koin.core)
    implementation(libs.koin.coroutines)
    implementation(libs.koin.android)

    // ui
    implementation(libs.kenburnsview) // ken burns effect
    implementation(libs.core.splashscreen) // splash screen
}

sqldelight {
    databases {
        create("WeatherDatabase") {
            packageName.set("com.sidharth.mosam")
        }
    }
}
