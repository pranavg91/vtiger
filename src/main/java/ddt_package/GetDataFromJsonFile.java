package ddt_package;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.TakesScreenshot;
import org.xml.sax.Parser;

public class GetDataFromJsonFile {
	public static void main(String[] args) throws IOException, ParseException {
		
	
	
	FileReader fr = new FileReader("./src/test/resources/commondata.json");
	
	
	JSONParser obj = new JSONParser();
	Object ob = obj.parse(fr);
	
	
	JSONObject jj = (JSONObject)ob;
	
	
	String url = jj.get("url").toString();
	System.out.println(url);

	
	
	}

}
