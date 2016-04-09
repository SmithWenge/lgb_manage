package com.lgb.arc.excel;

/**
 * Created by Samuel on 16/4/8.
 */

import jxl.format.Colour;

public class CellStyle {
    private String font;
    private int fontSize;
    private Colour bgColor = Colour.WHITE;

    public CellStyle() {}

    public CellStyle(String font, int fontSize) {
        this.font = font;
        this.fontSize = fontSize;
    }

    public Colour getBgColor() {
        return bgColor;
    }

    public void setBgColor(Colour bgColor) {
        this.bgColor = bgColor;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }
}