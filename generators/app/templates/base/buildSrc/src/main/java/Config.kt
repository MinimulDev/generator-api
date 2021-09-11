import org.gradle.api.JavaVersion
import org.gradle.api.Project
import java.util.*

object Config {

    const val group = "<%= group %>"

    val javaVersion = JavaVersion.VERSION_11

    private lateinit var v: Version

    val optIn = listOf(
        "org.koin.core.component.KoinApiExtension"
    )

    fun Project.loadVersion(): Version {
        if (!::v.isInitialized) {
            val props = Properties()
            file("$rootDir/version.properties").inputStream().use { props.load(it) }
            v = Version(
                major = props.getProperty("MAJOR").toInt(),
                minor = props.getProperty("MINOR").toInt(),
                patch = props.getProperty("PATCH").toInt(),
                hotfix = props.getProperty("HOTFIX").toInt()
            )
        }
        return v
    }

    data class Version(
        val major: Int,
        val minor: Int,
        val patch: Int,
        val hotfix: Int
    ) {

        override fun toString(): String {
            return "$major.$minor.$patch" + if (hotfix != 0) ".$hotfix" else ""
        }

    }

}