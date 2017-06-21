package dev.ipatrol.activityreports;

import android.os.Bundle;

import dev.ipatrol.R;
import dev.ipatrol.objects.reports.OpenWindowReport;
import dev.ipatrol.objects.reports.Report;

public class OpenWindowReportActivity extends ReportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_report);
        setupCommonButtons();

    }

    protected Report createReport() {
        OpenWindowReport report = new OpenWindowReport(getReportTitle(),getImage(),getLocation(),getNotes());
        return report;
    }



}
