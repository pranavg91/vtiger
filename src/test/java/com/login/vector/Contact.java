package com.login.vector;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Contact {

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

		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@class='hdrTabBg']//td//tr/td/a[text()='Contacts']")).click();
		
		driver.findElement(By.cssSelector("[title='Create Contact...']")).click();
		
		
		WebElement enterLastName = driver.findElement(By.cssSelector("[name='lastname']"));
		enterLastName.sendKeys("TestContact");
		
		driver.findElement(By.cssSelector("[title='Save [Alt+S]']")).click();
		
		System.out.println("Pass");
		
		driver.quit();

	}

}
