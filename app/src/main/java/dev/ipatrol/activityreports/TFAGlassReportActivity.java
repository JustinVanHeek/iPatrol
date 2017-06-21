package dev.ipatrol.activityreports;

import android.os.Bundle;

import dev.ipatrol.R;
import dev.ipatrol.objects.reports.Report;
import dev.ipatrol.objects.reports.TFAGlassReport;

public class TFAGlassReportActivity extends ReportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tfa_glass_report);
        setupCommonButtons();


    }

    protected Report createReport() {
        TFAGlassReport report = new TFAGlassReport(getReportTitle(),getImage(),getLocation(),getNotes());
        return report;
    }

}
