package com.lgb.arc.excel.mapper;

import com.lgb.arc.excel.ExcelMapper;
import com.lgb.function.admin.finance.Finance;
import com.lgb.function.support.dictionary.impl.DefaultDictionaryManager;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class FinanceExcelMapper implements ExcelMapper<Finance>{
    public void mapToExcel(WritableSheet sheet, Finance finance, WritableCellFormat wcf, int rowNum) {
        try {
            sheet.addCell(new Label(0, rowNum, String.valueOf(finance.getStudentCourseId())));
            sheet.addCell(new Label(1, rowNum, finance.getCardNum()));
            sheet.addCell(new Label(2, rowNum, finance.getStuName()));
            DefaultDictionaryManager manager = DefaultDictionaryManager.getInstance();
            sheet.addCell(new Label(3, rowNum, manager.dictionary(finance.getSignUpComeFrom(), "signUpComeFrom").getItemValue()));
            sheet.addCell(new Label(4, rowNum, finance.getDepartmentName()));
            sheet.addCell(new Label(5, rowNum, finance.getMajorName()));
            sheet.addCell(new Label(6, rowNum, finance.getCourseName()));
            sheet.addCell(new Label(7, rowNum, String.valueOf(finance.getCourseTuition())));
            sheet.addCell(new Label(8, rowNum, manager.dictionary(finance.getCourseDiscount(), "courseDiscount").getItemValue()));
            sheet.addCell(new Label(9, rowNum, finance.getFinanceUser()));
            sheet.addCell(new Label(10, rowNum, finance.getFinanceTime().toString()));
            sheet.addCell(new Label(11, rowNum, String.valueOf(finance.getActualTuition())));

        } catch (RowsExceededException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (WriteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


