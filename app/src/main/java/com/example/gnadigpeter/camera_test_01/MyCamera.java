package com.example.gnadigpeter.camera_test_01;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.hardware.Camera.*;

import java.io.ByteArrayInputStream;


public class MyCamera extends ActionBarActivity {

    private android.hardware.Camera mCamera;
    private MyPreview mPreview;
    private ImageView ivPhoto;
    private FrameLayout preview;

    private android.hardware.Camera.PictureCallback mPicture = new android.hardware.Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, android.hardware.Camera camera) {
            Log.d("CAM", " Size: " + data.length);
            ByteArrayInputStream imageStream = new ByteArrayInputStream(data);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            ivPhoto.setImageBitmap(theImage);
            /* mCamera.stopPreview ;*/
            preview.removeView(mPreview);
            ivPhoto.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mCamera= android.hardware.Camera.open(0);
        mPreview = new MyPreview(this, mCamera);
        preview = (FrameLayout) findViewById(R.id.cameraPreview);
        preview.addView(mPreview);
        ivPhoto = (ImageView) findViewById(R.id.ivPhoto);

        final Button captureButton = (Button) findViewById(R.id.buttonPhoto);
        captureButton.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCamera.takePicture(null, null, mPicture);
                        /* captureButton.setEnabled(false); */
                        /* newButton.setEnabled(true);*/
                        if (mCamera != null) {
                            mCamera.release();
                        }
                    }
                }
        );
        final Button newButton = (Button) findViewById(R.id.buttonNewPhoto);
        newButton.setOnClickListener(
                new OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        mCamera= android.hardware.Camera.open(0);
                        mPreview = new MyPreview(this, mCamera);
                        preview = (FrameLayout) findViewById(R.id.cameraPreview);
                        preview.addView(mPreview);
                        ivPhoto.setVisibility(View.INVISIBLE);
                        /*captureButton.setEnabled(true);*/
                        /*newButton.setEnabled(false);*/
                    }
                }
        );

    }



    @Override
    protected void onStop(){
        if (mCamera != null){
            mCamera.release();
        }
    }

}
