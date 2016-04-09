package com.lgb.arc.excel.mapper;

import com.lgb.arc.excel.ExcelMapper;
import com.lgb.function.admin.course.leader.CourseLeader;
import com.lgb.function.support.dictionary.impl.DefaultDictionaryManager;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class CourseLeaderExcelMapper implements ExcelMapper<CourseLeader> {
    @Override
    public void mapToExcel(WritableSheet sheet, CourseLeader courseLeader, WritableCellFormat wcf, int rowNum) {
        try {
            DefaultDictionaryManager manager = DefaultDictionaryManager.getInstance();

            sheet.addCell(new Label(0, rowNum, courseLeader.getCardNum()));
            sheet.addCell(new Label(1, rowNum, courseLeader.getLeaderName()));
            sheet.addCell(new Label(2, rowNum, manager.dictionary(courseLeader.getClassRoom(), "courseRoom").getItemValue()));
            sheet.addCell(new Label(3, rowNum, courseLeader.getDepartmentName()));
            sheet.addCell(new Label(4, rowNum, courseLeader.getMajorName()));
            sheet.addCell(new Label(5, rowNum, courseLeader.getCourseName()));
            sheet.addCell(new Label(6, rowNum, manager.dictionary(courseLeader.getGender(), "gender").getItemValue()));
            sheet.addCell(new Label(7, rowNum, courseLeader.getBirthday().toString()));
            sheet.addCell(new Label(8, rowNum, courseLeader.getTelOne()));
            sheet.addCell(new Label(9, rowNum, courseLeader.getTelTwo()));
            sheet.addCell(new Label(10, rowNum, String.valueOf(courseLeader.getSiteNum())));

        } catch (RowsExceededException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (WriteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
