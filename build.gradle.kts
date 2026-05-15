plugins {
    kotlin("jvm") version "2.3.21"
    application
    id("org.jlleitschuh.gradle.ktlint") version "14.2.0"
    id("com.github.ben-manes.versions") version "0.54.0"
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

tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
    rejectVersionIf {
        listOf("alpha", "beta", "rc", "cr", "m", "preview", "dev")
            .any { candidate.version.lowercase().contains(it) }
    }
}
