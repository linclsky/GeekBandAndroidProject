package com.geekban.geekbandandroidproject.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2020/3/9.
 */

public class MessengerService extends Service{
    public static final String TAG = MessengerService.class.getSimpleName();
    Messenger mMessenger = new Messenger(new IncomingHandler());

    class IncomingHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //处理消息
            switch (msg.what){
                case 0:
                    String str = (String) msg.obj;
                    Log.i(TAG,"我收到消息了" + str);
                    break;
            }
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //通过binder把数据传到ActivityService
        return mMessenger.getBinder();
    }
}
