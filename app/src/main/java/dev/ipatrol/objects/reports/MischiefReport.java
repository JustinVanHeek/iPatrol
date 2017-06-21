package dev.ipatrol.objects.reports;

import android.graphics.Bitmap;
import android.location.Location;
import android.media.Image;
import android.net.Uri;

/**
 * Created by Justin.vanHeek on 6/20/2017.
 */

public class MischiefReport extends Report {

    public MischiefReport(String t, Bitmap i, Location loc, String n) {
        super(ReportType.Mischief, t, i, loc, n);
    }


}
