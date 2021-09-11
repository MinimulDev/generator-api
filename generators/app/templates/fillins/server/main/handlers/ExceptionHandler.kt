package <%= serverPackageName %>.handlers

import javax.annotation.Priority
import javax.ws.rs.Priorities
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
@Priority(Priorities.USER)
class ExceptionHandler : ExceptionMapper<Exception> {
    override fun toResponse(exception: Exception): Response {
        val msg = exception.message
        if (msg != null) {
            return Response.status(Status.BAD_REQUEST).entity(
                """{
                |"error": "$msg"
                |}""".trimMargin()
            ).build()
        }
        return Response.status(Status.BAD_REQUEST).build()
    }
}