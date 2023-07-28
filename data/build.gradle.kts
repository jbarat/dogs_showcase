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
    implementation(project(":domain"))

    implementation("javax.inject:javax.inject:${Versions.Java.inject}")

    api("com.squareup.retrofit2:retrofit:${Versions.Network.retrofit}")
    api("com.squareup.retrofit2:converter-gson:${Versions.Network.retrofit}")
    api("com.squareup.okhttp3:logging-interceptor:${Versions.Network.okhttp}")

    testImplementation("junit:junit:${Versions.Test.junit}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.KotlinX.coroutines}")
    testImplementation("io.mockk:mockk:${Versions.Test.mockk}")
}