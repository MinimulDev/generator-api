package <%= serverPackageName %>

import <%= serverPackageName %>.configs.DataSourceConfig
import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.QuarkusApplication
import io.quarkus.runtime.annotations.QuarkusMain
import javax.inject.Inject
import javax.ws.rs.ApplicationPath
import javax.ws.rs.core.Application

@ApplicationPath("<%= rootPath %>")
class Server : Application()

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