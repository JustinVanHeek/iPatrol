package dev.ipatrol.activityreports;

import android.os.Bundle;
import android.widget.EditText;

import dev.ipatrol.R;
import dev.ipatrol.objects.reports.RecFacilityReport;
import dev.ipatrol.objects.reports.Report;

public class RecFacilityReportActivity extends ReportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_facility_report);
        setupCommonButtons();

    }

    protected Report createReport() {
        RecFacilityReport report = new RecFacilityReport(getReportTitle(),getImage(),getLocation(),getNotes(),
                ((EditText) findViewById(R.id.EventsText)).getText().toString(),
                ((EditText) findViewById(R.id.VehiclesText)).getText().toString(),
                ((EditText) findViewById(R.id.UnusualText)).getText().toString());
        return report;
    }



}
