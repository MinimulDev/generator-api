import Config.loadVersion

plugins {
    kotlin("jvm")
}

group = Config.group
version = project.loadVersion().toString()

dependencies {
    implementation(kotlin("stdlib"))

    implementation(libs.bundles.sharedImplementations)
    implementation(libs.bundles.coreImplementations)

    testRuntimeOnly(libs.bundles.sharedTestRuntimes)
    testRuntimeOnly(libs.bundles.coreTestRuntimes)
    testImplementation(libs.bundles.sharedTestImplementations)
    testImplementation(libs.bundles.coreTestImplementations)
}
