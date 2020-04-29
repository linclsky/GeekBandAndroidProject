package com.geekban.geekbandandroidproject;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2020/2/29.
 */

public class SendBroadcastActivity extends Activity {


    public static final String COM_GEEKBAND_TEST_BROADCAST = "com.geekband.test.broadcast";
    private TestBroadcastReceiver mTestBroadcastReceiver = new TestBroadcastReceiver();
    private Button mSendBroadcastButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_broadcast);



        mSendBroadcastButton = findViewById(R.id.send_broadcast_button);
        mSendBroadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送广播
                Intent intent = new Intent();
                intent.setAction(COM_GEEKBAND_TEST_BROADCAST);
                intent.putExtra("toast","这里是toast广播");
                sendBroadcast(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        //有一个intentFilter 动态注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(COM_GEEKBAND_TEST_BROADCAST);
        registerReceiver(mTestBroadcastReceiver,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mTestBroadcastReceiver);
    }
}
