package dev.ipatrol;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;

public class NewPatrol extends AppCompatActivity {

    private int page = 0;

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
        page++;
        if (page > 5) {
            goToMenuActivity();
        } else {
            hideForm(page - 1);
            moveDataRowUp();
            showForm();
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

    private void moveDataRowUp() {
        //Get data row height
        int dataRowHeight = dataRows.getHeight();
        int rowHeight = dataRowHeight/7; //7 rows of data (All equal heights)

        dataRows.animate().translationY(-rowHeight*page);
    }
    private void moveDataRowDown() {
        //Get data row height
        int dataRowHeight = dataRows.getHeight();
        int rowHeight = dataRowHeight/7; //7 rows of data (All equal heights)

        dataRows.animate().translationY(rowHeight*page);
    }
    private void setDataRowHeight() {

        //Get screen height
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels - getStatusBarHeight();

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
