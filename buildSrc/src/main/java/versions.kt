import org.gradle.api.JavaVersion

object Versions {

    object BuildTools {
        const val buildGradle = "4.2.2"
        const val kotlinCompilerExtensionVersion = "1.4.3"
    }

    object Android {
        const val compileSdk = 33
        const val minSdk = 24
        const val targetSdk = 33
        const val versionCode = 1
        const val versionName = "1.0"
    }

    object AndroidX {
        const val core = "1.10.1"
        const val lifecycle = "2.6.1"
        const val activity_compose = "1.7.2"
        const val nav_compose = "2.6.0"
        const val hilt_nav_compose = "1.0.0"
        const val compose_bom = "2023.03.00"
    }

    object KotlinX {
        const val coroutines = "1.7.1"
    }

    object Network {
        const val okhttp = "4.9.1"
        const val retrofit = "2.9.0"
    }

    object Dagger {
        const val hilt = "2.44"
    }

    object Coil {
        const val coil_compose = "2.4.0"
    }

    object Test {
        const val mockk = "1.12.0"
        const val junit = "4.13.2"
        const val junit_ext = "1.1.5"
        const val espresso_core = "3.5.1"
    }

    object Java {
        const val inject = "1"
        const val jvmTarget = "17"
        val sourceCompatibility = JavaVersion.VERSION_17
        val targetCompatibility = JavaVersion.VERSION_17
    }
}