package com.qa.api.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	private static String TEST_DATA_PATH =".\\src\\test\\resources\\APITestdata.xlsx";
	
	private static Workbook book;
	private static Sheet sheet;
	public static Object[][] readData(String sheetname) throws Exception {
		Object data [][]=null;
		FileInputStream ip = new FileInputStream(TEST_DATA_PATH);
		
		book= WorkbookFactory.create(ip);
		sheet=book.getSheet(sheetname);
		data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j]=sheet.getRow(i+1).getCell(j).toString();
				
			}
			
		}
		return data;
	}
	

}
