package dev.ipatrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import dev.ipatrol.activityreports.AbandonedCarReportActivity;
import dev.ipatrol.activityreports.AlcoholReportActivity;
import dev.ipatrol.activityreports.CrimePreventionReportActivity;
import dev.ipatrol.activityreports.DrugUseReportActivity;
import dev.ipatrol.activityreports.GraffitiReportActivity;
import dev.ipatrol.activityreports.IllegalDumpingReportActivity;
import dev.ipatrol.activityreports.MischiefReportActivity;
import dev.ipatrol.activityreports.OffLeashReportActivity;
import dev.ipatrol.activityreports.OtherReportActivity;
import dev.ipatrol.activityreports.PoliceCallReportActivity;
import dev.ipatrol.activityreports.RecklessDrivingReportActivity;
import dev.ipatrol.activityreports.SpeedingReportActivity;
import dev.ipatrol.activityreports.StreetPerformingReportActivity;

public class MenuActivity extends AppCompatActivity {


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

        LinearLayout police = (LinearLayout) findViewById(R.id.PoliceCallTile);
        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPolice();
            }
        });

        LinearLayout streetPerf = (LinearLayout) findViewById(R.id.StreetPerformTile);
        streetPerf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToStreetPerf();
            }
        });

        LinearLayout leash = (LinearLayout) findViewById(R.id.OffLeashTile);
        leash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLeash();
            }
        });

        LinearLayout drug = (LinearLayout) findViewById(R.id.DrugTile);
        drug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDrug();
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

        LinearLayout speeding = (LinearLayout) findViewById(R.id.SpeedingTile);
        speeding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSpeeding();
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




    }

    private void goToGraffiti() {
        Intent intent = new Intent(this, GraffitiReportActivity.class);
        startActivity(intent);
    }

    private void goToMischief() {
        Intent intent = new Intent(this, MischiefReportActivity.class);
        startActivity(intent);
    }

    private void goToDumping() {
        Intent intent = new Intent(this, IllegalDumpingReportActivity.class);
        startActivity(intent);
    }

    private void goToPolice() {
        Intent intent = new Intent(this, PoliceCallReportActivity.class);
        startActivity(intent);
    }

    private void goToStreetPerf() {
        Intent intent = new Intent(this, StreetPerformingReportActivity.class);
        startActivity(intent);
    }

    private void goToLeash() {
        Intent intent = new Intent(this, OffLeashReportActivity.class);
        startActivity(intent);
    }

    private void goToDrug() {
        Intent intent = new Intent(this, DrugUseReportActivity.class);
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

    private void goToSpeeding() {
        Intent intent = new Intent(this, SpeedingReportActivity.class);
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
        //TODO
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
