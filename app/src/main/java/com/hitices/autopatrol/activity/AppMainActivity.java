package com.hitices.autopatrol.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hitices.autopatrol.R;
import com.hitices.autopatrol.helper.PermissionHelper;

import org.opencv.android.OpenCVLoader;

public class AppMainActivity extends AppCompatActivity implements View.OnClickListener{

    // opencv
    static {
        if (!OpenCVLoader.initDebug()) {
            Log.d("PatrolMainActivity", "OpenCV not loaded");
        } else {
            Log.d("PatrolMainActivity", "OpenCV loaded！");
        }
        System.loadLibrary("native-lib");
    }

    private Button appFunc1Button, appFunc2Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ndk测试
        Log.d("PatrolMainActivity", stringFromJNI());

        setContentView(R.layout.activity_app_main);

        // 初始化权限
        PermissionHelper.checkAndRequestPermissions(this);
        // 初始化界面
        initUI();

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.app_func1_button:
                startActivity(new Intent(this, PatrolMainActivity.class));
                break;
            case R.id.app_func2_button:

                break;
        }
    }

    private void initUI() {
        appFunc1Button = findViewById(R.id.app_func1_button);
        appFunc2Button = findViewById(R.id.app_func2_button);
        appFunc1Button.setOnClickListener(this);
        appFunc2Button.setOnClickListener(this);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}