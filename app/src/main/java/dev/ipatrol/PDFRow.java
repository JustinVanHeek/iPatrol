package dev.ipatrol;

import android.graphics.Color;

import java.util.ArrayList;

/**
 * Created by Justin.vanHeek on 6/26/2017.
 */

public class PDFRow {

    private String[] cells;
    private int color = Color.WHITE;


    public PDFRow() {
        //Blank row
        cells = new String[0];
    }

    public PDFRow(String[] cells) {
        this.cells = cells;
    }

    public PDFRow(String[] cells, int color) {
        this.cells = cells;
        this.color = color;
    }

    public String[] getCells() {
        return cells.clone();
    }

    public int getColor() {
        return color;
    }

    public void setColor(int c) {
        color = c;
    }
}
