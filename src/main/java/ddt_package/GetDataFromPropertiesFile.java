package ddt_package;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GetDataFromPropertiesFile {

	public static void main(String[] args) throws IOException {

		FileInputStream fis = new FileInputStream("./src/test/resources/CommonData.properties");

		Properties prop = new Properties();

		prop.load(fis);

		String browserValue = prop.getProperty("browser");
		String urlValue = prop.getProperty("url");

		System.out.println(browserValue);
		System.out.print(urlValue);
	

	}

}
