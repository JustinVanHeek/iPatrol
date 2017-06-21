package dev.ipatrol.objects.reports;

import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;

/**
 * Created by Justin.vanHeek on 6/20/2017.
 */

public class ShopCenterReport extends Report {

    public ShopCenterReport(String t, Bitmap i, Location loc, String n) {
        super(ReportType.ShopCenter, t, i, loc, n);
    }


}
