package <%= resourcesPackageName %>

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

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