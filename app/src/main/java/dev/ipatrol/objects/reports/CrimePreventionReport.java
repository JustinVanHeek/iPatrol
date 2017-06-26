package dev.ipatrol.objects.reports;

import android.graphics.Bitmap;
import android.location.Location;
import android.media.Image;
import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by Justin.vanHeek on 6/20/2017.
 */

public class CrimePreventionReport extends Report {

    private float value;
    private ArrayList<String> items;
    private boolean openWindow;
    private boolean tintedWindow;
    private boolean unlocked;
    private boolean securitySystem;
    private boolean expired;

    public CrimePreventionReport(String t, Bitmap i, Location loc, String n, float v, ArrayList<String> item, boolean open, boolean tint, boolean u, boolean s, boolean e) {
        super(ReportType.CrimePrevention, t, i, loc, n);
        value = v;
        items = item;
        openWindow = open;
        tintedWindow = tint;
        unlocked = u;
        securitySystem = s;
        expired = e;
    }

    public float getValue() {
        return value;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public boolean isOpenWindow() {
        return openWindow;
    }

    public boolean isTintedWindow() {
        return tintedWindow;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public boolean isSecuritySystem() {
        return securitySystem;
    }

    public boolean isExpiredPlate() {
        return expired;
    }
}
