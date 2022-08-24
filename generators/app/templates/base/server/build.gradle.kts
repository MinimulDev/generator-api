import Config.loadVersion

plugins {
    kotlin("jvm")
    id("kotlin-allopen")
    alias(libs.plugins.quarkusPlugin)
    alias(libs.plugins.jandexPlugin)
}

group = Config.group
version = project.loadVersion().toString()

dependencies {
    implementation(project(":core"))

    implementation(platform(libs.quarkusBom))
    implementation(libs.bundles.sharedImplementations)
    implementation(libs.bundles.serverImplementations)

    testRuntimeOnly(libs.bundles.sharedTestRuntimes)
    testRuntimeOnly(libs.bundles.serverTestRuntimes)
    testImplementation(libs.bundles.sharedTestImplementations)
    testImplementation(libs.bundles.serverTestImplementations)
}

tasks {
    allOpen {
        annotation("javax.ws.rs.Path")
        annotation("javax.enterprise.context.ApplicationScoped")
        annotation("io.quarkus.test.junit.QuarkusTest")
    }
}