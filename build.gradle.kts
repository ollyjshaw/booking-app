plugins {
    kotlin("jvm") version "2.1.20"
    application
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.javalin:javalin-bundle:7.2.2")
    testImplementation("io.javalin:javalin-testtools:7.2.2")
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("app.MainKt")
}

tasks.test {
    useJUnitPlatform()
}
