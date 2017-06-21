package dev.ipatrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dev.ipatrol.objects.Patrol;

public class MainActivity extends AppCompatActivity {

    public static Patrol currentPatrol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.new_patrol_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goToNewPatrol();
            }

        });

    }

    private void goToNewPatrol() {
        currentPatrol = new Patrol();

        Intent intent = new Intent(this, NewPatrol.class);
        startActivity(intent);

    }



}
