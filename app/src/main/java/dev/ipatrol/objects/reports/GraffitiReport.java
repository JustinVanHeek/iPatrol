package dev.ipatrol.objects.reports;

import android.graphics.Bitmap;
import android.location.Location;
import android.media.Image;
import android.net.Uri;

/**
 * Created by Justin.vanHeek on 6/20/2017.
 */

public class GraffitiReport extends Report {

    public GraffitiReport(String t, Bitmap i, Location loc, String n) {
        super(ReportType.Graffiti, t, i, loc, n);
    }


}
