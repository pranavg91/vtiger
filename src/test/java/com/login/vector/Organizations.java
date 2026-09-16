package com.login.vector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.logging.FileHandler;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Organizations {

	public static void main(String[] args) throws InterruptedException, IOException, ParseException {
		// TODO Auto-generated method stub

		FileReader fr = new FileReader("./src/test/resources/Commondata.json");

		JSONParser pareser = new JSONParser();
		Object obj = pareser.parse(fr);

		JSONObject jobj = (JSONObject) obj;

		String browser = jobj.get("browser").toString();
		String url = jobj.get("url").toString();
		String userName = jobj.get("us").toString();
		String password = jobj.get("password").toString();
		
		//readdata from excel.

		FileInputStream fs = new FileInputStream("./src/test/resources/ExcelData.xlsx");

		Workbook wb = WorkbookFactory.create(fs);

		Sheet ws = wb.getSheet("Org");

		Row row = ws.getRow(2);
		Cell cell = row.getCell(0);

		System.out.println(cell.getStringCellValue());

		fs.close();
		wb.close();

		WebDriver driver = null;

		if (browser.equals("chrome")) {
			driver = new ChromeDriver();

		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();

		}

		// WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(url);

		Thread.sleep(10000);
		driver.findElement(By.name("user_name")).sendKeys(userName);

		driver.findElement(By.name("user_password")).sendKeys(password);

		driver.findElement(By.cssSelector("[id='submitButton']")).click();

		Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@class='hdrTabBg']//td//tr/td/a[text()='Organizations']")).click();

		driver.findElement(By.cssSelector("[title='Create Organization...']")).click();

		String accountName = "Test" + (int) (Math.random() * 1000);

		driver.findElement(By.cssSelector("[name='accountname']")).sendKeys(accountName);

		Thread.sleep(2000);
		WebElement dropDown = driver.findElement(By.cssSelector("[name='industry']"));
		Select st = new Select(dropDown);
		st.selectByValue("Apparel");

		WebElement typeDropDown = driver.findElement(By.cssSelector("[name='accounttype']"));
		Select sct = new Select(typeDropDown);
		sct.selectByIndex(1);

		driver.findElement(By.id("phone")).sendKeys("9878987898");
		Thread.sleep(2000);
		driver.findElement(By.id("email1")).sendKeys("pranav@gmail.com");

		Thread.sleep(2000);

		driver.findElement(By.cssSelector("[title='Save [Alt+S]']")).click();

		Thread.sleep(2000);

		TakesScreenshot ts = (TakesScreenshot) driver;

		File src = ts.getScreenshotAs(OutputType.FILE);
		File dist = new File("./Screenshot/org.png");

		org.openqa.selenium.io.FileHandler.copy(src, dist);

		System.out.println("Pass");

		driver.quit();

	}

}
