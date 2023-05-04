package ExcelDataDrivenFramework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class datadriven {

	public ArrayList<String> getdata(String testcasename) throws IOException {
	
	// To get and validate the value from column  
		
	ArrayList<String> a = new ArrayList<String>();	
		
	FileInputStream fis = new FileInputStream("C:\\Software\\RestTesting\\DataDrivenTesting.xlsx");
    XSSFWorkbook workbook = new XSSFWorkbook(fis);
	int sheets =  workbook.getNumberOfSheets();
	
    for(int i=0; i<sheets; i++)
	{
		if(workbook.getSheetName(i).equalsIgnoreCase("Details"))
		{
			 XSSFSheet sheet = workbook.getSheetAt(i);
			 Iterator<Row> row =  sheet.iterator();
	         Row firstrow = row.next();
			 Iterator<Cell> cell = firstrow.cellIterator();
			 int k=0;
			 int column=0;
			 while(cell.hasNext())
			 {
				Cell value = cell.next();
				
				if(value.getStringCellValue().equalsIgnoreCase("Username1"))
				{
					column = k;
				}
				k++;
			 }
			 System.out.println(column);
			 
			 
	// Identify particular row and pull the data		 
			 
			 
		while(row.hasNext())
		{
		Row r =	row.next();
		if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcasename))
		{
		  Iterator<Cell> cv = r.cellIterator();
		  
		  while(cv.hasNext())
		  {
			 a.add(cv.next().getStringCellValue());
			 
		  }
		}
		
		}
			 
			 
			 
			}
	}
	return a;
	
		
}

	public ArrayList getdata1(String testcasename) {
		// TODO Auto-generated method stub
		return null;
	}
}