package DynamicJsonPayloadwithParameterization;

public class Payload {

public static String Book(String isbn, String aisle)
{
String Payload = "{\r\n"
		+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
		+ "\"isbn\":\""+isbn+"\",\r\n"
		+ "\"aisle\":\""+aisle+"\",\r\n"
		+ "\"author\":\"John foer\"\r\n"
		+ "}\r\n"
		+ "";
 return Payload;
 
}

}
