package com.login.vector;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Organizations {

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
		
		driver.findElement(By.xpath("//*[@class='hdrTabBg']//td//tr/td/a[text()='Organizations']")).click();
		
		driver.findElement(By.cssSelector("[title='Create Organization...']")).click();
		
		
		driver.findElement(By.cssSelector("[name='accountname']")).sendKeys("Test");
		
		Thread.sleep(2000);
		
		driver.findElement(By.cssSelector("[title='Save [Alt+S]']")).click();
		
		Thread.sleep(2000);
		
		System.out.println("Pass");
		
		driver.quit();

	}

}
