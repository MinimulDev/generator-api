import Config.loadVersion
import Deps.Core.implementations
import Deps.Core.testImplementations
import Deps.Core.testRuntimes

plugins {
    kotlin("jvm")
}

group = Config.group
version = project.loadVersion().toString()

dependencies {
    implementation(kotlin("stdlib"))

    implementations()

    testRuntimes()
    testImplementations()
}
