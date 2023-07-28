plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = Versions.Java.sourceCompatibility
    targetCompatibility = Versions.Java.targetCompatibility
}

dependencies {
    implementation("javax.inject:javax.inject:${Versions.Java.inject}")

    testImplementation("junit:junit:${Versions.Test.junit}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.KotlinX.coroutines}")
    testImplementation("io.mockk:mockk:${Versions.Test.mockk}")
}