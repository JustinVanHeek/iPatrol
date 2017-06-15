package dev.ipatrol;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class NewPatrol extends AppCompatActivity {

    private int page = 0;

    LinearLayout dataRows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patrol);

        Button button = (Button) findViewById(R.id.new_patrol_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nextPage();
            }

        });

        dataRows = (LinearLayout) findViewById(R.id.DataRows);

    }

    private void nextPage() {
        page++;
        if (page > 5) {
            goToMenuActivity();
        } else {
            changePage();
        }
    }

    private void prevPage() {
        page--;
        if (page < 0) {
            goToMainActivity();
        } else {
            changePage();
        }
    }

    private void changePage() {
        hideCurrentForm();
        setDataRowHeight();
        showForm();
    }

    private void showForm() {
        ConstraintLayout form = getForm(page);
        form.setVisibility(View.VISIBLE);
        ViewGroup.LayoutParams layout = form.getLayoutParams();
        layout.height = (ViewGroup.LayoutParams.MATCH_PARENT);
        layout.width = (ViewGroup.LayoutParams.MATCH_PARENT);
        form.setLayoutParams(layout);
    }

    private void hideCurrentForm() {
        ConstraintLayout form = getForm(page - 1);
        form.setVisibility(View.GONE);
    }

    private ConstraintLayout getForm(int page) {
        ConstraintLayout form = null;
        switch (page) {
            case 0: form = (ConstraintLayout) findViewById(R.id.UserLogin);
                break;
            case 1: form = (ConstraintLayout) findViewById(R.id.Patrollers);
                break;
            case 2: form = (ConstraintLayout) findViewById(R.id.DateTime);
                break;
            case 3: form = (ConstraintLayout) findViewById(R.id.Location);
                break;
            case 4: form = (ConstraintLayout) findViewById(R.id.Conditions);
                break;
            case 5: form = (ConstraintLayout) findViewById(R.id.PatrolType);
                break;
        }
        return form;
    }

    private void setDataRowHeight() {

        //Get screen height
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels;

        //Get data row height
        int dataRowHeight = dataRows.getHeight();
        int rowHeight = dataRowHeight/7; //7 rows of data (All equal heights)

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
