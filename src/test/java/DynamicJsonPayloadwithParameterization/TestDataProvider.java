package DynamicJsonPayloadwithParameterization;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class TestDataProvider {

	@Test(dataProvider="BooksData")
	public void AddBook(String isbn, String aisle)
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		 String resp = RestAssured.given().header("Content-Type","application/json")
		.body(Payload.Book(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200) 
		.extract().response().asString();
		
		//System.out.println(resp);
		 
		 JsonPath js = new JsonPath(resp);
		 String id = js.get("ID");
		 
		 System.out.println(id);
	}

@DataProvider(name="BooksData")
public Object[][] getData()
{
	return new Object[][] {{"Laptop","456789"},{"Mobile","223456"},{"Bag","111234"}};
}
}
