package dev.ipatrol;

import android.support.v4.content.res.ResourcesCompat;

/**
 * Created by Justin.vanHeek on 6/27/2017.
 */

public class Resources {

    public static Resources resources;


    private MainActivity activity;

    public Resources(MainActivity a) {
        activity = a;

        colorAbandonedCar = ResourcesCompat.getColor(activity.getResources(), R.color.tileAbandoned, null);
        colorAlcohol = ResourcesCompat.getColor(activity.getResources(), R.color.tileAlcohol, null);
        colorBushParty = ResourcesCompat.getColor(activity.getResources(), R.color.tileBushParty, null);
        colorBusiness = ResourcesCompat.getColor(activity.getResources(), R.color.tileBusiness, null);
        colorPublicFacilities = ResourcesCompat.getColor(activity.getResources(), R.color.tilePublicFacilities, null);
        colorCrimePrev = ResourcesCompat.getColor(activity.getResources(), R.color.tileCrimePrev, null);
        colorOpen = ResourcesCompat.getColor(activity.getResources(), R.color.tileOpenAccess, null);
        colorGraffiti = ResourcesCompat.getColor(activity.getResources(), R.color.tileGraffiti, null);
        colorDumping = ResourcesCompat.getColor(activity.getResources(), R.color.tileDumping, null);
        colorMischief = ResourcesCompat.getColor(activity.getResources(), R.color.tileMischief, null);
        colorOther = ResourcesCompat.getColor(activity.getResources(), R.color.tileOther, null);
        colorReckless = ResourcesCompat.getColor(activity.getResources(), R.color.tileReckless, null);
        colorSuspicious = ResourcesCompat.getColor(activity.getResources(), R.color.tileSuspicious, null);

        colorLightAbandonedCar = ResourcesCompat.getColor(activity.getResources(), R.color.lightAbandoned, null);
        colorLightAlcohol = ResourcesCompat.getColor(activity.getResources(), R.color.lightAlcohol, null);
        colorLightBushParty = ResourcesCompat.getColor(activity.getResources(), R.color.lightBushParty, null);
        colorLightBusiness = ResourcesCompat.getColor(activity.getResources(), R.color.lightBusiness, null);
        colorLightPublicFacilities = ResourcesCompat.getColor(activity.getResources(), R.color.lightPublicFacilities, null);
        colorLightCrimePrev = ResourcesCompat.getColor(activity.getResources(), R.color.lightCrimePrev, null);
        colorLightOpen = ResourcesCompat.getColor(activity.getResources(), R.color.lightOpenAccess, null);
        colorLightGraffiti = ResourcesCompat.getColor(activity.getResources(), R.color.lightGraffiti, null);
        colorLightDumping = ResourcesCompat.getColor(activity.getResources(), R.color.lightDumping, null);
        colorLightMischief = ResourcesCompat.getColor(activity.getResources(), R.color.lightMischief, null);
        colorLightOther = ResourcesCompat.getColor(activity.getResources(), R.color.lightOther, null);
        colorLightReckless = ResourcesCompat.getColor(activity.getResources(), R.color.lightReckless, null);
        colorLightSuspicious = ResourcesCompat.getColor(activity.getResources(), R.color.lightSuspicious, null);
    }

    //Colors
    public  int colorAbandonedCar;
    public  int colorAlcohol;
    public  int colorBushParty;
    public  int colorBusiness;
    public  int colorPublicFacilities;
    public  int colorCrimePrev;
    public  int colorOpen;
    public  int colorGraffiti;
    public   int colorDumping;
    public   int colorMischief;
    public   int colorOther;
    public   int colorReckless;
    public   int colorSuspicious;

    public   int colorLightAbandonedCar;
    public   int colorLightAlcohol;
    public   int colorLightBushParty;
    public   int colorLightBusiness;
    public   int colorLightPublicFacilities;
    public   int colorLightCrimePrev;
    public   int colorLightOpen;
    public   int colorLightGraffiti;
    public   int colorLightDumping;
    public   int colorLightMischief;
    public   int colorLightOther;
    public   int colorLightReckless;
    public   int colorLightSuspicious;




}
