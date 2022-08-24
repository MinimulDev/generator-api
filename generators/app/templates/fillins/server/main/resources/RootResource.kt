package <%= serverPackageName %>.resources

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import io.quarkus.runtime.annotations.RegisterForReflection
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@RegisterForReflection
class HelloResponse @JsonCreator constructor(
    @JsonProperty("hello") val hello: String
)

@Path("/")
class RootResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun index(@QueryParam("name") name: String?): Response {
        val value = name ?: "world"
        return Response.status(200).entity(HelloResponse(value)).build()
    }

}