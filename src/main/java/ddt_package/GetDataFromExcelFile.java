package ddt_package;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class GetDataFromExcelFile {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// TODO Auto-generated method stub
		FileInputStream fs = new FileInputStream("./src/test/resources/ExcelData.xlsx");
		
		Workbook wb = WorkbookFactory.create(fs);
		

		Sheet ws = wb.getSheet("Org");
		
		Row row = ws.getRow(2);
		Cell cell = row.getCell(0);
		
		System.out.println(cell.getStringCellValue());
		
		fs.close();
		wb.close();
	}
}
