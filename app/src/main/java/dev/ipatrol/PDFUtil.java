package dev.ipatrol;

import org.apache.pdfbox.contentstream.PDContentStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;

/**
 * Created by Justin.vanHeek on 6/22/2017.
 */

public class PDFUtil {

    private PDDocument doc;
    private PDPage page;
    public PDPageContentStream content;
    String path;

    public PDFUtil(String path) {
        this.path = path;
        doc = new PDDocument();
        page = new PDPage();
        try {
            content = new PDPageContentStream(doc, page);
            content.beginText();
            content.setLeading(14.5f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void println(String s) {
        try {
            content.showText(s);
            content.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTable(PDFTable table, int y, int x, int tableWidth) {
        int cellX = 0;
        boolean rgb2 = false;
        try {
            for (PDFRow row : table.getRows()) {
                if (rgb2) {content.setNonStrokingColor(table.r2, table.g2, table.b2);rgb2 = false;}
                else {content.setNonStrokingColor(table.r1, table.g1, table.b1);rgb2 = true;}
                content.addRect(y, x, tableWidth, row.getHeight());
                content.fill();
                for (String data : row.cells) {
                    content.setNonStrokingColor(0, 0, 0);
                    content.moveTo(y, x + cellX);
                    content.showText(data);
                    cellX = cellX + tableWidth/row.cells.size();
                }
                cellX = 0;
                y = y+row.getHeight();
            }
            content.moveTo(y, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            doc.save(path);
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
