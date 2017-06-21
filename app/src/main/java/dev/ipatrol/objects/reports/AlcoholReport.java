package dev.ipatrol.objects.reports;

import android.graphics.Bitmap;
import android.location.Location;
import android.media.Image;
import android.net.Uri;

/**
 * Created by Justin.vanHeek on 6/20/2017.
 */

public class AlcoholReport extends Report {

    public AlcoholReport(String t, Bitmap i, Location loc, String n) {
        super(ReportType.Alcohol, t, i, loc, n);
    }


}
