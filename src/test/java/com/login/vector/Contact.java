package com.login.vector;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import generic_utility.FileUtility;

public class Contact {

	public static void main(String[] args) throws InterruptedException, IOException, ParseException {
		// TODO Auto-generated method stub

		//read data from json
		
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
		
		//Read data from utility file.
		String browser=FileUtility.getDataFromJsonFile("browser");
		String url = FileUtility.getDataFromJsonFile("url");
		String username = FileUtility.getDataFromJsonFile("us");
		String password = FileUtility.getDataFromJsonFile("password");

		// Read Excel
//		FileInputStream fis = new FileInputStream("./src/test/resources/ExcelData.xlsx");
//
//		Workbook wb = WorkbookFactory.create(fis);
//		Sheet sh = wb.getSheet("Org");
//		Row row = sh.getRow(1);
//		Cell cell = row.getCell(0);
//		String orgName = cell.getStringCellValue();

		
		
		WebDriver driver = null;

		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(url);

		Thread.sleep(10000);
		driver.findElement(By.name("user_name")).sendKeys(username);

		driver.findElement(By.name("user_password")).sendKeys(password);

		driver.findElement(By.cssSelector("[id='submitButton']")).click();

		Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@class='hdrTabBg']//td//tr/td/a[text()='Contacts']")).click();

		driver.findElement(By.cssSelector("[title='Create Contact...']")).click();
		
	
		//read data from excel using utility method
		
		LocalDateTime random = LocalDateTime.now();
		String randomLastName=FileUtility.readDataFromExcel("Contact", 1, 0)  + random;

		String lead = FileUtility.readDataFromExcel("Contact", 1, 1);
		System.out.println(lead);

		String email = FileUtility.readDataFromExcel("Contact", 1, 2);
		System.out.println(email);
		
		String assistant = FileUtility.readDataFromExcel("Contact", 1, 3);
		
		WebElement enterLastName = driver.findElement(By.cssSelector("[name	='lastname']"));
		enterLastName.sendKeys(randomLastName);

		Thread.sleep(3000);
		WebElement leadSourceDropDown = driver.findElement(By.cssSelector("[name='leadsource']"));
		Select sct = new Select(leadSourceDropDown);

		sct.selectByValue("Cold Call");
		//sct.selectByValue(lead);

		Thread.sleep(3000);
		//driver.findElement(By.id("email")).sendKeys("pranav@gmail.com");
		driver.findElement(By.id("email")).sendKeys(email);

		//driver.findElement(By.id("assistant")).sendKeys("Assistant");
		driver.findElement(By.id("assistant")).sendKeys(assistant);

		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement birthdayIcon = driver.findElement(By.id("jscal_trigger_birthday"));
		wt.until(ExpectedConditions.visibilityOf(birthdayIcon)).click();

		for (int i = 1; i <= 10; i++) {

			WebElement previousButton = driver.findElement(
					By.xpath("//div[@class='calendar']//following::tr[@class ='headrow']//td[text()='«']"));
			previousButton.click();
		}
		Thread.sleep(500);
		driver.findElement(By.xpath("//div[@class='calendar']//td[text()='12']")).click();

		driver.findElement(By.cssSelector("[title='Save [Alt+S]']")).click();
		Thread.sleep(2000);

		System.out.println("Pass");

		driver.quit();

	}

}
