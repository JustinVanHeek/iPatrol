package dev.ipatrol.activityreports;

import android.os.Bundle;

import dev.ipatrol.R;
import dev.ipatrol.objects.reports.Report;
import dev.ipatrol.objects.reports.ShopReport;

public class ShopReportActivity extends ReportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_report);
        setupCommonButtons();

    }

    protected Report createReport() {
        ShopReport report = new ShopReport(getReportTitle(),getImage(),getLocation(),getNotes());
        return report;
    }



}
