package dev.ipatrol.activityreports;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

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
        String value = ((EditText) findViewById(R.id.ValueText)).getText().toString();
        int v = 0;
        try {
            v = Integer.parseInt(value);
        }
        catch (Exception e) {

        }
        CrimePreventionReport report = new CrimePreventionReport(getReportTitle(),getImage(),getLocation(),getNotes(),
                v,
                getItems(),
                ((CheckBox) findViewById(R.id.WindowCheck)).isChecked(),
                ((CheckBox) findViewById(R.id.TintCheck)).isChecked(),
                ((CheckBox) findViewById(R.id.UnlockedCheck)).isChecked(),
                ((CheckBox) findViewById(R.id.SecurityCheck)).isChecked(),
                ((CheckBox) findViewById(R.id.PlateCheck)).isChecked()
                );
        return report;
    }

    private ArrayList<String> getItems() {
        ArrayList<String> items = new ArrayList<String>();
        if (((CheckBox) findViewById(R.id.PhoneCheck)).isChecked()) {
            items.add("Phone");
        }
        if (((CheckBox) findViewById(R.id.GPSCheck)).isChecked()) {
            items.add("GPS");
        }
        if (((CheckBox) findViewById(R.id.BagCheck)).isChecked()) {
            items.add("Bag");
        }
        if (((CheckBox) findViewById(R.id.PetCheck)).isChecked()) {
            items.add("Pet");
        }
        if ((((EditText) findViewById(R.id.OtherText)).getText().toString()) != "") {
            items.add(((EditText) findViewById(R.id.OtherText)).getText().toString());
        }
        return items;
    }


}
