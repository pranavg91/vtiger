package com.login.vector;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Email2 {
	public static void main(String[] args) throws InterruptedException {

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

		Thread.sleep(3000);
		// 2nd win
		Set<String> secondWin = driver.getWindowHandles();
		String secondWindow = "";
		for (String win : secondWin) {

			if (!win.equals(mainWindow)) {
				driver.switchTo().window(win);

				secondWindow = win;

				driver.manage().window().maximize();
				Thread.sleep(3000);
				
				break;

			}
			
			 driver.manage().window().maximize();

		        wait.until(ExpectedConditions.elementToBeClickable(
		                By.cssSelector("[title='Select']"))).click();


			wait.until(ExpectedConditions.numberOfWindowsToBe(3));
			Set<String> thirdWindow = driver.getWindowHandles();

			for (String win1 : thirdWindow) {
				if (!win1.equals(mainWindow) && !win1.equals(secondWindow)) {
					driver.switchTo().window(win1);
					{
						Thread.sleep(3000);
						System.out.println("Switched to third window");
						driver.manage().window().maximize();
						
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=' TestContact']"))).click();
						//driver.findElement(By.xpath("//a[text()=' TestContact']")).click();
						break;
						
					}
				}
				Thread.sleep(3000);
				driver.quit();
			}
			
		}

	}
}
