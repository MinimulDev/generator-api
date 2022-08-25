package <%= serverPackageName %>.resources

import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
abstract class ResourceTest {
    private val mapper = ObjectMapper()

    internal fun json(value: Any) = mapper.writeValueAsString(value)

}