package dev.ipatrol.activityreports;

import android.os.Bundle;

import dev.ipatrol.R;
import dev.ipatrol.objects.reports.Report;
import dev.ipatrol.objects.reports.SuspiciousReport;

public class SuspiciousReportActivity extends ReportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspicious_report);
        setupCommonButtons();

    }

    protected Report createReport() {
        SuspiciousReport report = new SuspiciousReport(getReportTitle(),getImage(),getLocation(),getNotes());
        return report;
    }



}
