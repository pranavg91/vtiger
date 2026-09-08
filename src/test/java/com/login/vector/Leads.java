package com.login.vector;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Leads {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");

		driver.findElement(By.name("user_password")).sendKeys("manager");

		driver.findElement(By.cssSelector("[id='submitButton']")).click();

		Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@class='hdrTabBg']//td//tr/td/a[text()='Leads']")).click();

		// click to create lead

		driver.findElement(By.cssSelector("[title='Create Lead...']")).click();
		Thread.sleep(2000);
		WebElement enterLastName = driver.findElement(By.cssSelector("[name='lastname']"));
		enterLastName.sendKeys("Test");

		driver.findElement(By.cssSelector("[name='company']")).sendKeys("Test1");

		driver.findElement(By.cssSelector("[title='Save [Alt+S]']")).click();

		driver.findElement(By.xpath("(//*[@title='Edit [Alt+E]'])[1]")).click();

		String lastName = driver.findElement(By.cssSelector("[name='lastname']")).getAttribute("value");
		System.out.println(lastName);
		
		driver.quit();

	}

}
