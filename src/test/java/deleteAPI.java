import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;

public class deleteAPI {
	@Test
	public void deleteAPITest() {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().queryParam("key","qaclick123").header("Content-type","application/json")
		.body("{\r\n"
				+ "    \"place_id\":\"b44f6e68505f6a016a649bfbbf507a35\"\r\n"
				+ "}\r\n"
				+ "")
		.when().delete("/maps/api/place/delete/json")
		.then().log().all().statusCode(200);		
	}

}
