plugins {
    kotlin("jvm") version "1.9.23"
}

group = "ltd.bauhinia"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven("https://maven.aliyun.com/repository/central")
    maven("https://maven.aliyun.com/repository/public")
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}