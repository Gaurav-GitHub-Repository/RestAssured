package BuildEndtoEndAutomation;

import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Automation {

	public static void main(String[] args) {
	
		
		// Build End to End Automation using HTTP Methods
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
	    
		 String response =   RestAssured.given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		    .body("{\r\n"
		    		+ "  \"location\": {\r\n"
		    		+ "    \"lat\": -38.383494,\r\n"
		    		+ "    \"lng\": 33.427362\r\n"
		    		+ "  },\r\n"
		    		+ "  \"accuracy\": 50,\r\n"
		    		+ "  \"name\": \"Frontline house\", \r\n"
		    		+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
		    		+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
		    		+ "  \"types\": [\r\n"
		    		+ "    \"shoe park\",\r\n"
		    		+ "    \"shop\"\r\n"
		    		+ "  ],\r\n"
		    		+ "  \"website\": \"http://google.com\",\r\n"
		    		+ "  \"language\": \"French-IN\"\r\n"
		    		+ "}\r\n"
		    		+ "")
		    
		    .when().post("maps/api/place/add/json")
		    .then().log().all().assertThat().statusCode(200)
		    
		    .body("scope", equalTo("APP")).header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		    
		    
		    System.out.println(response);
		    
		    JsonPath js = new JsonPath(response);
		   String Place_ID = js.getString("place_id"); 
		   
		   System.out.println(Place_ID);

		    
		    // Update place
		    
		    String NewAddress = "Summer walk, Africa"; 
		    
		    String newaddress = RestAssured.given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		    .body("{\r\n"
		    		+ "\"place_id\":\""+Place_ID+"\",\r\n"
		    		+ "\"address\":\""+NewAddress+"\",\r\n"
		    		+ "\"key\":\"qaclick123\"\r\n"
		    		+ "}\r\n"
		    		+ "")
		    
		    .when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
   		    .body("msg", equalTo("Address successfully updated")).extract().response().asString();
		    
		    
	      // Get Place 	    

    String Address = RestAssured.given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
    .queryParam("place_id", Place_ID)
    .when().get("/maps/api/place/get/json")
    .then().log().all().assertThat().statusCode(200).extract().response().asString();
		  
	System.out.println(Address);	
	
	JsonPath js1 = new JsonPath(Address);
	String actualaddress = js1.getString("address");
	System.out.println(actualaddress);	  
		  
    Assert.assertEquals(actualaddress, NewAddress);		  
		  
		  
		  
		  
		    
		    
		    
	}

}
