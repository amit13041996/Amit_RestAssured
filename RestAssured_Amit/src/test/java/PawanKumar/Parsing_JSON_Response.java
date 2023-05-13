package PawanKumar;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

// PreRequisite: Navigate to UdemyPOSTMAN/ 03 folder / cmd / run with 'json-server students.json' and press Enter.

public class Parsing_JSON_Response {

	// @Test (priority = 1)
	public void JSON_Respose_Test() {

		/*
		 * Approach == 1
		 * --------------------------------------------------------------------------
		 * 
		 * given() .contentType(ContentType.JSON)
		 * 
		 * .when() .get("http://localhost:3000/store")
		 * 
		 * .then() .statusCode(200) .header("Content-Type",
		 * "application/json; charset=utf-8") .body("book[3].title",
		 * equalTo("The Lord of the Rings"));
		 */

		// Approach == 2 ---- MORE ADVANTAGE
		// --------------------------------------------------------------------------

		Response resp = given().contentType(ContentType.JSON)

				.when().get("http://localhost:3000/store");

		Assert.assertEquals(resp.getStatusCode(), 200); // validation 1
		Assert.assertEquals(resp.header("Content-Type"), "application/json; charset=utf-8"); // validation 2

		// resp.getBody();

		String bookName = resp.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookName, "The Lord of the Rings"); // validation 3

		// .then()

	}

	@Test (priority =  2)
	public void JSON_Respose_DATA() {
	
		// Approach == 2   ---- MORE ADVANTAGE --------------------------------------------------------------------------
		
		Response resp =	given()
			.contentType(ContentType.JSON)
			
			.when()
				.get("http://localhost:3000/store");
	
		// Approach == 3 ---> Using  JSON Object Class ----------------------------------------------------------------------
		
		JSONObject jo = new JSONObject(resp.asString());  // Converting response to JSon object type
		
		for(int i=0; i< jo.getJSONArray("book").length(); i++) {
			
			String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			System.out.println(bookTitle);
//			boolean status = false;
//			
//			if(bookTitle.equals("The Lord of the Rings")) {
//					
//				status = true;
//				break;
//			}
//			Assert.assertEquals(status, true);
			
		}
		
		// Validate totla price ---
		double priceTotal = 0;
		
			for(int i=0; i< jo.getJSONArray("book").length(); i++) {
			
			String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
			System.out.println(price);
			
			priceTotal = priceTotal + Double.parseDouble(price);	
			}
			
			System.out.println("Toatal price is:  " + priceTotal );
			
	}

}
