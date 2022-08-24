package <%= serverPackageName %>.resources

import <%= serverPackageName %>.rootPath
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class RootResourceTest : ResourceTest() {

    @Test
    fun `should return 200 with default name`() {
        given()
            .`when`()
            .get(rootPath)
            .then()
            .statusCode(200)
            .body(`is`(json(HelloResponse("world"))))
    }

    @Test
    fun `should return 200 with provided name`() {
        given()
            .`when`()
            .queryParam("name", "John")
            .get(rootPath)
            .then()
            .statusCode(200)
            .body(`is`(json(HelloResponse("John"))))
    }

}