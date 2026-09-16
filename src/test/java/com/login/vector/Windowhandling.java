package com.login.vector;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

public class Windowhandling {
	public static void main(String[] args) throws IOException, InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/windows");

		String parentWindow = driver.getWindowHandle();

		driver.findElement(By.linkText("Click Here")).click();

		Set<String> allWindow = driver.getWindowHandles();
		System.out.println(allWindow.size());
		System.out.println(allWindow);

		for (String win : allWindow) {
			if (!win.contains(parentWindow)) {
				driver.switchTo().window(win);

				System.out.println(driver.findElement(By.xpath("//h3[text()='New Window']")).getText());

				System.out.println("PASS");

			}
		}

		Thread.sleep(3000);

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);

		File dest = new File("./Screenshot/windowhandling.png");
		FileHandler.copy(src, dest);

		driver.quit();
	}

}
