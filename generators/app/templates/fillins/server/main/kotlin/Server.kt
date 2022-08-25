package <%= serverPackageName %>

import <%= serverPackageName %>.configs.DataSourceConfig
import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.QuarkusApplication
import io.quarkus.runtime.annotations.QuarkusMain
import javax.inject.Inject

@QuarkusMain
class Main {

    companion object {
        @JvmStatic
        fun main(vararg args: String) {
            Quarkus.run(App::class.java, *args)
        }

        private class App : QuarkusApplication {

            @Inject
            @Suppress("unused")
            lateinit var datasources: DataSourceConfig

            override fun run(vararg args: String): Int {
                Quarkus.waitForExit()
                return 0
            }
        }
    }
}