plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "hu.jozsefbarat.dogsshowcase"
    compileSdk = Versions.Android.compileSdk

    defaultConfig {
        applicationId = "hu.jozsefbarat.dogsshowcase"
        minSdk = Versions.Android.minSdk
        targetSdk = Versions.Android.targetSdk
        versionCode = Versions.Android.versionCode
        versionName = Versions.Android.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.BuildTools.kotlinCompilerExtensionVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation("androidx.core:core-ktx:${Versions.AndroidX.core}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.AndroidX.lifecycle}")
    implementation("androidx.activity:activity-compose:${Versions.AndroidX.activity_compose}")
    implementation("androidx.navigation:navigation-compose:${Versions.AndroidX.nav_compose}")
    implementation("androidx.hilt:hilt-navigation-compose:${Versions.AndroidX.hilt_nav_compose}")
    implementation(platform("androidx.compose:compose-bom:${Versions.AndroidX.compose_bom}"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    implementation("io.coil-kt:coil-compose:${Versions.Coil.coil_compose}")

    implementation("com.google.dagger:hilt-android:${Versions.Dagger.hilt}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.Dagger.hilt}")

    testImplementation("junit:junit:${Versions.Test.junit}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.KotlinX.coroutines}")
    testImplementation("io.mockk:mockk:${Versions.Test.mockk}")

    androidTestImplementation("androidx.test.ext:junit:${Versions.Test.junit_ext}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.Test.espresso_core}")
    androidTestImplementation(platform("androidx.compose:compose-bom:${Versions.AndroidX.compose_bom}"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}