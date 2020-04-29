package com.geekban.geekbandandroidproject;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.nfc.Tag;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MusicButtonActivity extends AppCompatActivity implements View.OnClickListener {


    private Button mStartbutton;
    private Button mStopbutton;
    private MusicService mMMusicService;
    private static final String TAG = MusicService.class.getSimpleName();
    private ServiceConnection mServiceConnection = new ServiceConnection() {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //找到service传进来的内部类数据，获取到数据
            MusicService.LocalBinder localBinder = (MusicService.LocalBinder)service;
            mMMusicService = localBinder.getService();

        }


        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_button);
        mStartbutton = findViewById(R.id.start);
        mStopbutton = findViewById(R.id.stop);
        mStartbutton.setOnClickListener(this);
        mStopbutton.setOnClickListener(this);

        if (mMMusicService != null){
            int progress = mMMusicService.getMusicPlayProgress();

        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MusicButtonActivity.this, MusicService.class);
        switch (v.getId()){

            case R.id.start:


               // intent.putExtra()传递数据
                startService(intent);//绑定服务
                bindService(intent,mServiceConnection,BIND_AUTO_CREATE);
                break;
            case R.id.stop:
                unbindService(mServiceConnection);//解绑服务
                stopService(intent);
                break;
        }
    }
}
