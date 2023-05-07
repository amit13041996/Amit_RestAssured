package PawanKumar;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;


/*
 given()
 	content type, set cookies, add auth, add param, set headers info etc....

when()
	get, post, put, delete

then()
	validate status code, extract response, extract headers cookies & response body....

*/

public class HttpRequest {
	int id;

	@Test (priority = 1)
	public void GetUser() {
		
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.body("page", equalTo(2))
			.statusCode(200)
			.log().all();
	}
	
	@Test (priority = 2)
	public void CreateUser() {
		
		HashMap data = new HashMap();
		
		data.put("name", "Amit");
		data.put("job", "QA");
		
		id = given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		
//		.then()
//			.statusCode(201)
//			.log().all();
	}
	
	@Test (priority = 3, dependsOnMethods = {"CreateUser"})
	public void UpdateUser() {
		
	HashMap data = new HashMap();
		
		data.put("name", "Amit Srivastava");
		data.put("job", "QA Test Engineer");
		
		given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.put("https://reqres.in/api/users/"+id)
			//.jsonPath().getInt("id");
		
		.then()
			.statusCode(200)
			.log().all();		
		
	}
	
	@Test(priority=4)
	void deleteUser()
	{
		given()
			
		.when()
			.delete("https://reqres.in/api/users/"+id)
		
		.then()
			.statusCode(204)
			.log().all();
	}
	
	
	
}
