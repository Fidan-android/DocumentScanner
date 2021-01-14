package com.example.documentscanner.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.documentscanner.MainActivity;
import com.example.documentscanner.R;

public class ScannerFragment extends Fragment {

    private static final int REQUESTED_IMAGE_CAPTURE = 0;
    ImageView imageView;

    public ScannerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_scanner, container, false);

        Button take_picture = view.findViewById(R.id.btnTakePicture);
        take_picture.setOnClickListener(v -> {
            int status_camera = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA);
            int status_storage = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (status_camera == PackageManager.PERMISSION_GRANTED){
                if (status_storage == PackageManager.PERMISSION_GRANTED){
                    Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUESTED_IMAGE_CAPTURE);
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUESTED_IMAGE_CAPTURE);
                }
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{ Manifest.permission.CAMERA}, REQUESTED_IMAGE_CAPTURE);
            }
        });


        imageView = view.findViewById(R.id.imageView);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUESTED_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            assert extras != null;
            Bitmap pic = (Bitmap) extras.get("data");
            imageView.setImageBitmap(pic);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}