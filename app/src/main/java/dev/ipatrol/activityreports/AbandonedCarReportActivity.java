package dev.ipatrol.activityreports;

import android.content.Intent;
import android.location.Location;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dev.ipatrol.MainActivity;
import dev.ipatrol.MenuActivity;
import dev.ipatrol.R;
import dev.ipatrol.objects.reports.AbandonedCarReport;
import dev.ipatrol.objects.reports.GraffitiReport;
import dev.ipatrol.objects.reports.Report;

public class AbandonedCarReportActivity extends ReportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abandoned_car_report);
        setupCommonButtons();

    }

    @Override
    protected Report createReport() {
        AbandonedCarReport report = new AbandonedCarReport(getReportTitle(),getImage(),getLocation(),getNotes());
        return report;
    }


}
