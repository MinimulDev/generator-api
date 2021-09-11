package <%= serverPackageName %>

import <%= serverPackageName %>.data.repositories.repositoryModule
import <%= serverPackageName %>.domain.domainModule
import <%= serverPackageName %>.resources.resourceModule
import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.QuarkusApplication
import io.quarkus.runtime.annotations.QuarkusMain
import org.koin.core.context.startKoin
import org.koin.dsl.module
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

        class App : QuarkusApplication {

            override fun run(vararg args: String): Int {
                startKoin {
                    val runtimeModule = module {
                    }
                    modules(
                        listOf(
                            runtimeModule,
                            repositoryModule,
                            domainModule,
                            resourceModule
                        )
                    )
                }
                Quarkus.waitForExit()
                return 0
            }
        }
    }
}