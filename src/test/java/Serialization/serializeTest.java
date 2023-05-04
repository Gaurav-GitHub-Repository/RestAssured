package Serialization;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;

public class serializeTest {

	public static void main(String[] args) {

	RestAssured.baseURI="https://rahulshettyacademy.com";	
		
	AddPlacePojo  p = new AddPlacePojo();
	p.setAccuracy(50);
	p.setName("Frontline house");
	p.setPhone_number("(+91) 983 893 3937");	
	p.setAddress("29, side layout, cohen 09");
	p.setWebsite("https://rahulshettyacademy.com");	
	p.setLanguage("French-IN");
	
	List<String> mylist = new ArrayList<String>();
	mylist.add("shoe park");
	mylist.add("shop");
	p.setTypes(mylist);

	Location l = new Location();
	l.setLat(-38.383494);
	l.setLng(33.427362);
	p.setLocation(l);

    
    String response =  RestAssured.given().log().all().queryParam("key", "qaclick123")
    .body(p)
    .when().post("/maps/api/place/add/json")
    .then().assertThat().statusCode(200)
    .extract().response().asString();
    
    System.out.println(response);
	}

}
