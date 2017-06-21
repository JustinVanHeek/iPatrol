package dev.ipatrol.activityreports;

import android.os.Bundle;

import dev.ipatrol.R;
import dev.ipatrol.objects.reports.MischiefReport;
import dev.ipatrol.objects.reports.Report;

public class MischiefReportActivity extends ReportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mischief_report);


        setupCommonButtons();

    }

    protected Report createReport() {
        MischiefReport report = new MischiefReport(getReportTitle(),getImage(),getLocation(),getNotes());
        return report;
    }


}
