package com.hrbsys.tools;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/***
 * 将Excel中的内容导入到数据库工具类
 * 
 * @author wangjiucheng
 * 
 */
public class ExcelUtils
{
	public static int getIntCellValue(Row row, int index)
	{
		int rtn = 0;
		try
		{
			Cell cell = row.getCell((short) index);
			rtn = (int) cell.getNumericCellValue();
		} catch (RuntimeException e)
		{
		}
		return rtn;
	}

	public static String getStringValue(Row row, int index)
	{
		String rtn = "";
		try
		{
			Cell cell = row.getCell((short) index);
			rtn = cell.getRichStringCellValue().getString();
		} catch (RuntimeException e)
		{
		}
		return rtn;
	}

}
