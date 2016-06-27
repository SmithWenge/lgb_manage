package com.lgb.arc.excel.into;

import com.lgb.function.teaScore.ScoreModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;

public class ScoreModelExcelMapper implements ExcelMapper<ScoreModel>{
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

    @Override
    public ScoreModel mapStringArray(HSSFRow row) {
        this.mapExcel(row);
        ScoreModel scoreModel = new ScoreModel();

        scoreModel.setStudentCardNum(strings[0]);
        scoreModel.setCourseName(strings[1]);
        scoreModel.setStuScore(strings[2]);

        return scoreModel;
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
}
