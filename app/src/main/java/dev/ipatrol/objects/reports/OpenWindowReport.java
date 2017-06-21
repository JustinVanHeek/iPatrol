package dev.ipatrol.objects.reports;

import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;

/**
 * Created by Justin.vanHeek on 6/20/2017.
 */

public class OpenWindowReport extends Report {

    public OpenWindowReport(String t, Bitmap i, Location loc, String n) {
        super(ReportType.OpenWindow, t, i, loc, n);
    }


}
