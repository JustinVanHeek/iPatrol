package dev.ipatrol.activityreports;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dev.ipatrol.MainActivity;
import dev.ipatrol.MenuActivity;
import dev.ipatrol.R;
import dev.ipatrol.objects.reports.Report;

/**
 * Created by Justin.vanHeek on 6/21/2017.
 */

public abstract class ReportActivity extends AppCompatActivity {

    protected Bitmap image;

    protected void setupCommonButtons() {
        ImageView camera = (ImageView) findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                takePhoto();
            }

        });

        Button cancel = (Button) findViewById(R.id.CancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cancel();
            }

        });


        /*Button email = (Button) findViewById(R.id.EmailButton);
        email.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                email();
            }

        });*/


        Button submit = (Button) findViewById(R.id.OKButton);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                submit();
            }

        });
    }

    private void submit() {
        MainActivity.currentPatrol.addReport(createReport());
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    private void takePhoto() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, 1888);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1888 && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ImageView camera = (ImageView) findViewById(R.id.camera);
            camera.setImageBitmap(photo);
            image = photo;
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        String mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    protected abstract Report createReport();

    protected String getReportTitle() {
        return ((TextView) findViewById(R.id.title)).getText().toString();
    }

    protected Bitmap getImage() {
        return image;
    }

    protected String getNotes() {
        return ((EditText) findViewById(R.id.NotesText)).getText().toString();
    }

    protected Location getLocation() {
        String locationInput = ((EditText) findViewById(R.id.LocationText)).getText().toString();
        //TODO
        return null;
    }

    private void email() {
        //TODO
    }

    private void cancel() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
