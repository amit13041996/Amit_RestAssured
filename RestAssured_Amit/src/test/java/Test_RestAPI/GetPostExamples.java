package Test_RestAPI;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class GetPostExamples {

	//@Test
	public void GetRequest() {
		
		baseURI = "https://reqres.in/api";
		
		given().
			get("/users?page=2").
		then().
			statusCode(200).
			body("data[2].first_name", equalTo("Tobias")).
			body("data.first_name", hasItems("Michael","Rachell"));
		
		
	}
	
	@Test 
	public void PostRequest() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
//		map.put("name", "Amit");
//		map.put("job", "QA");
//		
//		System.out.println(map);
//		
//		
//		JSONObject request = new JSONObject(map);
//		
		JSONObject request = new JSONObject();
		
		request.put("name","Amit");
		request.put("job", "QA");
		
		
		System.out.println(request.toJSONString());
		
		baseURI = "https://reqres.in/api";
		
		given().
			header("Content-Type","application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).		
			body(request.toJSONString()).
		when().
			post("/users").
		then().
			statusCode(201).
			log().all();			
		
	}
	
	
	
}
