package <%= serverPackageName %>

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.quarkus.jackson.ObjectMapperCustomizer
import javax.inject.Singleton
import javax.ws.rs.ApplicationPath
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.Application
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@ApplicationPath("<%= rootPath %>")
internal class Server : Application()

@Singleton
@Suppress("unused")
class Mapper : ObjectMapperCustomizer {
    override fun customize(objectMapper: ObjectMapper) {
        objectMapper.registerModule(
            KotlinModule.Builder()
                .build()
        )
    }
}

@Path("/")
class RootResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun index(): Response {
        return Response
            .status(200)
            .entity(
                """{
                |"hello": "world"
                |}
            """.trimMargin()
            )
            .build()
    }

}