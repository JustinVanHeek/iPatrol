package dev.ipatrol.activityreports;

import android.os.Bundle;

import dev.ipatrol.R;
import dev.ipatrol.objects.reports.OtherReport;
import dev.ipatrol.objects.reports.Report;

public class OtherReportActivity extends ReportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_report);
        setupCommonButtons();

    }

    protected Report createReport() {
        OtherReport report = new OtherReport(getReportTitle(),getImage(),getLocation(),getNotes());
        return report;
    }


}
