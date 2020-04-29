package com.geekban.geekbandandroidproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * 接收广播
 * Created by Administrator on 2020/2/29.
 */

public class TestBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //注册广播  处理数据，接收广播
        if (intent != null){
            if (TextUtils.equals(intent.getAction(),SendBroadcastActivity.COM_GEEKBAND_TEST_BROADCAST));
            String toastString = intent.getStringExtra("toast");
            Toast.makeText(context,toastString,Toast.LENGTH_LONG).show();
        }

    }
}
