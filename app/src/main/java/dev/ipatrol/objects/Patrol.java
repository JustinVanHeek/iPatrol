package dev.ipatrol.objects;

import android.location.Location;

import java.util.ArrayList;
import java.util.Calendar;

import dev.ipatrol.objects.reports.Report;

/**
 * Created by Justin.vanHeek on 6/19/2017.
 */

public class Patrol {

    public enum WeatherCondition {Sunny, Cloudy, Rain, Snow};
    public enum TimeCondition {Dawn, Day, Dusk, Artificial, Night};
    public enum PatrolType {Walk, Bike, Car};

    private String username;
    private String[] patrollers;
    private Calendar startTime;
    private Location startLocation;
    private WeatherCondition conditionWeather;
    private TimeCondition conditionTime;
    private PatrolType patrolType;
    private ArrayList<Report> reports;

}
