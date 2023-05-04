package ExcelDataDrivenFramework;

import java.io.IOException;
import java.util.ArrayList;

public class sampletest {

	public static void main(String[] args) throws IOException {
	
		
		datadriven d = new datadriven();
    	ArrayList data = d.getdata("Username1");

     	System.out.println(data.get(0));
    	System.out.println(data.get(1));
	}

}
