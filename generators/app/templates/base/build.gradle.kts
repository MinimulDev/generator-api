buildscript {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
    dependencies {
        classpath(libs.bundles.root)
    }
}

allprojects {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
    tasks {
        withType<Test>().configureEach {
            useJUnitPlatform()
        }
        withType<JavaCompile>().configureEach {
            targetCompatibility = Config.javaVersion.toString()
            sourceCompatibility = Config.javaVersion.toString()
        }
        withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = Config.javaVersion.toString()
                freeCompilerArgs = freeCompilerArgs + Config.optIn.map { "-Xopt-in=$it" }
            }
        }
    }
}
