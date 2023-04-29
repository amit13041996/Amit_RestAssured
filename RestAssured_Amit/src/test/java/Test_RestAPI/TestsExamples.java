package Test_RestAPI;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;

public class TestsExamples {

	
	@Test
	public void test_1()
	{
		//Response resp = RestAssured.get("https://reqres.in/api/users?page=2");
		
		Response resp = get("https://reqres.in/api/users?page=2");
		
		System.out.println("Status Code is: \t" + resp.getStatusCode());
		System.out.println("Time is: \t" + resp.getTime());
		
		System.out.println(resp.getBody().asString());
		
		System.out.println(resp.getStatusLine());
		
		System.out.println("Content Type Header:" +resp.getHeader("content-type"));
		
		int statusCode = resp.getStatusCode();
		
		Assert.assertEquals(statusCode, 200);
	}	
	
	@Test
	public void test_2() {
		
		baseURI = "https://reqres.in/api";
		
		given().
			get("/users?page=2").
		then().
			statusCode(200).
			body("data[1].id", equalTo(8))
			.log().all();
		
	}
}
