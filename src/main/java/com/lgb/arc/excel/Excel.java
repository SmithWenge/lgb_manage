package com.lgb.arc.excel;

/**
 * Created by Samuel on 16/4/8.
 */
public class Excel {
    private String sheetName;
    private int sheetNum;

    public Excel() {

    }

    public Excel(String sheetName, int sheetNum) {
        this.sheetName = sheetName;
        this.sheetNum = sheetNum;
    }
    public String getSheetName() {
        return sheetName;
    }
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }
    public int getSheetNum() {
        return sheetNum;
    }
    public void setSheetNum(int sheetNum) {
        this.sheetNum = sheetNum;
    }
}