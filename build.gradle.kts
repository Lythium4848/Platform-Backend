val kotlinVersion: String by project
val logbackVersion: String by project


plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.serialization") version "1.9.23"
}

group = "dev.lythium"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
    maven("https://oss.sonatype.org/content/repositories/ksoap2-android-releases/")
}

dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
