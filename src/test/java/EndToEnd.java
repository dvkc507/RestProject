import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import restUtil.Payload;

public class EndToEnd {
	String Place_ID = "";
	@Test(priority=0)
	public void createPlace() {
		// body method accept/require JSON string fromat
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		Response response = given().log().all().queryParam("key", "qaclick123")
				.header("Content-Type", "application/json")
				.body(Payload.getcreatePlacePayload())
				.when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
				.header("Content-Type", "application/json;charset=UTF-8").body("scope", Matchers.equalTo("APP"))
				.extract().response();
		Place_ID = response.path("place_id");
		/*
		 * or you can use JsonPath instead of Response class, 
		 * String response = -----.extract.asString(); 
		 * JsonPath jp = new JsonPath(response); 
		 * String Place_ID =jp.getString("place_id");
		 */
	}

	@Test(priority=1,dependsOnMethods="createPlace")
	public void getPlace() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		Response response = given().log().all().queryParam("key", "qaclick123")
				.queryParam("place_id", Place_ID).when().get("/maps/api/place/get/json")
				.then().assertThat().log().all().statusCode(200)
				.header("Content-type", "application/json;charset=UTF-8").body("accuracy", Matchers.equalTo("50"))
				.body("name", Matchers.equalTo("Frontline house")).extract().response();

		System.out.println(response);

		boolean accur = response.body().asString().contains("accuracy");
		Assert.assertTrue(accur, "Does not contains accuracy");

		String accuracyValue = response.path("accuracy");
		Assert.assertEquals(accuracyValue, "50");

		String latitude = response.path("location.latitude");
		Assert.assertEquals(latitude, "-38.383494");
	}

	@Test(priority=2,dependsOnMethods="createPlace")
	public void updateTest() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().header("Content-type", "application/json")
				.body(Payload.getUpdatePlacePayload(Place_ID))
				.when().patch("/maps/api/place/update/json").then().assertThat().log().all().statusCode(200).extract()
				.asString();
		System.out.println(response);
	}

	@Test(priority=3,dependsOnMethods="createPlace")
	public void deleteAPITest() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().queryParam("key", "qaclick123").header("Content-type", "application/json")
				.body("{\r\n" + "    \"place_id\":\""+Place_ID+"\"\r\n" + "}\r\n" + "").when()
				.delete("/maps/api/place/delete/json").then().log().all().statusCode(200).extract().asString();
		System.out.println(response);
	}

}
