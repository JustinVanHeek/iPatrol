package dev.ipatrol.activityreports;

import android.os.Bundle;

import dev.ipatrol.R;
import dev.ipatrol.objects.reports.OpenGateReport;
import dev.ipatrol.objects.reports.Report;

public class OpenGateReportActivity extends ReportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gate_report);
        setupCommonButtons();

    }

    protected Report createReport() {
        OpenGateReport report = new OpenGateReport(getReportTitle(),getImage(),getLocation(),getNotes());
        return report;
    }



}
