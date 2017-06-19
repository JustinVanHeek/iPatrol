package dev.ipatrol.reports;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import dev.ipatrol.MenuActivity;
import dev.ipatrol.R;

public class IllegalDumpingReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illegal_dumping_report);



        Button cancel = (Button) findViewById(R.id.CancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cancel();
            }

        });


        Button email = (Button) findViewById(R.id.EmailButton);
        email.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                email();
            }

        });


        Button submit = (Button) findViewById(R.id.OKButton);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                submit();
            }

        });
    }

    private void submit() {
        //TODO
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    private void email() {
        //TODO
    }

    private void cancel() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
