package dev.ipatrol.activityreports;

import android.os.Bundle;

import dev.ipatrol.R;
import dev.ipatrol.objects.reports.GasStationReport;
import dev.ipatrol.objects.reports.Report;

public class GasStationReportActivity extends ReportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas_station_report);
        setupCommonButtons();

    }

    protected Report createReport() {
        GasStationReport report = new GasStationReport(getReportTitle(),getImage(),getLocation(),getNotes());
        return report;
    }



}
