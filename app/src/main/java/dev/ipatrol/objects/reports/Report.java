package dev.ipatrol.objects.reports;

import android.graphics.Bitmap;
import android.location.Location;
import android.media.Image;
import android.net.Uri;

import java.util.Calendar;

import dev.ipatrol.LocationUtils;

/**
 * Created by Justin.vanHeek on 6/19/2017.
 */

public class Report {


    public Report(ReportType type, String t, Bitmap i, Location loc, String n) {
        reportType = type;
        title = t;
        image = i;
        location = loc;
        notes = n;
        time = Calendar.getInstance();
    }

    public enum ReportType {AbandonedCar, Alcohol, CrimePrevention, Graffiti, IllegalDumping, Mischief, Other, RecklessDriving, TFAGlass, BushParty, Business, Church, GasStation, OpenDoor, OpenGarage, OpenGate, OpenWindow, Park, RecFacility, School, ShopCenter, Shop, Suspicious};

    protected String title;
    protected ReportType reportType;
    protected Bitmap image;
    protected Location location;
    protected String notes;
    protected Calendar time;

    public Calendar getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public String getNotes() {
        return notes;
    }

    public Location getLocation() {
        return location;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getAddress() {
        return LocationUtils.getClosestAddress(location);
    }

    public double getLatitude() {
        return location.getLatitude();
    }

    public double getLongitude() {
        return location.getLongitude();
    }


}
