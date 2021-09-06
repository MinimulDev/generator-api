import Config.loadVersion
import Deps.Server.implementations
import Deps.Server.testImplementations
import Deps.Server.testRuntimes

plugins {
    kotlin("jvm")
    id("io.quarkus") version "2.1.3.Final"
    id("kotlin-allopen")
}

group = Config.group
version = project.loadVersion().toString()

dependencies {
    implementation(project(":core"))

    implementation(platform(Deps.Quarkus.bom))
    implementations()

    testRuntimes()
    testImplementations()
}

tasks {
    allOpen {
        annotation("javax.ws.rs.Path")
        annotation("javax.enterprise.context.ApplicationScoped")
        annotation("io.quarkus.test.junit.QuarkusTest")
    }
}