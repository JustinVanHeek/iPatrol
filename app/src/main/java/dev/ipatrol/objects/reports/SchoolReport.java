package dev.ipatrol.objects.reports;

import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;

/**
 * Created by Justin.vanHeek on 6/20/2017.
 */

public class SchoolReport extends Report {

    public SchoolReport(String t, Bitmap i, Location loc, String n) {
        super(ReportType.School, t, i, loc, n);
    }


}
