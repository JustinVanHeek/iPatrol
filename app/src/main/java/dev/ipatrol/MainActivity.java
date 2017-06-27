package dev.ipatrol;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import dev.ipatrol.objects.Patrol;

public class MainActivity extends AppCompatActivity {

    public static Patrol currentPatrol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Resources.resources = new Resources(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout continueButton = (LinearLayout) findViewById(R.id.ContinueButton);

        if (currentPatrol == null) {
            continueButton.setVisibility(View.GONE);
        }
        else {
            continueButton.setVisibility(View.VISIBLE);
            continueButton.setAlpha(1.0f);
            ViewGroup.LayoutParams layout = continueButton.getLayoutParams();
            layout.width = (ViewGroup.LayoutParams.WRAP_CONTENT);
            continueButton.setLayoutParams(layout);
        }

        LinearLayout button = (LinearLayout) findViewById(R.id.NewPatrolButton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goToNewPatrol();
            }

        });

        continueButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goToMenuActivity();
            }

        });
    }

    private void goToMenuActivity() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    private void goToNewPatrol() {
        currentPatrol = new Patrol();

        Intent intent = new Intent(this, NewPatrol.class);
        startActivity(intent);

    }



}
