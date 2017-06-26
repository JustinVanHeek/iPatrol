package dev.ipatrol;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Justin.vanHeek on 6/26/2017.
 */

public class PDFUtil {

    private static final int cellHeight = 12;
    private static final int textSize = 8;
    private int marginLeft = 0;
    private int margineRight = 0;
    private int marginTop = 0;
    private int marginBottom = 0;
    private PdfDocument document;
    private PdfDocument.Page page;
    private int pageNum = 1;
    private Canvas canvas;
    private int cursorX = 0;
    private int cursorY = 0;


    public void save() {
        // finish the page
        document.finishPage(page);
        // write the document content
        //File filePath = new File("/storage/1A17-360E/Android/data/test.pdf");
        File filePath = new File(Environment.getExternalStorageDirectory(),"report.pdf");
        try {
            Log.d("iPatrol",filePath.getAbsolutePath());
            document.writeTo(new FileOutputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // close the document
        document.close();
    }

    public void print(String text, int size) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(size);
        canvas.drawText(text,cursorX,cursorY+size,paint);
    }

    public void print(String text, Typeface type, int size) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTypeface(type);
        paint.setTextSize(size);
        canvas.drawText(text,cursorX,cursorY+textSize,paint);
    }

    public void print(String text) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(textSize);
        canvas.drawText(text,cursorX,cursorY+textSize,paint);
    }

    public void println(String text, Typeface type, int size) {
        print(text,type,size);
        newline(size);
    }

    public void println(String text, Typeface type) {
        print(text,type);
        newline();
    }

    public void print(String text, Typeface type) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTypeface(type);
        paint.setTextSize(textSize);
        canvas.drawText(text,cursorX,cursorY+textSize,paint);
    }

    public void drawLine() {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawLine(marginLeft,cursorY,canvas.getWidth()-margineRight,cursorY,paint);
        newline();
    }


    public void setCursor(int x, int y) {
        cursorX = x;
        cursorY = y;
    }

    public void moveCursor(int x, int y) {
        cursorX = cursorX + x;
        cursorY = cursorY + y;
    }

    public PDFUtil() {


        // create a new document
        document = new PdfDocument();

        // crate a page description
        PdfDocument.PageInfo pageInfo =
                new PdfDocument.PageInfo.Builder(500, 1000, 1).create();

        // start a page
        page = document.startPage(pageInfo);

        canvas = page.getCanvas();
    }

    public void createNewPage(){
        // finish the page
        document.finishPage(page);

        pageNum++;

        // crate a page description
        PdfDocument.PageInfo pageInfo =
                new PdfDocument.PageInfo.Builder(500, 1000, pageNum).create();

        // start a page
        page = document.startPage(pageInfo);

        canvas = page.getCanvas();

    }

    public void newline(int size) {
        setCursor(marginLeft, cursorY+size);
        if(cursorY > canvas.getHeight()) {
            createNewPage();
        }
    }

    public void newline() {
        setCursor(marginLeft, cursorY+textSize);
        if(cursorY > canvas.getHeight()) {
            createNewPage();
        }
    }

    public void println(String text, int size) {
        print(text,size);
        newline(size);
    }

    public void println(String text) {
        print(text);
        newline();
    }

    public void printCenterTable(int width, PDFTable table) {
        drawTable(canvas.getWidth()/2-width/2, width, table);
    }

    private void drawTable(int x, int width, PDFTable table) {

        Paint paint = new Paint();
        paint.setColor(table.getBorderColor());

        if (table.hasBorder()) {
            canvas.drawLine(x,cursorY,x+width,cursorY,paint);
        }

        for (PDFRow row : table.getRows()) {
            if (table.hasBorder()) {
                canvas.drawLine(x,cursorY,x,cursorY+cellHeight,paint);
                canvas.drawLine(x+width,cursorY,x+width,cursorY+cellHeight,paint);
            }
            int cellWidth;
            if (row.getCells().length == 0) {
                cellWidth = width;
            }
            else {
                cellWidth = width/row.getCells().length;
            }
            int column = 0;
            for (String cell : row.getCells()) {
                drawCell(x+cellWidth*column, cursorY, cellWidth, cell, row.getColor());
                column++;
            }
            setCursor(marginLeft, cursorY+cellHeight);
        }

        if (table.hasBorder()) {
            canvas.drawLine(x,cursorY,x+width,cursorY,paint);
        }
    }



    private void drawCell(int x, int y, int width, String data, int color) {
        Paint cellPaint = new Paint();
        cellPaint.setColor(color);
        //cellPaint.setStyle(Paint.Style.STROKE);

        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(textSize);


        canvas.drawRect(x,y,x+width,y+cellHeight,cellPaint);

        canvas.drawText(data,x+width/2,y+cellHeight/2+textSize/2,textPaint);


    }

    public void setMargins(int left, int right, int top, int bot) {
        marginLeft = left;
        margineRight = right;
        marginTop = top;
        marginBottom = bot;

        if(cursorX < marginLeft) {
            cursorX = marginLeft;
        }
        if(cursorY < marginTop) {
            cursorY = marginTop;
        }
    }
}
