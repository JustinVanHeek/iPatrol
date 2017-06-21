package dev.ipatrol.objects.reports;

import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;

/**
 * Created by Justin.vanHeek on 6/20/2017.
 */

public class ShopReport extends Report {

    public ShopReport(String t, Bitmap i, Location loc, String n) {
        super(ReportType.Shop, t, i, loc, n);
    }


}
