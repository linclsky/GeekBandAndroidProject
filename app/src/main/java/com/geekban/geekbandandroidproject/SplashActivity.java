package com.geekban.geekbandandroidproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {


    public static final String TITLE = "title";
    public static final String USER_INFO = "userInfo";
    public static final int REQUEST_CODE = 9999;

    private static final String TAG = SplashActivity.class.getSimpleName();
    Handler mHandler = new Handler();
    private TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mTextView = findViewById(R.id.title_text_view);
       final String title = mTextView.getText().toString();
       mHandler.postDelayed(new Runnable() {
           @Override
           public void run() {
               //初始化usreinfo
               UserInfo userInfo = new UserInfo("小白",12);
               // //延迟一秒自动跳转到mainactivity
               Intent intent = new Intent(SplashActivity.this,MainActivity.class);
               //放数据包
               intent.putExtra(TITLE,title);
               intent.putExtra(USER_INFO,userInfo);
               //启动Activity以获得结果
               startActivityForResult(intent,REQUEST_CODE);

           }
       },1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //可以获取到mainactivity的返回结果码
        Log.i(TAG,"requestcode:" + requestCode + ",resultcode:" + resultCode);
        if (requestCode == REQUEST_CODE && resultCode == MainActivity.RESULT_CODE)
        {
            //可以取得intent里带的data数据
            if (data != null){
                String tetle = data.getStringExtra(TITLE);
                mTextView.setText(tetle);
            }
        }

    }
}
