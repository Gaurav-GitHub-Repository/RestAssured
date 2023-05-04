package DynamicJsonPayloadwithParameterization;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {

	@Test
	public void AddBook()
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		 String resp = RestAssured.given().header("Content-Type","application/json")
		.body(Payload.Book("acwjyui","11442553"))
		.when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200) 
		.extract().response().asString();
		
		//System.out.println(resp);
		
		
		 JsonPath js = new JsonPath(resp);
		 String id = js.get("ID");
		 
		 System.out.println(id);
	}
	
	
}
