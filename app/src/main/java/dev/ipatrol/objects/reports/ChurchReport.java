package dev.ipatrol.objects.reports;

import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.StrictMode;

/**
 * Created by Justin.vanHeek on 6/20/2017.
 */

public class ChurchReport extends Report {

    private String events;
    private String vehicles;
    private String unusual;

    public ChurchReport(String t, Bitmap i, Location loc, String n, String e, String v, String u) {
        super(ReportType.Church, t, i, loc, n);
        events = e;
        vehicles = v;
        unusual = u;
    }


    public String getEvents() {
        return events;
    }

    public String getOddVehicles() {
        return vehicles;
    }

    public String getUnusualActivities() {
        return unusual;
    }
}
