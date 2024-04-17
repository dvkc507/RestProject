import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import restUtil.Payload;
import restUtil.ReUsableMethods;

public class TestingComplexPayload {
	/*
	 * 1. Print No of courses returned by API 
	 * 2.Print Purchase Amount 
	 * 3. Print Title of the first course 
	 * 4. Print All course titles and their respective Prices 
	 * 5. Print no of copies sold by RPA Course 
	 * 6. Verify if Sum of all Course prices matches with Purchase Amount
	 */

	@Test
	public void  noOfCourses() {
		String payload = Payload.getCoursePayload();
		JsonPath jsonPath = ReUsableMethods.rawToJSON(payload);
		//print number of courses
		int courseCount = jsonPath.getInt("courses.size()");
		System.out.println("courseCount :"+courseCount);
		//printing purchase amount
		int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");
		System.out.println("purchaseAmount :"+purchaseAmount);
		//print title of the first course
		String title = jsonPath.getString("courses[0].title");
		System.out.println("title of first course "+title);
		//print all course titles
		int sum=0;
		for(int i=0;i<courseCount;i++) {
			System.out.println("cources title is :"+jsonPath.getString("courses["+i+"].title")+" and it's price :"+jsonPath.getString("courses["+i+"].price"));
			if(jsonPath.getString("courses["+i+"].title").equalsIgnoreCase("RPA")) {
				System.out.println("RPA copies :"+jsonPath.getString("courses["+i+"].copies"));
			}
			sum = sum+jsonPath.getInt("courses["+i+"].price");	
		}
		int totalPrice = jsonPath.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum,totalPrice,"Actual and expected prices are not same");	
	}
}
//payload used is, mock/dummy payload, we can use dummy payload when we dont have API ready for testing
//{
//	  "dashboard": {
//	    "purchaseAmount": 910,
//	    "website": "rahulshettyacademy.com"
//	  },
//	  "courses": [
//	    {
//	      "title": "Selenium Python",
//	      "price": 50,
//	      "copies": 6
//	    },
//	    {
//	      "title": "Cypress",
//	      "price": 40,
//	      "copies": 4
//	    },
//	    {
//	      "title": "RPA",
//	      "price": 45,
//	      "copies": 10
//	    }
//	  ]
//	}
