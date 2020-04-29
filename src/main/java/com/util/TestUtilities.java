package com.util;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestUtilities {
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	String c1_1;
	
	public static int getRowCount(String xlfile,String xlsheet) throws IOException
	{
	fi=new FileInputStream(xlfile);
	wb=new XSSFWorkbook(fi);
	ws=wb.getSheet(xlsheet);
	int rowcount=ws.getLastRowNum();
	wb.close();
	fi.close();
	return rowcount;
	}
	
	public static int getCellCount(String xlfile,String xlsheet,int rownum) throws IOException
	{
	fi=new FileInputStream(xlfile);
	wb=new XSSFWorkbook(fi);
	ws=wb.getSheet(xlsheet);
	row=ws.getRow(rownum);
	int cellcount=row.getLastCellNum();
	wb.close();
	fi.close();
	return cellcount;
	}
	
	public static String getCellData(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
	{
	fi=new FileInputStream(xlfile);
	wb=new XSSFWorkbook(fi);
	ws=wb.getSheet(xlsheet);
	row=ws.getRow(rownum);
	cell=row.getCell(colnum);
	String data;
	try {
		
		DataFormatter formatter=new DataFormatter();
		String cellData=formatter.formatCellValue(cell);
		return cellData;
	}
	catch (Exception e) {
	data ="";
	}
	wb.close();
	fi.close();
	return data;
	}
	
	
	public static void setCellData(String xlfile,String xlsheet,int rownum,int colnum,String data) throws IOException
	{
	fi=new FileInputStream(xlfile);
	wb=new XSSFWorkbook(fi);
	ws=wb.getSheet(xlsheet);
	row=ws.getRow(rownum);
	cell=row.createCell(colnum);
	cell.setCellValue(data);
	fo=new FileOutputStream(xlfile);
	wb.write(fo);
	wb.close();
	fi.close();
	fo.close();
	}
	
	public static void updateResult(String xlfile, String xlsheet, String testCaseName, String testStatus) throws IOException {

		try {
			fi=new FileInputStream(xlfile);
			wb=new XSSFWorkbook(fi);
           	ws=wb.getSheet(xlsheet);
			int row = ws.getLastRowNum() + 1;
			// count number of active columns in row
			for (int i = 1; i < row; i++) {
				XSSFRow r = ws.getRow(i);
				String ce = r.getCell(0).getStringCellValue();
				if (ce.contains(testCaseName)) {
					r.createCell(2).setCellValue(testStatus);
					fi.close();
					System.out.println("result updated");
					fo=new FileOutputStream(xlfile);
					wb.write(fo);
					fo.close();
					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
