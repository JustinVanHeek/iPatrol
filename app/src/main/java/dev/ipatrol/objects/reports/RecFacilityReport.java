package dev.ipatrol.objects.reports;

import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;

/**
 * Created by Justin.vanHeek on 6/20/2017.
 */

public class RecFacilityReport extends Report {

    public RecFacilityReport(String t, Bitmap i, Location loc, String n) {
        super(ReportType.RecFacility, t, i, loc, n);
    }


}
