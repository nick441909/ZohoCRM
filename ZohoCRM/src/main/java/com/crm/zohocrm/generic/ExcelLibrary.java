package com.crm.zohocrm.generic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelLibrary {
	public static String getStringData(String sheetName, int rowNumber, int cellNumber) {
		try {
			FileInputStream file = new FileInputStream(IAutoConstants.XL_PATH);
			return WorkbookFactory.create(file).getSheet(sheetName).getRow(rowNumber).getCell(cellNumber).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getStringData1(String sheetName, int rowNumber, int cellNumber) {
		try {
			FileInputStream file = new FileInputStream(IAutoConstants.XL_PATH1);
			return WorkbookFactory.create(file).getSheet(sheetName).getRow(rowNumber).getCell(cellNumber).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getStringData2(String sheetName, int rowNumber, int cellNumber) {
		try {
			FileInputStream file = new FileInputStream(IAutoConstants.XL_PATH2);
			return WorkbookFactory.create(file).getSheet(sheetName).getRow(rowNumber).getCell(cellNumber).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Double getNumericData(String sheetName, int rowNumber, int cellNumber) {
		try {
			FileInputStream file = new FileInputStream(IAutoConstants.XL_PATH);
			return WorkbookFactory.create(file).getSheet(sheetName).getRow(rowNumber).getCell(cellNumber)
					.getNumericCellValue();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Boolean getBooleanData(String sheetName, int rowNumber, int cellNumber) {
		try {
			FileInputStream file = new FileInputStream(IAutoConstants.XL_PATH);
			return WorkbookFactory.create(file).getSheet(sheetName).getRow(rowNumber).getCell(cellNumber)
					.getBooleanCellValue();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static LocalDateTime getDate(String sheetName, int rowNumber, int cellNumber) {
		try {
			FileInputStream file = new FileInputStream(IAutoConstants.XL_PATH);
			return WorkbookFactory.create(file).getSheet(sheetName).getRow(rowNumber).getCell(cellNumber)
					.getLocalDateTimeCellValue();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String[][] getMultipleData(String sheet){
		try {
			FileInputStream file = new FileInputStream(IAutoConstants.XL_PATH2);
			Workbook workbook = WorkbookFactory.create(file);
		   Sheet Sheet1 = workbook.getSheet(sheet);
		   int rowcount = Sheet1.getPhysicalNumberOfRows();
		   int cellcount = Sheet1.getRow(2).getPhysicalNumberOfCells();
		   String[][] sr=new String[rowcount][cellcount];
		   for(int i=1,k=0;i<=rowcount-1;i++,k++) {
			   for(int j=0;j<=cellcount-1;j++) {
				   sr[k][j]=Sheet1.getRow(i).getCell(j).toString();
			   }
		   }
		   return sr;
		} catch (Exception e) {
		
			e.printStackTrace();
			return null;
		}
	}
}
