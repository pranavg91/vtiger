package com.login.vector;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Email {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("http://localhost:8888/");

		Thread.sleep(10000);
		driver.findElement(By.name("user_name")).sendKeys("admin");

		driver.findElement(By.name("user_password")).sendKeys("manager");

		driver.findElement(By.cssSelector("[id='submitButton']")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@alt='vtiger-crm-logo.gif']"))));

		driver.findElement(By.xpath("//*[@class='hdrTabBg']//tbody//tbody//tr//td//a[text()='Email']")).click();

		String mainWindow = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text()='Compose']")).click();

		// main window

		Set<String> allWindows = driver.getWindowHandles();

		String firstWindow = "";

		for (String window : allWindows) {

			if (!window.equals(mainWindow)) {

				firstWindow = window;
				break;
			}
		}

		// Switch to Compose window
		driver.switchTo().window(firstWindow);
		driver.manage().window().maximize();

		System.out.println("First Window : " + firstWindow);
		System.out.println("Title : " + driver.getTitle());

		// ------------------------------------------------
		// 3. Click Contacts button
		// ------------------------------------------------

		driver.findElement(By.cssSelector("[title='Select']")).click();

		// ------------------------------------------------
		// 4. SECOND WINDOW - CONTACT LIST
		// ------------------------------------------------

		Set<String> windowsAfterContact = driver.getWindowHandles();

		String secondWindow = "";

		for (String window : windowsAfterContact) {

			if (!window.equals(mainWindow) && !window.equals(firstWindow)) {

				secondWindow = window;
				break;
			}
		}

		// Switch to Contact window
		driver.switchTo().window(secondWindow);
		driver.manage().window().maximize();

		System.out.println("Second Window : " + secondWindow);
		System.out.println("Title : " + driver.getTitle());

		driver.findElement(By.xpath("//a[text()=' TestContact']")).click();

		Thread.sleep(7000);
		driver.switchTo().window(firstWindow);
		driver.findElement(By.xpath("//input[@id='subject']")).sendKeys("Testing woks");

		 List<WebElement> allFrame = driver.findElements(By.tagName("iframe"));
		System.out.println("total frame " + allFrame.size());
		//driver.switchTo().frame(driver.findElement(By.id("cke_contents_description")));
		
		driver.findElement(By.tagName("body")).sendKeys("Testing is in progress");

		driver.findElement(By.xpath("(//*[@name='Send'])[2]")).click();
		driver.switchTo().defaultContent();

		Thread.sleep(5000);
		driver.close();

	}

}
