package AutomateNestedJson;

import io.restassured.path.json.JsonPath;

public class NestedJson {

	public static void main(String[] args) {
	
		
	// Print all course titles and their prices
		
    // Print number of copies sold by RPA course
		
	
		
		
	JsonPath js = new JsonPath(Payload.CoursePrice());	
		
	// Print number of courses returned by API
  
	 int count = js.getInt("courses.size()");
	 System.out.println(count);

   // Print Purchase Amount	 
	
     int totalamount =	js.getInt("dashboard.purchaseAmount"); 
	 System.out.println(totalamount);
	 
	// Print Title of first course
	 
	String title = js.get("courses[0].title");
	System.out.println(title); 
	
	// Print all course titles and their prices
	
	for(int i=0; i<count; i++)
	{
	String coursetitle = js.get("courses["+i+"].title");
    System.out.println(coursetitle);
    System.out.println(js.get("courses["+i+"].price").toString());  
    
    }
    
	}  
	
}
	


