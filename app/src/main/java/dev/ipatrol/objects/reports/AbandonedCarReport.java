package dev.ipatrol.objects.reports;

import android.graphics.Bitmap;
import android.location.Location;
import android.media.Image;
import android.net.Uri;

/**
 * Created by Justin.vanHeek on 6/20/2017.
 */

public class AbandonedCarReport extends Report {

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColour() {
        return colour;
    }

    public Direction getFacing() {
        return facing;
    }

    public String getPlate() {
        return plate;
    }

    public boolean isExpiredPlate() {
        return expired;
    }

    public boolean isFlatTire() {
        return flat;
    }

    public String getCondition() {
        return condition;
    }


    public enum Direction {N, NE, E, SE, S, SW, W, NW}

    private String make;
    private String model;
    private String colour;
    private Direction facing;
    private String plate;
    private boolean expired;
    private boolean flat;
    private String condition;


    public AbandonedCarReport(String t, Bitmap i, Location loc, String n, String ma, String mo, String c, Direction d, String p, boolean e, boolean f, String con) {
        super(ReportType.AbandonedCar, t, i, loc, n);
        make = ma;
        model = mo;
        colour = c;
        facing = d;
        plate = p;
        expired = e;
        flat = f;
        condition = con;
    }
}
