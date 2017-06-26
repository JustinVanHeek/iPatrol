package dev.ipatrol;

import android.graphics.Color;

import java.util.ArrayList;

/**
 * Created by Justin.vanHeek on 6/26/2017.
 */

public class PDFTable {

    private int borderColor = Color.BLACK;
    private boolean border = false;


    private ArrayList<PDFRow> rows = new ArrayList<PDFRow>();

    public PDFTable() {

    }

    public void addRow(PDFRow row) {
        rows.add(row);
    }

    public ArrayList<PDFRow> getRows() {
        return rows;
    }

    public void setBorder(boolean b, int color) {
        border = b;
        borderColor = color;
    }

    public boolean hasBorder() {
        return border;
    }

    public int getBorderColor() {
        return borderColor;
    }
}
