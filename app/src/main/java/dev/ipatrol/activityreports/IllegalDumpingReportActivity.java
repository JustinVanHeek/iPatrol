package dev.ipatrol.activityreports;

import android.os.Bundle;

import dev.ipatrol.R;
import dev.ipatrol.objects.reports.IllegalDumpingReport;
import dev.ipatrol.objects.reports.Report;

public class IllegalDumpingReportActivity extends ReportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illegal_dumping_report);
        setupCommonButtons();

    }

    protected Report createReport() {
        IllegalDumpingReport report = new IllegalDumpingReport(getReportTitle(),getImage(),getLocation(),getNotes());
        return report;
    }


}
