package <%= resourcesPackageName %>

import <%= serverPackageName %>.rootPath
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class RootResourceTest {

    @Test
    fun `should return 200`() {
        given()
            .`when`()
            .get(rootPath)
            .then()
            .statusCode(200)
            .body(
                `is`(
                    """{
                |"hello": "world"
                |}""".trimMargin()
                )
            )
    }

}