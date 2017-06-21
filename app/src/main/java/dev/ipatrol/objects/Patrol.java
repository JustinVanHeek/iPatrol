package dev.ipatrol.objects;

import android.location.Location;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

import dev.ipatrol.LocationUtils;
import dev.ipatrol.objects.reports.Report;

/**
 * Created by Justin.vanHeek on 6/19/2017.
 */

public class Patrol {

    public void finishReport() {
        setEndTime(Calendar.getInstance());
        setEndLocation(null);
    }

    public enum WeatherCondition {Sunny, Cloudy, Rain, Snow};
    public enum LightingCondition {Dawn, Day, Dusk, Artificial, Night};
    public enum PatrolType {Foot, Bike, Car};

    private ArrayList<String> patrollers = new ArrayList<>();
    private Calendar startTime = Calendar.getInstance();
    private Calendar endTime;
    private Location startLocation;
    private Location endLocation;
    private WeatherCondition conditionWeather;
    private LightingCondition conditionLight;
    private PatrolType patrolType;
    private ArrayList<Report> reports = new ArrayList<>();

    private long getDuration() {
        long duration = endTime.getTimeInMillis() - startTime.getTimeInMillis();
        long minutes = duration/60000;
        return minutes;
    }

    private double getDistance() {
        //TODO
        return 0;
    }

    private int getTotalEventsRecorded() {
        return reports.size();
    }

    public void addPatroller(String name) {
        patrollers.add(name);
    }

    public void removePatroller(String name) {
        patrollers.remove(name);
    }

    public void clearPatrollers() {
        patrollers.clear();
    }

    public ArrayList<String> getPatrollers() {
        return (ArrayList<String>) patrollers.clone();
    }

    public void setStartTime(Calendar time) {
        startTime = (Calendar) time.clone();
    }

    public Calendar getStartTime() {
        return (Calendar) startTime.clone();
    }

    public void setEndTime(Calendar time) {
        endTime = (Calendar) time.clone();
    }

    public Calendar getEndTime() {
        return (Calendar) endTime.clone();
    }

    public void setStartLocation(Location loc) {
        startLocation = loc;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public void setEndLocation(Location loc) {
        endLocation = loc;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setWeather(WeatherCondition weather) {
        conditionWeather = weather;
    }

    public WeatherCondition getWeather() {
        return conditionWeather;
    }

    public void setLighting(LightingCondition light) {
        conditionLight = light;
    }

    public LightingCondition getLighting() {
        return conditionLight;
    }

    public void setPatrolType(PatrolType patrol) {
        patrolType = patrol;
    }

    public PatrolType getPatrolType() {
        return patrolType;
    }

    public void addReport(Report report) {
        reports.add(report);
    }

    public void removeReport(Report report) {
        reports.remove(report);
    }

    public void clearReports() {
        reports.clear();
    }

    public ArrayList<Report> getReports() {
        return (ArrayList<Report>) reports.clone();
    }

    public void printFullReport() {
        Log.i("Report", "--Start Of Full Patrol Report--");
        Log.i("Report", "   Patrollers:");
        for (String patroller : getPatrollers()) {
            Log.i("Report", "       " + patroller);
        }
        Log.i("Report", "   Start Time: " + getStartTime().getTime().toString());
        Log.i("Report", "   End Time: " + getEndTime().getTime().toString());
        Log.i("Report", "   Duration: " + getDuration());
        Log.i("Report", "   Start Location: " + LocationUtils.getClosestAddress(startLocation));
        Log.i("Report", "   End Location: " + LocationUtils.getClosestAddress(endLocation));
        Log.i("Report", "   Distance: " + getDistance());
        Log.i("Report", "   Conditions: " + getWeather().toString() + ", " + getLighting().toString());
        Log.i("Report", "   Patrol Type: " + getPatrolType().name());
        Log.i("Report", "   Reports: ");
        for (Report report : getReports()) {
            Log.i("Report", "       Type: " + report.getReportType().name());
            Log.i("Report", "       Location: " + LocationUtils.getClosestAddress(report.getLocation()));
            Log.i("Report", "       Notes: " + report.getNotes());
        }
        Log.i("Report", "--End Of Full Patrol Report--");
    }

}
