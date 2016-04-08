package com.lgb.arc.excel;

/**
 * Created by Samuel on 16/4/8.
 */
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;

public interface ExcelMapper<T> {
    void mapToExcel(WritableSheet sheet, T t, WritableCellFormat wcf, int rowNum);
}

