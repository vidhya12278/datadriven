package org.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class New {
	static WebDriver driver;
	static JavascriptExecutor js;
	public static  WebDriver open()
	{
	System.setProperty("webdriver.chrome.driver","C:\\Selenium\\drivers\\chromedriver.exe" );
	driver = new ChromeDriver();
	driver.get("http://adactinhotelapp.com/");
	driver.manage().window().maximize();
	return driver;

	}
   public static  void send(WebElement t,String text) {
	t.sendKeys(text);
}
   public  static void click(WebElement t) {
	
     t.click();
}
   public static  void scriptjava(WebElement r) 
   {
	 js=(JavascriptExecutor)driver;
	 js.executeScript("arguments[0].setAttribute('style','background:pink');",r);

}
   public static  void down(String text,WebElement r) {

		 js=(JavascriptExecutor)driver;
		 js.executeScript("arguments[0].setAttribute('value','"+text+"')",r);
} 
   public static  void clicks(WebElement r) {

	 js=(JavascriptExecutor)driver;
	 js.executeScript("arguments[0].click()",r);
}
   public static  String read(int rowno,int colno) throws InvalidFormatException, IOException {
	   File f = new File("C:\\Selenium\\excel\\finalstext.xlsx");
		Workbook n=new XSSFWorkbook(f);
	Sheet s = n.getSheet("Basic");
	Row r = s.getRow(rowno);
	Cell c = r.getCell(colno);
	CellType cellType = c.getCellType();
	
	String value="";
		if(cellType==cellType.STRING)
	{
	value=c.getStringCellValue();	
	}
	else 
		if(DateUtil.isCellDateFormatted(c))
		{
			    Date e=c.getDateCellValue();
			    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			    value=sdf.format(e);
		}
		else
		{
			double d = c.getNumericCellValue();
			long l=(long)d;
			value=String.valueOf(l);
				}
	return value;
	}
}
