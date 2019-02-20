package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import basePackage.BrowserManager;

public class DataReader extends BrowserManager {

	public static String data;
	public static Sheet sh;

	public static void getExcelSheet(String sheetNo) throws IOException {
		FileInputStream fin = new FileInputStream(new File(CURRENTDIR + "\\DataEngine\\Data.xls"));
		HSSFWorkbook wb = new HSSFWorkbook(fin);
		sh = wb.getSheet(sheetNo);
	}

	public static String getCellValue(int rowNum, int columnNum) {
		Cell cell = sh.getRow(rowNum).getCell(columnNum);
		data = cell.getStringCellValue();
		// System.out.println(data);
		return data;
	}

	// public static void main(String[] args) throws IOException {
	// getExcelSheet("Sheet1");
	// getCellValue(1, 3);
	// }
}
