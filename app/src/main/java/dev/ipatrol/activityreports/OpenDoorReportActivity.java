package dev.ipatrol.activityreports;

import android.os.Bundle;

import dev.ipatrol.R;
import dev.ipatrol.objects.reports.OpenDoorReport;
import dev.ipatrol.objects.reports.Report;

public class OpenDoorReportActivity extends ReportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door_report);
        setupCommonButtons();

    }

    protected Report createReport() {
        OpenDoorReport report = new OpenDoorReport(getReportTitle(),getImage(),getLocation(),getNotes());
        return report;
    }



}
