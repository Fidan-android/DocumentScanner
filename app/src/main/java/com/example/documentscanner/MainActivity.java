package com.example.documentscanner;
import java.io.File;
import java.io.FileOutputStream;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.SurfaceHolder;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.documentscanner.ui.FilesFragment;
import com.example.documentscanner.ui.PicturesFragment;
import com.example.documentscanner.ui.ScannerFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUESTED_IMAGE_CAPTURE = 0;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, new ScannerFragment()).commit();
        BottomNavigationView nav = findViewById(R.id.bottom_nav);
        nav.setOnNavigationItemSelectedListener(item -> {
            Fragment selected = null;
            switch (item.getItemId()){
                case R.id.scan:
                    selected = new ScannerFragment();
                    break;
                case R.id.photos:
                    selected = new PicturesFragment();
                    break;
                case R.id.files:
                    selected = new FilesFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, selected).commit();
            return true;
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUESTED_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            assert extras != null;
            Bitmap pic = (Bitmap) extras.get("data");
            imageView.setImageBitmap(pic);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
/*
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    protected static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 0;
    Uri photoURI1;
    Uri oldPhotoURI;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.photo);

        button.setOnClickListener(v -> {
            //prepare intent
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    Toast.makeText(this, "errorFileCreate", Toast.LENGTH_SHORT).show();
                    Log.i("File error", ex.toString());
                }
                // Continue only if the File was successfully created
                if (photoFile != null) {
                    oldPhotoURI = photoURI1;
                    photoURI1 = Uri.fromFile(photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI1);
                    startActivityForResult(takePictureIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                }
            }
        });

        imageView = findViewById(R.id.textResult);
    }

    public File createImageFile() throws IOException {
        // Create an image file name
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("MMdd_HHmmss").format(new Date());
        String imageFileName = "DOCPARSE_" + timeStamp + "_";
        File storageDir = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  */
/* prefix *//*

                ".jpg",         */
/* suffix *//*

                storageDir      */
/* directory *//*

        );
        // Save a file: path for use with ACTION_VIEW intents
        String mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE: {
                if (resultCode == RESULT_OK) {
                    Bitmap bmp = null;
                    try {
                        InputStream is = this.getContentResolver().openInputStream(photoURI1);
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        bmp = BitmapFactory.decodeStream(is, null, options);

                    } catch (Exception ex) {
                        Log.i(getClass().getSimpleName(), ex.getMessage());
                        Toast.makeText(this, "errorConvert", Toast.LENGTH_SHORT).show();
                    }

                    imageView.setImageBitmap(bmp);
                    //doOCR(bmp);

                    OutputStream os;
                    try {
                        os = new FileOutputStream(photoURI1.getPath());
                        if (bmp != null) {
                            bmp.compress(Bitmap.CompressFormat.JPEG, 100, os);
                        }
                        os.flush();
                        os.close();
                    } catch (Exception ex) {
                        Log.e(getClass().getSimpleName(), ex.getMessage());
                        Toast.makeText(this, "errorFileCreate", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    {
                        photoURI1 = oldPhotoURI;
                        imageView.setImageURI(photoURI1);
                    }
                }
            }
        }
    }
}*/
