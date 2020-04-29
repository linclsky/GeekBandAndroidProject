package com.geekban.geekbandandroidproject;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class HandlerButtonActivity extends AppCompatActivity {
    public static final int MESSAGE_CODE = 888888;

    private TextView mTextView;
    private TestHandler mTestHandler = new TestHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_button);
        mTextView = findViewById(R.id.handler_text_view);
        //声明消息管理器
        Message message = mTestHandler.obtainMessage();
        // 传递消息
        message.arg1 = 0;
        message.arg2 = 1;
        message.what = MESSAGE_CODE;
        message.obj = 10000;


        //mHandler.sendMessageDelayed(message,1000);
        mTestHandler.sendMessageDelayed(message,1000);

    }

    public TextView getTextView() {
        return mTextView;
    }

    public static class TestHandler extends Handler{
        //弱引用
        public final WeakReference<HandlerButtonActivity> mHandlerButtonActivityWeakReference;
        public TestHandler(HandlerButtonActivity activity){
            //初始化activity弱引用
            mHandlerButtonActivityWeakReference = new WeakReference<>(activity);
        }


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            HandlerButtonActivity handlerButtonActivity = mHandlerButtonActivityWeakReference.get();

            //接收消息
            switch (msg.what) {
                case MESSAGE_CODE:
                    int value = (int)msg.obj;
                    handlerButtonActivity.getTextView().setText(String.valueOf(value/1000));
                    msg = Message.obtain();
                    msg.arg1 = 0;
                    msg.arg2 = 1;
                    msg.what = MESSAGE_CODE;
                    msg.obj = value - 1000;

                    if (value > 0 )
                        sendMessageDelayed(msg,1000);
                    break;
            }
        }
    }
}
