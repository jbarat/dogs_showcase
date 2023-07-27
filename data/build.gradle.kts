plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "hu.jozsefbarat.data"
    compileSdk = Versions.Android.compileSdk

    defaultConfig {
        minSdk = Versions.Android.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    compileOptions {
        sourceCompatibility = Versions.Java.sourceCompatibility
        targetCompatibility = Versions.Java.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = Versions.Java.jvmTarget
    }
}

dependencies {
    testImplementation("junit:junit:${Versions.Test.junit}")
    androidTestImplementation("androidx.test.ext:junit:${Versions.Test.junit_ext}")
}