package dev.ipatrol.activityreports;

import android.os.Bundle;

import dev.ipatrol.R;
import dev.ipatrol.objects.reports.OpenGarageReport;
import dev.ipatrol.objects.reports.Report;

public class OpenGarageReportActivity extends ReportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage_report);
        setupCommonButtons();

    }

    protected Report createReport() {
        OpenGarageReport report = new OpenGarageReport(getReportTitle(),getImage(),getLocation(),getNotes());
        return report;
    }



}
