package com.ecom.utilites;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataExtracter {

	public  Object[][] getExcelData(String path, String sheetName) throws Exception {

		DataFormatter formatter = new DataFormatter();
		FileInputStream fis = new FileInputStream(path);
		try (XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
			XSSFSheet sheet = workbook.getSheet(sheetName);
			int rowCount = sheet.getPhysicalNumberOfRows();
			int colCount = sheet.getRow(0).getLastCellNum();
			Object data[][] = new Object[rowCount-1][colCount];

			for (int i = 0; i < rowCount-1; i++) {

				for (int j = 0; j < colCount; j++) {

					XSSFCell cell = sheet.getRow(i+1).getCell(j);
					System.out.println(cell);
					

					data[i][j] = formatter.formatCellValue(cell);

				}
			}
			workbook.close();
			fis.close();
			return data;
		}
	}
}
