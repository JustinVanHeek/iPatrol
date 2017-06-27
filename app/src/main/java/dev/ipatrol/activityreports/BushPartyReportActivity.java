package dev.ipatrol.activityreports;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

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
        String people = ((EditText) findViewById(R.id.PeopleText)).getText().toString();
        String vehicles = ((EditText) findViewById(R.id.VehiclesText)).getText().toString();
        int p = 0;
        int v = 0;
        try {
            p = Integer.parseInt(people);
        }
        catch (Exception e) {

        }
        try {
            v = Integer.parseInt(vehicles);
        }
        catch (Exception e) {

        }
        BushPartyReport report = new BushPartyReport(getReportTitle(),getImage(),getLocation(),getNotes(),
                p,
                v,
                ((CheckBox) findViewById(R.id.DrugsCheck)).isChecked(),
                ((CheckBox) findViewById(R.id.AlcoholCheck)).isChecked(),
                ((CheckBox) findViewById(R.id.FireCheck)).isChecked()
                );
        return report;
    }



}
