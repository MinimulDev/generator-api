import org.gradle.api.artifacts.dsl.DependencyHandler

object Deps {

    object Kotlin {
        @Suppress("MemberVisibilityCanBePrivate")
        const val version = "1.5.20"

        object Coroutines {
            private const val version = "1.5.0"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }
    }

    object Quarkus {
        private const val version = "2.1.3.Final"
        const val bom = "io.quarkus:quarkus-bom:$version"
        const val kotlin = "io.quarkus:quarkus-kotlin"
        const val lambda = "io.quarkus:quarkus-amazon-lambda-rest"
        const val rest = "io.quarkus:quarkus-resteasy"
        const val jackson = "io.quarkus:quarkus-resteasy-jackson"
        const val yaml = "io.quarkus:quarkus-config-yaml"

        const val client = "io.quarkus:quarkus-rest-client"
        const val clientJackson = "io.quarkus:quarkus-rest-client-jackson"

        const val junit = "io.quarkus:quarkus-junit5"
        const val restAssured = "io.rest-assured:rest-assured"
    }

    object Koin {
        private const val version = "2.2.2"
        const val core = "org.koin:koin-core:$version"
    }

    object Postgres {
        private const val version = "42.2.19"
        const val core = "org.postgresql:postgresql:$version"
    }

    object Jupiter {
        private const val version = "5.4.2"
        const val core = "org.junit.jupiter:junit-jupiter-api:$version"
        const val runtime = "org.junit.jupiter:junit-jupiter-engine:$version"
    }

    object MockK {
        private const val version = "1.10.6"
        const val core = "io.mockk:mockk:$version"
    }

    object Google {
        private const val version = "1.1.2"
        const val truth = "com.google.truth:truth:$version"
    }

    object H2 {
        private const val version = "1.4.200"
        const val core = "com.h2database:h2:$version"
    }

    object Jackson {
        const val core = "com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0-rc1"
    }

    object Ktorm {
        private const val version = "3.4.1"
        const val core = "org.ktorm:ktorm-core:$version"
        const val jackson = "org.ktorm:ktorm-jackson:$version"
    }

    private fun DependencyHandler.implementations(impls: List<String>) {
        for (impl in impls) {
            add("implementation", impl)
        }
    }

    private fun DependencyHandler.testImplementations(impls: List<String>) {
        for (impl in impls) {
            add("testImplementation", impl)
        }
    }

    private fun DependencyHandler.testRuntimes(impls: List<String>) {
        for (impl in impls) {
            add("testRuntimeOnly", impl)
        }
    }

    private val sharedImplementations = listOf(
        Koin.core
    )

    private val sharedTestImplementations = listOf(
        Jupiter.core,
        MockK.core,
        Google.truth,
        Kotlin.Coroutines.test,
        H2.core
    )

    private val sharedTestRuntimes = listOf(
        Jupiter.runtime
    )

    object Core {
        private val implementations = listOf(
            Postgres.core,
            Jackson.core,
            Ktorm.core,
            Ktorm.jackson
        )

        private val testImplementations = emptyList<String>()

        private val testRuntimes = emptyList<String>()

        fun DependencyHandler.implementations() = implementations(sharedImplementations + implementations)

        fun DependencyHandler.testRuntimes() = testRuntimes(sharedTestRuntimes + testRuntimes)

        fun DependencyHandler.testImplementations() =
            testImplementations(sharedTestImplementations + testImplementations)
    }

    object Server {
        private val implementations = listOf<String>(
            Quarkus.kotlin,
            Quarkus.lambda,
            Quarkus.rest,
            Quarkus.jackson,
            Quarkus.yaml,
            Quarkus.client,
            Quarkus.clientJackson,
            Jackson.core,
            Postgres.core,
            Ktorm.core,
            Ktorm.jackson
        )

        private val testImplementations = listOf<String>(
            Quarkus.junit,
            Quarkus.restAssured
        )

        private val testRuntimes = listOf(
            Jupiter.runtime
        )

        fun DependencyHandler.implementations() = implementations(sharedImplementations + implementations)

        fun DependencyHandler.testImplementations() =
            testImplementations(sharedTestImplementations + testImplementations)

        fun DependencyHandler.testRuntimes() = testRuntimes(sharedTestRuntimes + testRuntimes)
    }

}
