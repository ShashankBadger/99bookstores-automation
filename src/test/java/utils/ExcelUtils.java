package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils
{
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private String filePath;
	
	public ExcelUtils(String filePath, String sheetName)
	{
		this.filePath = filePath;
		try 
		{
			FileInputStream fis = new FileInputStream(filePath);
			this.workbook = new XSSFWorkbook(fis);
			this.sheet = workbook.getSheet(sheetName);
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Failed to load the file : "+e.getMessage());
		}
	}
	
	public String readCellData(int rowNum, int colNum)
	{
		Row row = sheet.getRow(rowNum);
		DataFormatter formatter = new DataFormatter();
		Cell cell = row.getCell(colNum);
		
		if(row == null || cell == null)
			return "";		
		return formatter.formatCellValue(cell);
	}
	
	public int getRowCount()
	{
		return sheet.getLastRowNum();
	}
	
	public void writeCellData(int rowNum, int col, String value)
	{
		try {
			Row row = sheet.getRow(rowNum);
			if(row == null)
				row = sheet.createRow(rowNum);
			
			Cell cell = row.getCell(col);
			if(cell == null)
				cell = row.createCell(col);
			
			cell.setCellValue(value);
			
			FileOutputStream fos = new FileOutputStream(filePath);
			workbook.write(fos);
			fos.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Unable to write in excel : "+e.getMessage());
		}
	}
	
	public void closeWorkbook() throws IOException
	{
		workbook.close();
	}
}
