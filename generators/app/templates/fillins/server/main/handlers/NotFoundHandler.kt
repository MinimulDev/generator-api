package <%= handlersPackageName %>

import javax.annotation.Priority
import javax.ws.rs.NotFoundException
import javax.ws.rs.Priorities
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
@Priority(Priorities.USER)
class NotFoundHandler : ExceptionMapper<NotFoundException> {
    override fun toResponse(exception: NotFoundException): Response {
        return Response.status(404).build()
    }
}