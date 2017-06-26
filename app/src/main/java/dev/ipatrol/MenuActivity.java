package dev.ipatrol;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;

import dev.ipatrol.activityreports.AbandonedCarReportActivity;
import dev.ipatrol.activityreports.AlcoholReportActivity;
import dev.ipatrol.activityreports.BushPartyReportActivity;
import dev.ipatrol.activityreports.BusinessReportActivity;
import dev.ipatrol.activityreports.CrimePreventionReportActivity;
import dev.ipatrol.activityreports.GraffitiReportActivity;
import dev.ipatrol.activityreports.IllegalDumpingReportActivity;
import dev.ipatrol.activityreports.MischiefReportActivity;
import dev.ipatrol.activityreports.OpenDoorReportActivity;
import dev.ipatrol.activityreports.OtherReportActivity;
import dev.ipatrol.activityreports.RecFacilityReportActivity;
import dev.ipatrol.activityreports.RecklessDrivingReportActivity;
import dev.ipatrol.activityreports.SuspiciousReportActivity;
import dev.ipatrol.objects.Patrol;
import dev.ipatrol.objects.reports.BushPartyReport;
import dev.ipatrol.objects.reports.BusinessReport;
import dev.ipatrol.objects.reports.OpenDoorReport;

public class MenuActivity extends AppCompatActivity {


    public static final int MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TextView back = (TextView) findViewById(R.id.Back);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goToNewPatrol();
            }

        });

        TextView finish = (TextView) findViewById(R.id.Finish);
        finish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finishPatrol();
            }

        });

        LinearLayout graffiti = (LinearLayout) findViewById(R.id.GraffitiTile);
        graffiti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGraffiti();
            }
        });

        LinearLayout mischief = (LinearLayout) findViewById(R.id.MischiefTile);
        mischief.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMischief();
            }
        });

        LinearLayout dumping = (LinearLayout) findViewById(R.id.IllegalDumpTile);
        dumping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDumping();
            }
        });

        LinearLayout business = (LinearLayout) findViewById(R.id.BusinessTile);
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBusiness();
            }
        });

        LinearLayout bush = (LinearLayout) findViewById(R.id.BushPartyTile);
        bush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBushParty();
            }
        });

        LinearLayout facilities = (LinearLayout) findViewById(R.id.PublicFacilitiesTile);
        facilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFacilities();
            }
        });

        LinearLayout suspicious = (LinearLayout) findViewById(R.id.SuspiciousTile);
        suspicious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSuspicious();
            }
        });

        LinearLayout alcohol = (LinearLayout) findViewById(R.id.AlcoholTile);
        alcohol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAlcohol();
            }
        });

        LinearLayout reckless = (LinearLayout) findViewById(R.id.RecklessTile);
        reckless.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToReckless();
            }
        });

        LinearLayout open = (LinearLayout) findViewById(R.id.OpenAccessTile);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToOpenAccess();
            }
        });

        LinearLayout abandoned = (LinearLayout) findViewById(R.id.AbandonedTile);
        abandoned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAbandoned();
            }
        });

        LinearLayout crimePrev = (LinearLayout) findViewById(R.id.CrimePreventTile);
        crimePrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCrimePrev();
            }
        });

        LinearLayout other = (LinearLayout) findViewById(R.id.OtherTile);
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToOther();
            }
        });

        if(MainActivity.currentPatrol.getPatrolType() == null) {
            MainActivity.currentPatrol.setPatrolType(Patrol.PatrolType.Foot);
        }
        ((TextView) findViewById(R.id.PatrolTitle)).setText(MainActivity.currentPatrol.getPatrolType().name() + " Patrol");

    }

    private void goToBusiness() {
        //TODO
        Intent intent = new Intent(this, BusinessReportActivity.class);
        startActivity(intent);
    }

    private void goToBushParty() {
        Intent intent = new Intent(this, BushPartyReportActivity.class);
        startActivity(intent);
    }

    private void goToFacilities() {
        //TODO
        Intent intent = new Intent(this, RecFacilityReportActivity.class);
        startActivity(intent);
    }

    private void goToSuspicious() {
        Intent intent = new Intent(this, SuspiciousReportActivity.class);
        startActivity(intent);
    }

    private void goToOpenAccess() {
        //TODO
        Intent intent = new Intent(this, OpenDoorReportActivity.class);
        startActivity(intent);
    }

    private void goToGraffiti() {
        Intent intent = new Intent(this, GraffitiReportActivity.class);
        startActivity(intent);
    }

    private void goToMischief() {
        //TODO
        Intent intent = new Intent(this, MischiefReportActivity.class);
        startActivity(intent);
    }

    private void goToDumping() {
        Intent intent = new Intent(this, IllegalDumpingReportActivity.class);
        startActivity(intent);
    }



    private void goToAlcohol() {
        Intent intent = new Intent(this, AlcoholReportActivity.class);
        startActivity(intent);
    }

    private void goToReckless() {
        Intent intent = new Intent(this, RecklessDrivingReportActivity.class);
        startActivity(intent);
    }



    private void goToAbandoned() {
        Intent intent = new Intent(this, AbandonedCarReportActivity.class);
        startActivity(intent);
    }

    private void goToCrimePrev() {
        Intent intent = new Intent(this, CrimePreventionReportActivity.class);
        startActivity(intent);
    }

    private void goToOther() {
        Intent intent = new Intent(this, OtherReportActivity.class);
        startActivity(intent);
    }

    private void goToNewPatrol() {
        Intent intent = new Intent(this, NewPatrol.class);
        startActivity(intent);
    }

    private void finishPatrol() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
           /* if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {*/

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            //}
        }
        else {
            MainActivity.currentPatrol.finishReport();
            MainActivity.currentPatrol.printFullReport();
            //TODO
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            String filename="report.pdf";
            File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), filename);
            Uri path = Uri.fromFile(filelocation);
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
// set the type to 'email'
            emailIntent .setType("vnd.android.cursor.dir/email");
            String to[] = {"justinvanheek@gmail.com"};
            emailIntent .putExtra(Intent.EXTRA_EMAIL, to);
// the attachment
            emailIntent .putExtra(Intent.EXTRA_STREAM, path);
// the mail subject
            emailIntent .putExtra(Intent.EXTRA_SUBJECT, "Subject");
            startActivity(Intent.createChooser(emailIntent , "Send email..."));
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
