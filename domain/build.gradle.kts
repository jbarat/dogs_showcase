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
}