package dev.ipatrol.activityreports;

import android.os.Bundle;

import dev.ipatrol.R;
import dev.ipatrol.objects.reports.BushPartyReport;
import dev.ipatrol.objects.reports.Report;

public class BushPartyReportActivity extends ReportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bush_party_report);
        setupCommonButtons();

    }

    protected Report createReport() {
        BushPartyReport report = new BushPartyReport(getReportTitle(),getImage(),getLocation(),getNotes());
        return report;
    }



}
