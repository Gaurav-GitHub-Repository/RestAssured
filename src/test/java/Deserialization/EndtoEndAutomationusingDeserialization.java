package Deserialization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class EndtoEndAutomationusingDeserialization {

	public static void main(String[] args) {

	// Note : Authorization code is set to a particular time.  	
	// Given URL for code = https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php
	
	// OAuth 2.0	
		
     String URL = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AbUR2VM8WuGpl_TgrVVQXfahdxaOfwYytkf1G7r93ZH5FyLTYXMvRZlY4SEggcfSYeb1tA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
	 String partialcode = URL.split("code=")[1];
	 String code = partialcode.split("&scope")[0];	
			
	System.out.println(code);	
			
		String accesstokenresponse =  RestAssured.given().urlEncodingEnabled(false)
		.queryParam("code", code)
		.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParam("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParam("grant_type", "authorization_code")
		 .queryParams("state", "verifyfjdss")
		
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath js = new JsonPath(accesstokenresponse);
		String accessToken = js.getString("access_token");
			
		System.out.println(accessToken);	
			
			
		GetCourse gc = RestAssured.given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
		.when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class); 

       System.out.println(gc.getInstructor());
       System.out.println(gc.getLinkedIn());	
       
       // To find coursetitle of API
       System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
       
       //To Print API price 
       
      List<Api> APICourses = gc.getCourses().getApi();
      
      for(int i=0; i<APICourses.size(); i++)
      {
    	  if(APICourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
    	  {
    		 System.out.println(APICourses.get(i).getPrice());
    	  }    	  
      }
       
  //Assertion
      String[] courseTitles= { "Selenium Webdriver Java","Cypress","Protractor"};
      ArrayList<String> a = new ArrayList<String>();  
      List<WebAutomation> API = gc.getCourses().getWebAutomation();     
      
      for(int j=0; j<API.size(); j++)
      {
     	 a.add(API.get(j).getCourseTitle());
      }
      
     List<String> expectedlist =  Arrays.asList(courseTitles); 
     Assert.assertTrue(a.equals(expectedlist));
      
      }
	}
