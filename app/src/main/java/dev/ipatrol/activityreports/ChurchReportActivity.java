package dev.ipatrol.activityreports;

import android.os.Bundle;

import dev.ipatrol.R;
import dev.ipatrol.objects.reports.ChurchReport;
import dev.ipatrol.objects.reports.Report;

public class ChurchReportActivity extends ReportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_church_report);
        setupCommonButtons();

    }

    protected Report createReport() {
        ChurchReport report = new ChurchReport(getReportTitle(),getImage(),getLocation(),getNotes());
        return report;
    }



}
