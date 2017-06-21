package dev.ipatrol.activityreports;

import android.os.Bundle;

import dev.ipatrol.R;
import dev.ipatrol.objects.reports.CrimePreventionReport;
import dev.ipatrol.objects.reports.Report;

public class CrimePreventionReportActivity extends ReportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_prevention_report);

        setupCommonButtons();

    }

    @Override
    protected Report createReport() {
        CrimePreventionReport report = new CrimePreventionReport(getReportTitle(),getImage(),getLocation(),getNotes());
        return report;
    }


}
