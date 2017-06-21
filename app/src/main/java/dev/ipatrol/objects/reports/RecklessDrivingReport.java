package dev.ipatrol.objects.reports;

import android.graphics.Bitmap;
import android.location.Location;
import android.media.Image;
import android.net.Uri;

/**
 * Created by Justin.vanHeek on 6/20/2017.
 */

public class RecklessDrivingReport extends Report {

    public RecklessDrivingReport(String t, Bitmap i, Location loc, String n) {
        super(ReportType.RecklessDriving, t, i, loc, n);
    }


}
