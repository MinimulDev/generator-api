package <%= serverPackageName %>

import io.smallrye.config.ConfigMapping
import io.smallrye.config.WithName
import io.smallrye.config.WithParentName

@ConfigMapping(prefix = "datasources")
interface DataSourceConfig {
    @WithParentName
    fun sources(): Map<String, Config>

    interface Config {
        fun url(): String
        fun user(): String
        fun password(): String
    }

}