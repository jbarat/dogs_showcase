import org.gradle.api.JavaVersion

object Versions {
    object Android{
        const val compileSdk = 33
        const val minSdk = 24
        const val targetSdk = 33
        const val versionCode = 1
        const val versionName = "1.0"
    }

    object BuildTools {
        const val buildGradle = "4.2.2"
        const val kotlinCompilerExtensionVersion = "1.4.3"
    }

    object AndroidX {
        const val core = "1.10.1"
        const val lifecycle_runtime = "2.6.1"
        const val activity_compose = "1.7.2"
        const val compose_bom = "2023.03.00"
    }

    object Test {
        const val junit = "4.13.2"
        const val junit_ext = "1.1.5"
        const val espresso_core = "3.5.1"
    }

    object Java {
        val sourceCompatibility = JavaVersion.VERSION_17
        val targetCompatibility = JavaVersion.VERSION_17
        const val jvmTarget = "17"
    }
}