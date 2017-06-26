package dev.ipatrol;

import java.util.ArrayList;

/**
 * Created by Justin.vanHeek on 6/22/2017.
 */

public class PDFTable {

    private ArrayList<PDFRow> rows;

    public int r1;
    public int g1;
    public int b1;

    public int r2;
    public int g2;
    public int b2;

    public PDFTable(int r1, int g1, int b1, int r2, int g2, int b2) {
        this.r1 = r1;
        this.g1 = g1;
        this.b1 = b1;

        this.r2 = r2;
        this.g2 = g2;
        this.b2 = b2;
    }

    public void addRow(PDFRow r) {
        rows.add(r);
    }

    public ArrayList<PDFRow> getRows() {
        return rows;
    }



}
