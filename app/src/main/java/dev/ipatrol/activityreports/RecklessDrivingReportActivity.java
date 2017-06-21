package dev.ipatrol.activityreports;

import android.os.Bundle;

import dev.ipatrol.R;
import dev.ipatrol.objects.reports.RecklessDrivingReport;
import dev.ipatrol.objects.reports.Report;

public class RecklessDrivingReportActivity extends ReportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reckless_driving_report);
        setupCommonButtons();

    }

    protected Report createReport() {
        RecklessDrivingReport report = new RecklessDrivingReport(getReportTitle(),getImage(),getLocation(),getNotes());
        return report;
    }

}
