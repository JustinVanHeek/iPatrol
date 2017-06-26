package dev.ipatrol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Justin.vanHeek on 6/22/2017.
 */

public class PDFRow {

    public ArrayList<String> cells;

    public PDFRow(ArrayList<String> c) {
        cells = c;
    }

    public PDFRow(String[] c) {
        cells = new ArrayList<String>(Arrays.asList(c));

    }

    public int getHeight() {
        return 12;
    }

}
