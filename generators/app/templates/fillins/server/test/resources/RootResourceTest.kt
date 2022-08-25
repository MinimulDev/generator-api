package <%= serverPackageName %>.resources

import io.quarkus.test.common.http.TestHTTPEndpoint
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.RestAssured.`when`
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
@TestHTTPEndpoint(RootResource::class)
class RootResourceTest : ResourceTest() {

    @Test
    fun `should return 200 with default name`() {
        `when`()
            .get()
            .then()
            .statusCode(200)
            .body(`is`(json(HelloResponse("world"))))
    }

    @Test
    fun `should return 200 with provided name`() {
        given()
            .queryParam("name", "John")
            .`when`()
            .get()
            .then()
            .statusCode(200)
            .body(`is`(json(HelloResponse("John"))))
    }

}