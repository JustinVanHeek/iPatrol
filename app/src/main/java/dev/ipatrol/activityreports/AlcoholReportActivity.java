package dev.ipatrol.activityreports;

import android.os.Bundle;

import dev.ipatrol.R;
import dev.ipatrol.objects.reports.AlcoholReport;
import dev.ipatrol.objects.reports.Report;

public class AlcoholReportActivity extends ReportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcohol_report);

        setupCommonButtons();

    }

    @Override
    protected Report createReport() {
        AlcoholReport report = new AlcoholReport(getReportTitle(),getImage(),getLocation(),getNotes());
        return report;
    }



}
