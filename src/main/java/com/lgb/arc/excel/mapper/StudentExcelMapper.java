package com.lgb.arc.excel.mapper;

import com.lgb.arc.excel.ExcelMapper;
import com.lgb.function.admin.student.StudentUser;
import com.lgb.function.support.dictionary.impl.DefaultDictionaryManager;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class StudentExcelMapper implements ExcelMapper<StudentUser> {
    public void mapToExcel(WritableSheet sheet, StudentUser studentUser, WritableCellFormat wcf, int rowNum) {
        try {
            sheet.addCell(new Label(0, rowNum, studentUser.getStuCardNum()));
            sheet.addCell(new Label(1, rowNum, studentUser.getStuName()));
            DefaultDictionaryManager manager = DefaultDictionaryManager.getInstance();
            sheet.addCell(new Label(2, rowNum, manager.dictionary(studentUser.getStuGender(), "gender").getItemValue()));
            sheet.addCell(new Label(3, rowNum, studentUser.getStuBirthday().toString()));
            sheet.addCell(new Label(4, rowNum, studentUser.getStuTelOne()));
            sheet.addCell(new Label(5, rowNum, studentUser.getStuTelTwo()));
            sheet.addCell(new Label(7, rowNum, studentUser.getStuOldWorkPlaceName()));
            sheet.addCell(new Label(6, rowNum, studentUser.getStuLocation()));

        } catch (RowsExceededException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (WriteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
