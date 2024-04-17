import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class updatePlaceAPI {
	
	@Test()
	public void updateTest() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		Response response = given().header("Content-type","application/json")
				.body("{\r\n"
				+ "\"place_id\":\"bce5dd1259548e83396d36ce30ae0076\",\r\n"
				+ "\"address\":\"70 Summer walk, Sec\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().patch("/maps/api/place/update/json")
		.then().assertThat().log().all()
		.statusCode(200).extract().response();
		System.out.println(response);
	}

}
