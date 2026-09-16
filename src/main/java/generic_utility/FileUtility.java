package generic_utility;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileUtility {

	// Method to read json data
	public static String getDataFromJsonFile(String key) throws IOException, ParseException {
		FileReader fr = new FileReader("./src/test/resources/Commondata.json");

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(fr);

		JSONObject jobj = (JSONObject) obj;

		String value = jobj.get(key).toString();
		return value;
	}

	public static String readDataFromExcel(String sheet, int r, int c) throws EncryptedDocumentException, IOException {

		FileInputStream fis = new FileInputStream("./src/test/resources/ExcelData.xlsx");

		Workbook wb = WorkbookFactory.create(fis);

		Sheet sh = wb.getSheet(sheet);

		Row row = sh.getRow(r);

		Cell cell = row.getCell(c);
		String value = cell.getStringCellValue();
		return value;
	}

}
