package dev.ipatrol.objects.reports;

import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;

/**
 * Created by Justin.vanHeek on 6/20/2017.
 */

public class BushPartyReport extends Report {

    private int people;
    private int vehicles;
    private boolean drugs;
    private boolean alcohol;
    private boolean fire;

    public BushPartyReport(String t, Bitmap i, Location loc, String n, int p, int v, boolean d, boolean a, boolean f) {
        super(ReportType.BushParty, t, i, loc, n);
        people = p;
        vehicles = v;
        drugs = d;
        alcohol = a;
        fire = f;
    }


    public int getNumOfPeople() {
        return people;
    }

    public int getNumOfVehicles() {
        return vehicles;
    }

    public boolean isDrugs() {
        return drugs;
    }

    public boolean isAlcohol() {
        return alcohol;
    }

    public boolean isFire() {
        return fire;
    }
}
