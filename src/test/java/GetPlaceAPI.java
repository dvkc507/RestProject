import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GetPlaceAPI {
	@Test
	public void getPlace() {
		RestAssured.baseURI ="https://rahulshettyacademy.com";
		Response response = given().log().all().queryParam("key","qaclick123").queryParam("place_id", "b44f6e68505f6a016a649bfbbf507a35")
		.when().get("/maps/api/place/get/json").
		then().assertThat().log().all().statusCode(200).header("Content-type", "application/json;charset=UTF-8")
		.body("accuracy", Matchers.equalTo("50"))
		.body("name",Matchers.equalTo("Frontline house")).extract().response();
		
		System.out.println(response);
		
		boolean accur = response.body().asString().contains("accuracy");
		Assert.assertTrue(accur,"Does not contains accuracy");
		
		String accuracyValue = response.path("accuracy");
		Assert.assertEquals(accuracyValue, "50");
		
		String latitude = response.path("location.latitude");
		Assert.assertEquals(latitude, "-38.383494");
		

		

		
		
	}

}
