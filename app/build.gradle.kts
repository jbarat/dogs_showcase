plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
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
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.lifecycle_runtime}")
    implementation("androidx.activity:activity-compose:${Versions.AndroidX.activity_compose}")
    implementation(platform("androidx.compose:compose-bom:${Versions.AndroidX.compose_bom}"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    testImplementation("junit:junit:${Versions.Test.junit}")

    androidTestImplementation("androidx.test.ext:junit:${Versions.Test.junit_ext}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.Test.espresso_core}")
    androidTestImplementation(platform("androidx.compose:compose-bom:${Versions.AndroidX.compose_bom}"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}