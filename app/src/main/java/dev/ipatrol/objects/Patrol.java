package dev.ipatrol.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.location.Location;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import dev.ipatrol.LocationUtils;
import dev.ipatrol.PDFRow;
import dev.ipatrol.PDFTable;
import dev.ipatrol.PDFUtil;
import dev.ipatrol.Resources;
import dev.ipatrol.objects.reports.AbandonedCarReport;
import dev.ipatrol.objects.reports.AlcoholReport;
import dev.ipatrol.objects.reports.BushPartyReport;
import dev.ipatrol.objects.reports.CrimePreventionReport;
import dev.ipatrol.objects.reports.RecFacilityReport;
import dev.ipatrol.objects.reports.Report;

/**
 * Created by Justin.vanHeek on 6/19/2017.
 */

public class Patrol {

    public static String pdfPath = "test.pdf";

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

    public void finishReport() {
        setEndTime(Calendar.getInstance());
        setEndLocation(null);

        generatePDF();

    }

    private void generatePDF() {



        PDFUtil pdf = new PDFUtil();

        //The actual report content
        pdf.setMargins(10,10,10,10);
        pdf.println("Report Generated: "+ Calendar.getInstance().getTime().toString(), Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
        pdf.newline();
        pdf.printlnCenter("ACOPA-Patrol Report", 18);
        pdf.newline();
        pdf.printlnCenter("Type: " + getPatrolType().toString() + " Patrol", 12);
        pdf.newline();
        pdf.drawLine();
        pdf.newline();
        pdf.printlnCenter("Patrol Summary", 14);
        pdf.newline();
        pdf.printCenterTable(405,getSummaryTable());
        pdf.newline();
        pdf.drawLine();
        pdf.newline();
        pdf.printlnCenter("Patrol Map", 14);
        //TODO pdf.drawMap();
        pdf.newline();
        pdf.drawLine();

        //Abandoned Auto Report
        boolean firstReport = true;
        for (Report report : getReports()) {
            if (report.getReportType() == Report.ReportType.AbandonedCar) {
                if(firstReport) {
                    PDFTable abandonedTable = new PDFTable();
                    abandonedTable.addRow(new PDFRow(new String[]{"Abandoned Auto Events"}, Resources.resources.colorAbandonedCar));
                    pdf.printCenterTable(405, abandonedTable);
                    pdf.newline();
                    firstReport = false;
                }
                pdf.insertPhoto(report.getImage(),10+350,pdf.getCursorY(),55,55);
                AbandonedCarReport carReport = (AbandonedCarReport) report;
                pdf.printTable(350, getAbandonedCarTable(carReport));
                pdf.newline();
            }
        }
        pdf.newline();

        //Alcohol Report
        firstReport = true;
        for (Report report : getReports()) {
            if (report.getReportType() == Report.ReportType.Alcohol) {
                if(firstReport) {
                    PDFTable table = new PDFTable();
                    table.addRow(new PDFRow(new String[]{"Alcohol Events"}, Resources.resources.colorAlcohol));
                    pdf.printCenterTable(405, table);
                    pdf.newline();
                    firstReport = false;
                }
                pdf.insertPhoto(report.getImage(),10+350,pdf.getCursorY(),55,55);
                pdf.printTable(350, getGeneralReportTable(report));
                pdf.newline();
            }
        }
        pdf.newline();

        //Bush Party Report
        firstReport = true;
        for (Report report : getReports()) {
            if (report.getReportType() == Report.ReportType.BushParty) {
                if(firstReport) {
                    PDFTable abandonedTable = new PDFTable();
                    abandonedTable.addRow(new PDFRow(new String[]{"Bush Party Events"}, Resources.resources.colorBushParty));
                    pdf.printCenterTable(405, abandonedTable);
                    pdf.newline();
                    firstReport = false;
                }
                pdf.insertPhoto(report.getImage(),10+350,pdf.getCursorY(),55,55);

                BushPartyReport bushreport = (BushPartyReport) report;
                pdf.printTable(350, getBushPartyTable(bushreport));
                pdf.newline();
            }
        }
        pdf.newline();

        //Church Report
        firstReport = true;
        for (Report report : getReports()) {
            if (report.getReportType() == Report.ReportType.Church) {
                if(firstReport) {
                    PDFTable abandonedTable = new PDFTable();
                    abandonedTable.addRow(new PDFRow(new String[]{"Church Events"}, Resources.resources.colorPublicFacilities));
                    pdf.printCenterTable(405, abandonedTable);
                    pdf.newline();
                    firstReport = false;
                }
                pdf.insertPhoto(report.getImage(),10+350,pdf.getCursorY(),55,55);

                RecFacilityReport bushreport = (RecFacilityReport) report;
                pdf.printTable(350, getRecTable(bushreport));
                pdf.newline();
            }
        }
        pdf.newline();

        //Crime Prevention Report
        firstReport = true;
        for (Report report : getReports()) {
            if (report.getReportType() == Report.ReportType.CrimePrevention) {
                if(firstReport) {
                    PDFTable abandonedTable = new PDFTable();
                    abandonedTable.addRow(new PDFRow(new String[]{"Crime Prevention Notice Events"}, Resources.resources.colorCrimePrev));
                    pdf.printCenterTable(405, abandonedTable);
                    pdf.newline();
                    firstReport = false;
                }
                pdf.insertPhoto(report.getImage(),10+350,pdf.getCursorY(),55,55);

                CrimePreventionReport bushreport = (CrimePreventionReport) report;
                pdf.printTable(350, getCrimePrevTable(bushreport));
                pdf.newline();
            }
        }
        pdf.newline();

        //Gas Station Report
        firstReport = true;
        for (Report report : getReports()) {
            if (report.getReportType() == Report.ReportType.GasStation) {
                if(firstReport) {
                    PDFTable table = new PDFTable();
                    table.addRow(new PDFRow(new String[]{"Gas Station Events"}, Resources.resources.colorBusiness));
                    pdf.printCenterTable(405, table);
                    pdf.newline();
                    firstReport = false;
                }
                pdf.insertPhoto(report.getImage(),10+350,pdf.getCursorY(),55,55);

                pdf.printTable(350, getGeneralReportTable(report));
                pdf.newline();
            }
        }
        pdf.newline();

        //General Mischief Report
        firstReport = true;
        for (Report report : getReports()) {
            if (report.getReportType() == Report.ReportType.Mischief) {
                if(firstReport) {
                    PDFTable table = new PDFTable();
                    table.addRow(new PDFRow(new String[]{"General Mischief Events"}, Resources.resources.colorMischief));
                    pdf.printCenterTable(405, table);
                    pdf.newline();
                    firstReport = false;
                }
                pdf.insertPhoto(report.getImage(),10+350,pdf.getCursorY(),55,55);

                pdf.printTable(350, getGeneralReportTable(report));
                pdf.newline();
            }
        }
        pdf.newline();

        //Graffiti Report
        firstReport = true;
        for (Report report : getReports()) {
            if (report.getReportType() == Report.ReportType.Graffiti) {
                if(firstReport) {
                    PDFTable table = new PDFTable();
                    table.addRow(new PDFRow(new String[]{"Graffiti Events"}, Resources.resources.colorGraffiti));
                    pdf.printCenterTable(405, table);
                    pdf.newline();
                    firstReport = false;
                }
                pdf.insertPhoto(report.getImage(),10+350,pdf.getCursorY(),55,55);

                pdf.printTable(350, getGeneralReportTable(report));
                pdf.newline();
            }
        }
        pdf.newline();

        //Illegal Dumping Report
        firstReport = true;
        for (Report report : getReports()) {
            if (report.getReportType() == Report.ReportType.IllegalDumping) {
                if(firstReport) {
                    PDFTable table = new PDFTable();
                    table.addRow(new PDFRow(new String[]{"Illegal Dumping Events"},Resources.resources.colorBusiness));
                    pdf.printCenterTable(405, table);
                    pdf.newline();
                    firstReport = false;
                }
                pdf.insertPhoto(report.getImage(),10+350,pdf.getCursorY(),55,55);

                pdf.printTable(350, getGeneralReportTable(report));
                pdf.newline();
            }
        }
        pdf.newline();

        //Open Door Report
        firstReport = true;
        for (Report report : getReports()) {
            if (report.getReportType() == Report.ReportType.OpenDoor) {
                if(firstReport) {
                    PDFTable table = new PDFTable();
                    table.addRow(new PDFRow(new String[]{"Open Door Events"}, Resources.resources.colorOpen));
                    pdf.printCenterTable(405, table);
                    pdf.newline();
                    firstReport = false;
                }
                pdf.insertPhoto(report.getImage(),10+350,pdf.getCursorY(),55,55);

                pdf.printTable(350, getGeneralReportTable(report));
                pdf.newline();
            }
        }
        pdf.newline();

        //Open Garage Report
        firstReport = true;
        for (Report report : getReports()) {
            if (report.getReportType() == Report.ReportType.OpenGarage) {
                if(firstReport) {
                    PDFTable table = new PDFTable();
                    table.addRow(new PDFRow(new String[]{"Open Garage Events"}, Resources.resources.colorOpen));
                    pdf.printCenterTable(405, table);
                    pdf.newline();
                    firstReport = false;
                }
                pdf.insertPhoto(report.getImage(),10+350,pdf.getCursorY(),55,55);

                pdf.printTable(350, getGeneralReportTable(report));
                pdf.newline();
            }
        }
        pdf.newline();

        //Open Gate Report
        firstReport = true;
        for (Report report : getReports()) {
            if (report.getReportType() == Report.ReportType.OpenGate) {
                if(firstReport) {
                    PDFTable table = new PDFTable();
                    table.addRow(new PDFRow(new String[]{"Open Gate Events"}, Resources.resources.colorOpen));
                    pdf.printCenterTable(405, table);
                    pdf.newline();
                    firstReport = false;
                }
                pdf.insertPhoto(report.getImage(),10+350,pdf.getCursorY(),55,55);

                pdf.printTable(350, getGeneralReportTable(report));
                pdf.newline();
            }
        }
        pdf.newline();

        //Open Window Report
        firstReport = true;
        for (Report report : getReports()) {
            if (report.getReportType() == Report.ReportType.OpenWindow) {
                if(firstReport) {
                    PDFTable table = new PDFTable();
                    table.addRow(new PDFRow(new String[]{"Open Window Events"}, Resources.resources.colorOpen));
                    pdf.printCenterTable(405, table);
                    pdf.newline();
                    firstReport = false;
                }
                pdf.insertPhoto(report.getImage(),10+350,pdf.getCursorY(),55,55);

                pdf.printTable(350, getGeneralReportTable(report));
                pdf.newline();
            }
        }
        pdf.newline();

        //Other Business Report
        firstReport = true;
        for (Report report : getReports()) {
            if (report.getReportType() == Report.ReportType.Business) {
                if(firstReport) {
                    PDFTable table = new PDFTable();
                    table.addRow(new PDFRow(new String[]{"Other Business Events"}, Resources.resources.colorBusiness));
                    pdf.printCenterTable(405, table);
                    pdf.newline();
                    firstReport = false;
                }
                pdf.insertPhoto(report.getImage(),10+350,pdf.getCursorY(),55,55);

                pdf.printTable(350, getGeneralReportTable(report));
                pdf.newline();
            }
        }
        pdf.newline();

        //Park Report
        firstReport = true;
        for (Report report : getReports()) {
            if (report.getReportType() == Report.ReportType.Park) {
                if(firstReport) {
                    PDFTable abandonedTable = new PDFTable();
                    abandonedTable.addRow(new PDFRow(new String[]{"Park / Playground Events"}, Resources.resources.colorPublicFacilities));
                    pdf.printCenterTable(405, abandonedTable);
                    pdf.newline();
                    firstReport = false;
                }
                pdf.insertPhoto(report.getImage(),10+350,pdf.getCursorY(),55,55);

                RecFacilityReport bushreport = (RecFacilityReport) report;
                pdf.printTable(350, getRecTable(bushreport));
                pdf.newline();
            }
        }
        pdf.newline();

        //Reckless Driving Report
        firstReport = true;
        for (Report report : getReports()) {
            if (report.getReportType() == Report.ReportType.RecklessDriving) {
                if(firstReport) {
                    PDFTable table = new PDFTable();
                    table.addRow(new PDFRow(new String[]{"Reckless / Impaired Driving Events"}, Resources.resources.colorReckless));
                    pdf.printCenterTable(405, table);
                    pdf.newline();
                    firstReport = false;
                }
                pdf.insertPhoto(report.getImage(),10+350,pdf.getCursorY(),55,55);

                pdf.printTable(350, getGeneralReportTable(report));
                pdf.newline();
            }
        }
        pdf.newline();

        //Rec Report
        firstReport = true;
        for (Report report : getReports()) {
            if (report.getReportType() == Report.ReportType.RecFacility) {
                if(firstReport) {
                    PDFTable abandonedTable = new PDFTable();
                    abandonedTable.addRow(new PDFRow(new String[]{"Recreational Facility Events"}, Resources.resources.colorPublicFacilities));
                    pdf.printTable(405, abandonedTable);
                    pdf.newline();
                    firstReport = false;
                }
                pdf.insertPhoto(report.getImage(),10+350,pdf.getCursorY(),55,55);

                RecFacilityReport bushreport = (RecFacilityReport) report;
                pdf.printTable(350, getRecTable(bushreport));
                pdf.newline();
            }
        }
        pdf.newline();

        //School Report
        firstReport = true;
        for (Report report : getReports()) {
            if (report.getReportType() == Report.ReportType.School) {
                if(firstReport) {
                    PDFTable abandonedTable = new PDFTable();
                    abandonedTable.addRow(new PDFRow(new String[]{"School Events"},Resources.resources.colorPublicFacilities));
                    pdf.printCenterTable(405, abandonedTable);
                    pdf.newline();
                    firstReport = false;
                }
                pdf.insertPhoto(report.getImage(),10+350,pdf.getCursorY(),55,55);

                RecFacilityReport bushreport = (RecFacilityReport) report;
                pdf.printTable(350, getRecTable(bushreport));
                pdf.newline();
            }
        }
        pdf.newline();

        //Shop Report
        firstReport = true;
        for (Report report : getReports()) {
            if (report.getReportType() == Report.ReportType.Shop) {
                if(firstReport) {
                    PDFTable table = new PDFTable();
                    table.addRow(new PDFRow(new String[]{"Shop Events"}, Resources.resources.colorBusiness));
                    pdf.printCenterTable(405, table);
                    pdf.newline();
                    firstReport = false;
                }
                pdf.insertPhoto(report.getImage(),10+350,pdf.getCursorY(),55,55);

                pdf.printTable(350, getGeneralReportTable(report));
                pdf.newline();
            }
        }
        pdf.newline();

        //Shopping Center Report
        firstReport = true;
        for (Report report : getReports()) {
            if (report.getReportType() == Report.ReportType.ShopCenter) {
                if(firstReport) {
                    PDFTable table = new PDFTable();
                    table.addRow(new PDFRow(new String[]{"Shopping Center Events"}, Resources.resources.colorBusiness));
                    pdf.printCenterTable(405, table);
                    pdf.newline();
                    firstReport = false;
                }
                pdf.insertPhoto(report.getImage(),10+350,pdf.getCursorY(),55,55);

                pdf.printTable(350, getGeneralReportTable(report));
                pdf.newline();
            }
        }
        pdf.newline();

        //Suspicious Activity Report
        firstReport = true;
        for (Report report : getReports()) {
            if (report.getReportType() == Report.ReportType.Suspicious) {
                if(firstReport) {
                    PDFTable table = new PDFTable();
                    table.addRow(new PDFRow(new String[]{"Suspicious Activities Events"}, Resources.resources.colorSuspicious));
                    pdf.printCenterTable(405, table);
                    pdf.newline();
                    firstReport = false;
                }
                pdf.insertPhoto(report.getImage(),10+350,pdf.getCursorY(),55,55);

                pdf.printTable(350, getGeneralReportTable(report));
                pdf.newline();
            }
        }
        pdf.newline();

        //TFA Report
        firstReport = true;
        for (Report report : getReports()) {
            if (report.getReportType() == Report.ReportType.TFAGlass) {
                if(firstReport) {
                    PDFTable table = new PDFTable();
                    table.addRow(new PDFRow(new String[]{"Theft From Auto (Broken Auto Glass) Events"}, Resources.resources.colorMischief));
                    pdf.printCenterTable(405, table);
                    pdf.newline();
                    firstReport = false;
                }
                pdf.insertPhoto(report.getImage(),10+350,pdf.getCursorY(),55,55);

                pdf.printTable(350, getGeneralReportTable(report));
                pdf.newline();
            }
        }
        pdf.newline();

        //Other Report
        firstReport = true;
        for (Report report : getReports()) {
            if (report.getReportType() == Report.ReportType.Other) {
                if(firstReport) {
                    PDFTable table = new PDFTable();
                    table.addRow(new PDFRow(new String[]{"Other Events"}, Resources.resources.colorOther));
                    pdf.printCenterTable(405, table);
                    pdf.newline();
                    firstReport = false;
                }
                pdf.insertPhoto(report.getImage(),10+350,pdf.getCursorY(),55,55);

                pdf.printTable(350, getGeneralReportTable(report));
                pdf.newline();
            }
        }
        pdf.newline();

        pdf.save();




    }

    private PDFTable getCrimePrevTable(CrimePreventionReport report) {
        int color = Resources.resources.colorLightCrimePrev;

        //This part of the report is the same for each
        PDFTable table = new PDFTable();
        table.setBorder(true, Color.BLACK);
        table.addRow(new PDFRow(new String[]{report.getTitle()}, color));
        table.addRow(new PDFRow(new String[]{"Time", "Address", "Latitude", "Longitude"},color));
        String time = new SimpleDateFormat("MMM d H:mm YYYY").format(report.getTime().getTime());
        table.addRow(new PDFRow(new String[]{time, LocationUtils.getClosestAddress(report.getLocation()), "TODO"/*report.getLocation().getLatitude()*/, "TODO"/*report.getLocation().getLongitude()*/}));        //Unique fields of this particular report

        String items = "";

        for (String item : report.getItems()) {
            if (items == "") {
                items = item;
            } else {
                items = items + ", " + item;
            }
        }

        table.addRow(new PDFRow(new String[]{"Value", "Items", "Window Open", "Tinted Window"},color));
        table.addRow(new PDFRow(new String[]{"$"+report.getValue(), items, toYesOrNo(report.isOpenWindow()), toYesOrNo(report.isTintedWindow())}));
        table.addRow(new PDFRow(new String[]{"Vehicle Unlocked", "Security System", "Expired Plate"},color));
        table.addRow(new PDFRow(new String[]{toYesOrNo(report.isUnlocked()), toYesOrNo(report.isSecuritySystem()), toYesOrNo(report.isExpiredPlate())}));
        table.addRow(new PDFRow(new String[]{"Notes"},color));
        table.addRow(new PDFRow(new String[]{report.getNotes()}));

        return table;
    }

    private PDFTable getBushPartyTable(BushPartyReport report) {
        int color = Resources.resources.colorLightBushParty;

        //This part of the report is the same for each
        PDFTable table = new PDFTable();
        table.setBorder(true, Color.BLACK);
        table.addRow(new PDFRow(new String[]{report.getTitle()}, color));
        table.addRow(new PDFRow(new String[]{"Time", "Address", "Latitude", "Longitude"},color));
        String time = new SimpleDateFormat("MMM d H:mm YYYY").format(report.getTime().getTime());
        table.addRow(new PDFRow(new String[]{time, LocationUtils.getClosestAddress(report.getLocation()), "TODO"/*report.getLocation().getLatitude()*/, "TODO"/*report.getLocation().getLongitude()*/}));        //Unique fields of this particular report
        table.addRow(new PDFRow(new String[]{"Number of People", "Number of Vehicles"},color));
        table.addRow(new PDFRow(new String[]{report.getNumOfPeople()+"", report.getNumOfVehicles()+""}));
        table.addRow(new PDFRow(new String[]{"Drugs", "Alcohol", "Fire"},color));
        table.addRow(new PDFRow(new String[]{toYesOrNo(report.isDrugs()), toYesOrNo(report.isAlcohol()), toYesOrNo(report.isFire())}));
        table.addRow(new PDFRow(new String[]{"Notes"},color));
        table.addRow(new PDFRow(new String[]{report.getNotes()}));

        return table;
    }

    private PDFTable getRecTable(RecFacilityReport report) {
        int color = Resources.resources.colorLightPublicFacilities;

        //This part of the report is the same for each
        PDFTable table = new PDFTable();
        table.setBorder(true, Color.BLACK);
        table.addRow(new PDFRow(new String[]{report.getTitle()}, color));
        table.addRow(new PDFRow(new String[]{"Time", "Address", "Latitude", "Longitude"},color));
        String time = new SimpleDateFormat("MMM d H:mm YYYY").format(report.getTime().getTime());
        table.addRow(new PDFRow(new String[]{time, LocationUtils.getClosestAddress(report.getLocation()), "TODO"/*report.getLocation().getLatitude()*/, "TODO"/*report.getLocation().getLongitude()*/}));        //Unique fields of this particular report
        table.addRow(new PDFRow(new String[]{"Events Happening"},color));
        table.addRow(new PDFRow(new String[]{report.getEvents()}));
        table.addRow(new PDFRow(new String[]{"Vehicles Parked at Odd Times"},color));
        table.addRow(new PDFRow(new String[]{report.getOddVehicles()}));
        table.addRow(new PDFRow(new String[]{"Unusual Activities"},color));
        table.addRow(new PDFRow(new String[]{report.getUnusualActivities()}));
        table.addRow(new PDFRow(new String[]{"Notes"},color));
        table.addRow(new PDFRow(new String[]{report.getNotes()}));

        return table;
    }

    private PDFTable getGeneralReportTable(Report report) {
        int color = Color.GRAY;

        switch (report.getReportType()) {
            case Alcohol: color = Resources.resources.colorLightAlcohol;
                break;
            case GasStation: color = Resources.resources.colorLightBusiness;
                break;
            case Mischief: color = Resources.resources.colorLightMischief;
                break;
            case Graffiti: color = Resources.resources.colorLightGraffiti;
                break;
            case IllegalDumping: color = Resources.resources.colorLightDumping;
                break;
            case OpenDoor: color = Resources.resources.colorLightOpen;
                break;
            case OpenGarage: color = Resources.resources.colorLightOpen;
                break;
            case OpenGate: color = Resources.resources.colorLightOpen;
                break;
            case OpenWindow: color = Resources.resources.colorLightOpen;
                break;
            case Business: color = Resources.resources.colorLightBusiness;
                break;
            case RecklessDriving: color = Resources.resources.colorLightReckless;
                break;
            case Shop: color = Resources.resources.colorLightBusiness;
                break;
            case ShopCenter: color = Resources.resources.colorLightBusiness;
                break;
            case Suspicious: color = Resources.resources.colorLightSuspicious;
                break;
            case TFAGlass: color = Resources.resources.colorLightMischief;
                break;
            case Other: color = Resources.resources.colorLightOther;
                break;
        }
        //This part of the report is the same for each
        PDFTable table = new PDFTable();
        table.setBorder(true, Color.BLACK);
        table.addRow(new PDFRow(new String[]{report.getTitle()}, color));
        table.addRow(new PDFRow(new String[]{"Time", "Address", "Latitude", "Longitude"},color));
        String time = new SimpleDateFormat("MMM d H:mm YYYY").format(report.getTime().getTime());
        table.addRow(new PDFRow(new String[]{time, LocationUtils.getClosestAddress(report.getLocation()), "TODO"/*report.getLocation().getLatitude()*/, "TODO"/*report.getLocation().getLongitude()*/}));
        table.addRow(new PDFRow(new String[]{"Notes"},color));
        table.addRow(new PDFRow(new String[]{report.getNotes()}));

        return table;
    }

    private PDFTable getAbandonedCarTable(AbandonedCarReport report) {
        int color = Resources.resources.colorLightAbandonedCar;

        //This part of the report is the same for each
        PDFTable table = new PDFTable();
        table.setBorder(true, Color.BLACK);
        table.addRow(new PDFRow(new String[]{report.getTitle()}, color));
        table.addRow(new PDFRow(new String[]{"Time", "Address", "Latitude", "Longitude"},color));
        String time = new SimpleDateFormat("MMM d H:mm YYYY").format(report.getTime().getTime());
        table.addRow(new PDFRow(new String[]{time, LocationUtils.getClosestAddress(report.getLocation()), "TODO"/*report.getLocation().getLatitude()*/, "TODO"/*report.getLocation().getLongitude()*/}));        //Unique fields of this particular report
        table.addRow(new PDFRow(new String[]{"Make", "Model", "Color", "Direction Facing"},color));
        table.addRow(new PDFRow(new String[]{report.getMake(), report.getModel(), report.getColour(), report.getFacing().name()}));
        table.addRow(new PDFRow(new String[]{"License Plate", "Expired Plate", "Flat Tire(s)"},color));
        table.addRow(new PDFRow(new String[]{report.getPlate(), toYesOrNo(report.isExpiredPlate()), toYesOrNo(report.isFlatTire())}));
        table.addRow(new PDFRow(new String[]{"Condition"},color));
        table.addRow(new PDFRow(new String[]{report.getCondition()}));
        table.addRow(new PDFRow(new String[]{"Notes"},color));
        table.addRow(new PDFRow(new String[]{report.getNotes()}));

        return table;
    }

    private String toYesOrNo(boolean b) {
        if (b) {
            return "Yes";
        }
        else {
            return "No";
        }
    }

    private PDFTable getSummaryTable() {
        PDFTable summary = new PDFTable();
        summary.addRow(new PDFRow(new String[]{"Start Time", "End Time", "Duration (min)"},Color.GRAY));
        summary.addRow(new PDFRow(new String[]{new SimpleDateFormat("MMM d H:mm YYYY").format(getStartTime().getTime()),new SimpleDateFormat("MMM d H:mm YYYY").format(getEndTime().getTime()),getDuration()+""}));
        summary.addRow(new PDFRow());
        summary.addRow(new PDFRow(new String[]{"Start Location", "End Location", "Distance (km)"},Color.GRAY));
        summary.addRow(new PDFRow(new String[]{LocationUtils.getClosestAddress(getStartLocation()),LocationUtils.getClosestAddress(getEndLocation()),getDistance()+""}));
        summary.addRow(new PDFRow());
        summary.addRow(new PDFRow(new String[]{"Weather", "Lighting", "Total Events Recorded"},Color.GRAY));
        summary.addRow(new PDFRow(new String[]{getWeather().name(),getLighting().name(),getTotalEventsRecorded()+""}));
        summary.addRow(new PDFRow());
        summary.addRow(new PDFRow(new String[]{"Volunteers"},Color.GRAY));

        String patrollers = "";
        for (String patroller : getPatrollers()) {
            if (patrollers == "") {
                patrollers = patroller;
            }
            else {
                patrollers = patrollers + ", " + patroller;
            }
        }

        summary.addRow(new PDFRow(new String[]{patrollers}));
        summary.addRow(new PDFRow());
    return summary;
    }

}
