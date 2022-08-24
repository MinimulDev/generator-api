package <%= serverPackageName %>.resources

import com.fasterxml.jackson.databind.ObjectMapper

abstract class ResourceTest {
    private val mapper = ObjectMapper()

    internal fun json(value: Any) = mapper.writeValueAsString(value)

}