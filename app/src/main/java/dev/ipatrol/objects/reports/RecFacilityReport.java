package dev.ipatrol.objects.reports;

import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;

/**
 * Created by Justin.vanHeek on 6/20/2017.
 */

public class RecFacilityReport extends Report {

    private String events;
    private String vehicles;
    private String unusual;

    public RecFacilityReport(String t, Bitmap i, Location loc, String n, String e, String v, String u) {
        super(ReportType.RecFacility, t, i, loc, n);
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
