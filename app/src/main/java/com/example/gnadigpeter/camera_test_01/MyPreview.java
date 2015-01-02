package com.example.gnadigpeter.camera_test_01;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.util.Log;
import java.io.IOException;


/**
 * Created by gnadig.peter on 2014.12.28..
 */
public class MyPreview extends SurfaceView implements SurfaceHolder.Callback{

    private SurfaceHolder mHolder;
    private android.hardware.Camera mCamera;

    public MyPreview(Context context, android.hardware.Camera camera) {
        super(context);
        mCamera = camera;
        mHolder = getHolder();
        mHolder.addCallback(this);
        /* mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); */
    }

    public void setmCamera(android.hardware.Camera mCamera) {
        this.mCamera = mCamera;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d("CAM", "Failed to start preview: " + e.getMessage());
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // ToDo: nemtudommi de a könyv szerint kell ide valami???
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // ToDo: további funkciók ha kell???
    }
}
