package dev.ipatrol.activityreports;

import android.os.Bundle;

import dev.ipatrol.R;
import dev.ipatrol.objects.reports.Report;
import dev.ipatrol.objects.reports.ShopCenterReport;

public class ShopCenterReportActivity extends ReportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_center_report);
        setupCommonButtons();

    }

    protected Report createReport() {
        ShopCenterReport report = new ShopCenterReport(getReportTitle(),getImage(),getLocation(),getNotes());
        return report;
    }



}
