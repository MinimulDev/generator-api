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

    object Shadow {
        private const val version = "7.0.0"
        const val gradle = "gradle.plugin.com.github.jengelman.gradle.plugins:shadow:$version"
    }

    object Quarkus {
        private const val version = "2.1.3.Final"
        const val bom = "io.quarkus:quarkus-bom:$version"
        const val kotlin = "io.quarkus:quarkus-kotlin"
        const val lambda = "io.quarkus:quarkus-amazon-lambda-rest"
        const val rest = "io.quarkus:quarkus-resteasy"
        const val jackson = "io.quarkus:quarkus-resteasy-jackson"

        const val junit = "io.quarkus:quarkus-junit5"
        const val restAssured = "io.rest-assured:rest-assured"
    }

    object Koin {
        private const val version = "2.2.2"
        const val core = "org.koin:koin-core:$version"
    }

    object Exposed {
        private const val version = "0.29.1"
        const val core = "org.jetbrains.exposed:exposed-core:$version"
        const val jdbc = "org.jetbrains.exposed:exposed-jdbc:$version"
        const val dao = "org.jetbrains.exposed:exposed-dao:$version"
        const val time = "org.jetbrains.exposed:exposed-java-time:$version"
    }

    object Postgres {
        private const val version = "42.2.19"
        const val core = "org.postgresql:postgresql:$version"
    }

    object DotEnv {
        private const val version = "6.2.2"
        const val core = "io.github.cdimascio:dotenv-kotlin:$version"
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

    object TestContainers {
        private const val version = "1.15.2"
        const val core = "org.testcontainers:testcontainers:$version"
        const val jupiter = "org.testcontainers:junit-jupiter:$version"
        const val postgres = "org.testcontainers:postgresql:$version"
    }

    object H2 {
        private const val version = "1.4.200"
        const val core = "com.h2database:h2:$version"
    }

    object Jackson {
        const val core = "com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0-rc1"
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

    private fun DependencyHandler.integrationTestImplementations(impls: List<String>) {
        for (impl in impls) {
            add("integrationTestImplementation", impl)
        }
    }


    private fun DependencyHandler.integrationTestRuntimes(impls: List<String>) {
        for (impl in impls) {
            add("integrationTestRuntimeOnly", impl)
        }
    }

    private val sharedImplementations = listOf(
        Koin.core,
        DotEnv.core,
        Exposed.core
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
            Exposed.core,
            Exposed.jdbc,
            Exposed.dao,
            Exposed.time
        )

        private val testImplementations = listOf(
            Exposed.core,
            Exposed.jdbc,
            Exposed.dao,
            Exposed.time,
            TestContainers.core,
            TestContainers.jupiter,
            TestContainers.postgres
        )

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
            Jackson.core
        )

        private val testImplementations = listOf<String>(
            Quarkus.junit,
            Quarkus.restAssured
        )

        private val testRuntimes = listOf(
            Jupiter.runtime
        )

        private val integrationTestRuntimes = listOf(
            Jupiter.runtime
        )

        private val integrationTestImplementations = listOf(
            Jupiter.core,
            Google.truth,
            Kotlin.Coroutines.test,
            TestContainers.core,
            TestContainers.jupiter,
            TestContainers.postgres,
            Postgres.core
        )

        fun DependencyHandler.implementations() = implementations(sharedImplementations + implementations)

        fun DependencyHandler.testImplementations() =
            testImplementations(sharedTestImplementations + testImplementations)

        fun DependencyHandler.testRuntimes() = testRuntimes(sharedTestRuntimes + testRuntimes)
    }

}
