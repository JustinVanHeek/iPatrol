package dev.ipatrol;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

import dev.ipatrol.objects.Patrol;

public class NewPatrol extends AppCompatActivity {

    private int page = 0;
    private Patrol.PatrolType patrolType;

    LinearLayout dataRows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patrol);

        Button next = (Button) findViewById(R.id.NextButton);
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nextPage();
            }

        });

        Button back = (Button) findViewById(R.id.BackButton);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                prevPage();
            }

        });

        ImageButton walk = (ImageButton) findViewById(R.id.FootButton);
        walk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                patrolType = Patrol.PatrolType.Foot;
            }

        });

        ImageButton bike = (ImageButton) findViewById(R.id.BikeButton);
        bike.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                patrolType = Patrol.PatrolType.Bike;
            }

        });

        ImageButton car = (ImageButton) findViewById(R.id.CarButton);
        car.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                patrolType = Patrol.PatrolType.Car;
            }

        });

        ImageButton addPatroller = (ImageButton) findViewById(R.id.AddButton);
        addPatroller.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                addPatroller();
            }

        });


        dataRows = (LinearLayout) findViewById(R.id.DataRows);

        showForm();

        final LinearLayout layout = (LinearLayout) findViewById(R.id.DataRows);
        ViewTreeObserver vto = layout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener (new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                nextPage();
                prevPage();
                setDataRowHeight();

            }
        });

        updateDisplayData();


    }

    private void addPatroller() {
        LinearLayout patrollers = (LinearLayout) findViewById(R.id.PatrollersForm);
        if (((EditText)patrollers.getChildAt(patrollers.getChildCount() -1)).getText().toString() != "") {
            EditText newPatroller = new EditText(patrollers.getContext());
            patrollers.addView(newPatroller);
        }
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private void nextPage() {
        updatePatrolData(page);
        page++;
        if (page > 4) {
            goToMenuActivity();
        } else {
            hideForm(page - 1);
            moveDataRowUp();
            showForm();
        }
    }

    private void updatePatrolData(int page) {
        switch (page) {
            case 0: updatePatrollers();
                break;
            case 1: updateDateTime();
                break;
            case 2: updateLocation();
                break;
            case 3: updateConditions();
                break;
            case 4: updatePatrolType();
                break;
        }
        updateDisplayData();
    }

    private void updateDisplayData() {
        TextView patrollers = (TextView) findViewById(R.id.PatrollersText);
        TextView dateTime = (TextView) findViewById(R.id.DateTimeText);
        TextView location = (TextView) findViewById(R.id.LocationText);
        TextView conditions = (TextView) findViewById(R.id.ConditionsText);
        TextView type = (TextView) findViewById(R.id.PatrolTypeText);

        //Patrollers
        String patrollersString = "Patrollers: ";
        for(String name : MainActivity.currentPatrol.getPatrollers()) {
            patrollersString = patrollersString + name + ", ";
        }
        patrollersString = patrollersString.substring(0, patrollersString.length() - 2);

        patrollers.setText(patrollersString);

        //Date & Time
        Calendar time = MainActivity.currentPatrol.getStartTime();
        dateTime.setText("Date And Time: " + time.getTime().toString());

        //Location
        Location loc = MainActivity.currentPatrol.getStartLocation();
        location.setText("Location: " + LocationUtils.getClosestAddress(loc));

        //Conditions
        String weather = "";
        String lighting = "";
        if (MainActivity.currentPatrol.getWeather() != null) {
            weather = MainActivity.currentPatrol.getWeather().name();
        }
        if (MainActivity.currentPatrol.getLighting() != null) {
            lighting = MainActivity.currentPatrol.getLighting().name();
        }
        conditions.setText("Conditions: " + weather + ", " + lighting);

        //Patrol Type
        String patrol = "";
        if (MainActivity.currentPatrol.getPatrolType() != null) {
            patrol = MainActivity.currentPatrol.getPatrolType().name();
        }
        type.setText("Patrol Type: " + patrol);
    }

    private void updatePatrolType() {
        MainActivity.currentPatrol.setPatrolType(patrolType);
    }

    private void updateConditions() {
        Spinner weatherSpinner = (Spinner) findViewById(R.id.SpinnerWeather);
        String weather = weatherSpinner.getSelectedItem().toString();
        Spinner lightingSpinner = (Spinner) findViewById(R.id.SpinnerLight);
        String lighting = lightingSpinner.getSelectedItem().toString();

        MainActivity.currentPatrol.setWeather(Patrol.WeatherCondition.valueOf(weather));
        MainActivity.currentPatrol.setLighting(Patrol.LightingCondition.valueOf(lighting));
    }

    private void updateLocation() {
        //TODO
        MainActivity.currentPatrol.setStartLocation(null);
    }

    private void updateDateTime() {
        EditText monthField = (EditText) findViewById(R.id.Month);
        EditText dayField = (EditText) findViewById(R.id.Day);
        EditText yearField = (EditText) findViewById(R.id.Year);
        EditText hourField = (EditText) findViewById(R.id.Hour);
        EditText minuteField = (EditText) findViewById(R.id.Minute);

        try {
            int month = Integer.parseInt(monthField.toString());
            int day = Integer.parseInt(dayField.toString());
            int year = Integer.parseInt(yearField.toString());
            int hour = Integer.parseInt(hourField.toString());
            int minute = Integer.parseInt(minuteField.toString());

            Calendar time = Calendar.getInstance();
            time.set(year,month,day,hour,minute);

            MainActivity.currentPatrol.setStartTime(time);
        }
        catch (NumberFormatException e) {

        }


    }

    private void updatePatrollers() {
        MainActivity.currentPatrol.clearPatrollers();
        LinearLayout form = (LinearLayout) findViewById(R.id.PatrollersForm);
        for (int i = 0; i < form.getChildCount(); i++) {
            EditText nameField = (EditText) form.getChildAt(i);
            String name = nameField.getText().toString();
            MainActivity.currentPatrol.addPatroller(name);
        }
    }

    private void prevPage() {
        page--;
        if (page < 0) {
            goToMainActivity();
        } else {
            hideForm(page + 1);
            moveDataRowDown();
            showForm();
        }
    }

    private void showForm() {
        ConstraintLayout form = getForm(page);
        form.setVisibility(View.VISIBLE);
        form.setAlpha(0.0f);
        ViewGroup.LayoutParams layout = form.getLayoutParams();
        layout.height = (ViewGroup.LayoutParams.MATCH_PARENT);
        layout.width = (ViewGroup.LayoutParams.MATCH_PARENT);
        form.setLayoutParams(layout);
        form.animate().alpha(1.0f);
    }

    private void hideForm(int p) {
        ConstraintLayout form = getForm(p);
        form.animate().alpha(0.0f);
        form.setVisibility(View.GONE);
    }

    private ConstraintLayout getForm(int page) {
        ConstraintLayout form = null;
        switch (page) {
            case 0: form = (ConstraintLayout) findViewById(R.id.Patrollers);
                break;
            case 1: form = (ConstraintLayout) findViewById(R.id.DateTime);
                break;
            case 2: form = (ConstraintLayout) findViewById(R.id.Location);
                break;
            case 3: form = (ConstraintLayout) findViewById(R.id.Conditions);
                break;
            case 4: form = (ConstraintLayout) findViewById(R.id.PatrolType);
                break;
        }
        return form;
    }

    private void moveDataRowUp() {
        //Get data row height
        int dataRowHeight = dataRows.getHeight();
        int rowHeight = dataRowHeight/6; //7 rows of data (All equal heights)

        dataRows.animate().translationY(-rowHeight*page);
    }
    private void moveDataRowDown() {
        //Get data row height
        int dataRowHeight = dataRows.getHeight();
        int rowHeight = dataRowHeight/6; //7 rows of data (All equal heights)

        dataRows.animate().translationY(-rowHeight*page);
    }
    private void setDataRowHeight() {

        //Get screen height
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels - getStatusBarHeight();

        //Get data row height
        int dataRowHeight = dataRows.getHeight();
        int rowHeight = dataRowHeight/6; //7 rows of data (All equal heights)

        //Buttons + Each finished form row
        int showHeight = rowHeight + page*rowHeight;

        ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) dataRows.getLayoutParams();
        marginParams.setMargins(0, screenHeight-showHeight, 0, 0);
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    private void goToMenuActivity() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);

    }
}
