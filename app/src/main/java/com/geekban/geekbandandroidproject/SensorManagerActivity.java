package com.geekban.geekbandandroidproject;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class SensorManagerActivity extends AppCompatActivity implements SensorEventListener{

    private ImageView mImageView;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private float mStartDegree = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_manager);
        mImageView = findViewById(R.id.imageView);
        //获取传感器管理器对象
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //获取具体传感器类型
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //注册传感器监听器
        mSensorManager.registerListener(this,mSensor,SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //屏幕暂停时注销当前洛动的传感器监听器
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ORIENTATION){
            float degree = - event.values[0];
            RotateAnimation rotateAnimation = new RotateAnimation(
                    mStartDegree,degree, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f
            );
            rotateAnimation.setDuration(300);
            mImageView.startAnimation(rotateAnimation);
            mStartDegree = degree;
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //TODO:当传感器精度发生变化的时候回调该方法，一般无需处理

    }
}
