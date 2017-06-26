package dev.ipatrol.objects;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.location.Location;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import dev.ipatrol.LocationUtils;
import dev.ipatrol.PDFRow;
import dev.ipatrol.PDFTable;
import dev.ipatrol.PDFUtil;
import dev.ipatrol.objects.reports.Report;

/**
 * Created by Justin.vanHeek on 6/19/2017.
 */

public class Patrol {

    public static String pdfPath = "test.pdf";

    public enum WeatherCondition {Sunny, Cloudy, Rain, Snow};
    public enum LightingCondition {Dawn, Day, Dusk, Artificial, Night};
    public enum PatrolType {Foot, Bike, Car};

    private ArrayList<String> patrollers = new ArrayList<>();
    private Calendar startTime = Calendar.getInstance();
    private Calendar endTime;
    private Location startLocation;
    private Location endLocation;
    private WeatherCondition conditionWeather;
    private LightingCondition conditionLight;
    private PatrolType patrolType;
    private ArrayList<Report> reports = new ArrayList<>();

    private long getDuration() {
        long duration = endTime.getTimeInMillis() - startTime.getTimeInMillis();
        long minutes = duration/60000;
        return minutes;
    }

    private double getDistance() {
        //TODO
        return 0;
    }

    private int getTotalEventsRecorded() {
        return reports.size();
    }

    public void addPatroller(String name) {
        patrollers.add(name);
    }

    public void removePatroller(String name) {
        patrollers.remove(name);
    }

    public void clearPatrollers() {
        patrollers.clear();
    }

    public ArrayList<String> getPatrollers() {
        return (ArrayList<String>) patrollers.clone();
    }

    public void setStartTime(Calendar time) {
        startTime = (Calendar) time.clone();
    }

    public Calendar getStartTime() {
        return (Calendar) startTime.clone();
    }

    public void setEndTime(Calendar time) {
        endTime = (Calendar) time.clone();
    }

    public Calendar getEndTime() {
        return (Calendar) endTime.clone();
    }

    public void setStartLocation(Location loc) {
        startLocation = loc;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public void setEndLocation(Location loc) {
        endLocation = loc;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setWeather(WeatherCondition weather) {
        conditionWeather = weather;
    }

    public WeatherCondition getWeather() {
        return conditionWeather;
    }

    public void setLighting(LightingCondition light) {
        conditionLight = light;
    }

    public LightingCondition getLighting() {
        return conditionLight;
    }

    public void setPatrolType(PatrolType patrol) {
        patrolType = patrol;
    }

    public PatrolType getPatrolType() {
        return patrolType;
    }

    public void addReport(Report report) {
        reports.add(report);
    }

    public void removeReport(Report report) {
        reports.remove(report);
    }

    public void clearReports() {
        reports.clear();
    }

    public ArrayList<Report> getReports() {
        return (ArrayList<Report>) reports.clone();
    }

    public void printFullReport() {
        Log.i("Report", "--Start Of Full Patrol Report--");
        Log.i("Report", "   Patrollers:");
        for (String patroller : getPatrollers()) {
            Log.i("Report", "       " + patroller);
        }
        Log.i("Report", "   Start Time: " + getStartTime().getTime().toString());
        Log.i("Report", "   End Time: " + getEndTime().getTime().toString());
        Log.i("Report", "   Duration: " + getDuration());
        Log.i("Report", "   Start Location: " + LocationUtils.getClosestAddress(startLocation));
        Log.i("Report", "   End Location: " + LocationUtils.getClosestAddress(endLocation));
        Log.i("Report", "   Distance: " + getDistance());
        Log.i("Report", "   Conditions: " + getWeather().toString() + ", " + getLighting().toString());
        Log.i("Report", "   Patrol Type: " + getPatrolType().name());
        Log.i("Report", "   Reports: ");
        for (Report report : getReports()) {
            Log.i("Report", "       Type: " + report.getReportType().name());
            Log.i("Report", "       Location: " + LocationUtils.getClosestAddress(report.getLocation()));
            Log.i("Report", "       Notes: " + report.getNotes());
        }
        Log.i("Report", "--End Of Full Patrol Report--");
    }

    public void finishReport() {
        setEndTime(Calendar.getInstance());
        setEndLocation(null);

        generatePDF();

    }

    private void generatePDF() {

            // create a new document
            PdfDocument document = new PdfDocument();

            // crate a page description
            PdfDocument.PageInfo pageInfo =
                    new PdfDocument.PageInfo.Builder(100, 100, 1).create();

            // start a page
            PdfDocument.Page page = document.startPage(pageInfo);

            Canvas canvas = page.getCanvas();

            Paint paint = new Paint();
            paint.setColor(Color.RED);

            canvas.drawCircle(50, 50, 30, paint);

            // finish the page
            document.finishPage(page);

            // Create Page 2
            pageInfo = new PdfDocument.PageInfo.Builder(500, 500, 2).create();
            page = document.startPage(pageInfo);
            canvas = page.getCanvas();
            paint = new Paint();
            paint.setColor(Color.BLUE);
            canvas.drawCircle(200, 200, 100, paint);
            document.finishPage(page);

            // write the document content
            String targetPdf = "/sdcard/test.pdf";
            File filePath = new File(Environment.getExternalStorageDirectory(),"test.pdf");
            //File filePath = new File(targetPdf);
            try {
                Log.d("iPatrol",filePath.getAbsolutePath());
                document.writeTo(new FileOutputStream(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // close the document
            document.close();




    }

}
