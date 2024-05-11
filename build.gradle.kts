plugins {
    kotlin("jvm") version "1.9.23"
}

group = "ltd.bauhinia"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://maven.aliyun.com/repository/central")
    maven("https://maven.aliyun.com/repository/public")

    mavenCentral()
}

dependencies {

    // https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-compiler-embeddable
    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:1.9.24")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}