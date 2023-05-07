package PawanKumar;


import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;


/*
Different ways to create POST request body
1) Post request body using Hashmap
2) Post request body creation using using Org.JSON
3) Post request body creation using POJO class
4) Post request using external json file data
*/



public class Ways_to_create_request_PayLoad {

	//@Test (priority = 1)	
	public void testPostusingHashMap() {
		
	HashMap data=new HashMap();
		
		data.put("name","Amit");
		data.put("location","Pune");
		data.put("phone","777777");
		
		String courseArr[]= {"C","C++"};
		data.put("courses", courseArr);
		
		given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.post("http://localhost:3000/posts")		
		
		.then()
			.statusCode(201)
			.body("name", equalTo("Amit"))
			.body("location", equalTo("Pune"))
			.body("phone", equalTo("777777"))
			.body("courses[0]", equalTo("C"))
			.body("courses[1]", equalTo("C++"))
			.header("Content-Type","application/json; charset=utf-8")
			.log().all();	
		
	}	
	
	@Test	(priority = 2)	
	public void DeleteData()  {
		
		
		given()
			.contentType("application/json")
						
		.when()
			.delete("http://localhost:3000/posts/4")		
		
		.then()
			.statusCode(404);		
	}
	
}
