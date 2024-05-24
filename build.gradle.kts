plugins {
    kotlin("jvm") version "2.0.0"
}

group = "ltd.bauhinia"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://maven.aliyun.com/repository/central")
    maven("https://maven.aliyun.com/repository/public")

    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.reflections/reflections
    implementation("org.reflections:reflections:0.10.2")
    // https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-compiler-embeddable
    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:2.0.0")

    // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
    implementation("ch.qos.logback:logback-classic:1.5.6")
    // https://mvnrepository.com/artifact/org.slf4j/slf4j-api
    implementation("org.slf4j:slf4j-api:2.0.13")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}