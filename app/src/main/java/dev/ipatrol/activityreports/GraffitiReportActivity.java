package dev.ipatrol.activityreports;

import android.os.Bundle;

import dev.ipatrol.R;
import dev.ipatrol.objects.reports.GraffitiReport;
import dev.ipatrol.objects.reports.Report;

public class GraffitiReportActivity extends ReportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graffiti_report);
        setupCommonButtons();

    }

    protected Report createReport() {
        GraffitiReport report = new GraffitiReport(getReportTitle(),getImage(),getLocation(),getNotes());
        return report;
    }



}
