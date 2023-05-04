package RestAssuredAutomationforOauth2;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class OauthTest {

	public static void main(String[] args) {
		
		
		// Note : Authorization code is set to a particular time.  	
	    // Given URL for code = https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php
	 		
		
		String URL = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AbUR2VOiD1gYTSb6Rq9WkHrRcGIRIbNOH6IyUhLv1mHAVlIMBhfVSz65PJmEZoOFiSo2uA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
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
			
			
		 RestAssured.given().log().all().queryParam("access_token", accessToken)
		.when().get("https://rahulshettyacademy.com/getCourse.php");

		}
	

	}


