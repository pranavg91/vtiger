package com.login.vector;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

import generic_utility.FileUtility;

public class Leads {

	public static void main(String[] args) throws InterruptedException, IOException, ParseException {
		// TODO Auto-generated method stub
		//readdatafrom json file
//		FileReader fr = new FileReader("./src/test/resources/Commondata.json");
//
//		JSONParser parser = new JSONParser();
//		Object obj = parser.parse(fr);
//
//		JSONObject jobj = (JSONObject) obj;
//
//		String browser = jobj.get("browser").toString();
//		String url = jobj.get("url").toString();
//		String username = jobj.get("us").toString();
//		String password = jobj.get("password").toString();
		
//		Now read data from utility file
		
		String browser = FileUtility.getDataFromJsonFile("browser");
		String url = FileUtility.getDataFromJsonFile("url");
		String username = FileUtility.getDataFromJsonFile("us");
		String password= FileUtility.getDataFromJsonFile("password");
		
		//read data from excel
//		FileInputStream fs = new FileInputStream("./src/test/resources/ExcelData.xlsx");
//
//		Workbook wb = WorkbookFactory.create(fs);
//
//		Sheet ws = wb.getSheet("Org");
//
//		Row row = ws.getRow(2);
//		Cell cell = row.getCell(0);
//
//		System.out.println(cell.getStringCellValue());
//
//		fs.close();
//		wb.close();
		
		
		String leadName = FileUtility.readDataFromExcel("Lead", 1, 0);
		String companyName = FileUtility.readDataFromExcel("Lead", 1, 1);
	
		WebDriver driver = null;

		if (browser.equals("chrome")) {
			driver = new ChromeDriver();

		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(username);

		driver.findElement(By.name("user_password")).sendKeys(password);

		driver.findElement(By.cssSelector("[id='submitButton']")).click();

		Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@class='hdrTabBg']//td//tr/td/a[text()='Leads']")).click();

		// click to create lead

		driver.findElement(By.cssSelector("[title='Create Lead...']")).click();
		Thread.sleep(2000);
		WebElement enterLastName = driver.findElement(By.cssSelector("[name='lastname']"));
		//enterLastName.sendKeys("Test");
		enterLastName.sendKeys(leadName);

		//driver.findElement(By.cssSelector("[name='company']")).sendKeys("Test1");
		driver.findElement(By.cssSelector("[name='company']")).sendKeys(companyName);

		driver.findElement(By.xpath("(//*[@title='Edit [Alt+E]'])[1]")).click();

		String lastName = driver.findElement(By.cssSelector("[name='lastname']")).getAttribute("value");

		WebElement leadSourcedropDown = driver.findElement(By.cssSelector("[name='leadsource']"));

		Select sc = new Select(leadSourcedropDown);
		sc.selectByIndex(1);

		WebElement industryDropdown = driver.findElement(By.cssSelector("[name='industry']"));
		Select selectIndustry = new Select(industryDropdown);
		selectIndustry.selectByIndex(1);

		System.out.println(lastName);
		driver.findElement(By.cssSelector("[title='Save [Alt+S]']")).click();
		driver.quit();

	}

}
