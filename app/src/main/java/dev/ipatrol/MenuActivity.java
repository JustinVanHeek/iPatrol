package dev.ipatrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import dev.ipatrol.reports.GraffitiReport;

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

    }

    private void goToGraffiti() {
        Intent intent = new Intent(this, GraffitiReport.class);
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
