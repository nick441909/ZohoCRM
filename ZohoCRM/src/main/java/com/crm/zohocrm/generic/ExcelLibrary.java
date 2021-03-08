package com.crm.zohocrm.generic;

import java.io.FileInputStream;
import java.time.LocalDateTime;

import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelLibrary {
	public static String getStringData(String sheetName, int rowNumber, int cellNumber) {
		try {
			FileInputStream file = new FileInputStream(IAutoConstants.XL_PATH);
			return WorkbookFactory.create(file).getSheet(sheetName).getRow(rowNumber)
											.getCell(cellNumber).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Double getNumericData(String sheetName, int rowNumber, int cellNumber) {
		try {
			FileInputStream file = new FileInputStream(IAutoConstants.XL_PATH);
			return WorkbookFactory.create(file).getSheet(sheetName).getRow(rowNumber)
											.getCell(cellNumber).getNumericCellValue();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Boolean getBooleanData(String sheetName, int rowNumber, int cellNumber) {
		try {
			FileInputStream file = new FileInputStream(IAutoConstants.XL_PATH);
			return WorkbookFactory.create(file).getSheet(sheetName).getRow(rowNumber)
											.getCell(cellNumber).getBooleanCellValue();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static LocalDateTime getDate(String sheetName, int rowNumber, int cellNumber) {
		try {
			FileInputStream file = new FileInputStream(IAutoConstants.XL_PATH);
			return WorkbookFactory.create(file).getSheet(sheetName).getRow(rowNumber)
											.getCell(cellNumber).getLocalDateTimeCellValue();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
