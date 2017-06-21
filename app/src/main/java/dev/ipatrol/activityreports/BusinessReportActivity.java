package dev.ipatrol.activityreports;

import android.os.Bundle;

import dev.ipatrol.R;
import dev.ipatrol.objects.reports.BusinessReport;
import dev.ipatrol.objects.reports.Report;

public class BusinessReportActivity extends ReportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_report);
        setupCommonButtons();

    }

    protected Report createReport() {
        BusinessReport report = new BusinessReport(getReportTitle(),getImage(),getLocation(),getNotes());
        return report;
    }



}
