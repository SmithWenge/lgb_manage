package com.lgb.arc.excel.into;

import com.lgb.arc.utils.DateUtils;
import com.lgb.function.admin.student.StudentUser;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;

public class StudentExcelMapper implements ExcelMapper<StudentUser> {
	private static String[] strings = null;
	
	//把excel表格中的一行数据读出来,放到一个<code>java.lang.String[]</code>数据中
	public String[] mapExcel(HSSFRow row) {
		strings = new String[row.getLastCellNum()];
		
		for (int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++) {
			HSSFCell cell = row.getCell(cellIndex);
			
			if (null != cell) {
				strings[cellIndex] = getTypeValue(cell);
			}
		}
		
		return strings;
	}

	private  String getTypeValue(HSSFCell cell) {
		switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_STRING:
				return cell.getStringCellValue();
			case HSSFCell.CELL_TYPE_BLANK:
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				//判断是够是日期类型
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					return  cell.getDateCellValue().toString();
				} else {
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					return cell.getStringCellValue();
				}
			default:
				return "";
		}

		return "";
	}

	public StudentUser mapStringArray(HSSFRow row) {
		this.mapExcel(row);
		
		StudentUser studentUser = new StudentUser();

		studentUser.setStuCardNum(strings[0]);
		studentUser.setStuName(strings[1]);
		studentUser.setStuTelOne(strings[2]);
		studentUser.setStuIdentifiedNum(strings[3]);
		studentUser.setStuOldWorkPlaceName(strings[4]);
		studentUser.setStuBirthday(DateUtils.dataFromStringToDate(strings[5]));
		studentUser.setStuLastEightNum(strings[6]);
		studentUser.setStuLocation(strings[7]);
		studentUser.setStudentStartDate(DateUtils.dataFromStringToDate(strings[8]));

		strings = null;
		
		return studentUser;
	}

}