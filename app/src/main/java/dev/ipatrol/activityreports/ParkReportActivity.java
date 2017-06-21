package dev.ipatrol.activityreports;

import android.os.Bundle;

import dev.ipatrol.R;
import dev.ipatrol.objects.reports.ParkReport;
import dev.ipatrol.objects.reports.Report;

public class ParkReportActivity extends ReportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_report);
        setupCommonButtons();

    }

    protected Report createReport() {
        ParkReport report = new ParkReport(getReportTitle(),getImage(),getLocation(),getNotes());
        return report;
    }



}
