buildscript {

    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }

    dependencies {
        classpath(kotlin("gradle-plugin", version = Deps.Kotlin.version))
        classpath("org.jetbrains.kotlin:kotlin-allopen:${Deps.Kotlin.version}")
        classpath(Deps.Shadow.gradle)
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
        withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
            kotlinOptions {
                targetCompatibility = Config.javaVersion.toString()
                sourceCompatibility = Config.javaVersion.toString()
            }
        }
    }
}
