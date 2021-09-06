package <%= serverPackageName %>

import javax.ws.rs.ApplicationPath
import javax.ws.rs.core.Application

@ApplicationPath("<%= rootPath %>")
internal class Server : Application()