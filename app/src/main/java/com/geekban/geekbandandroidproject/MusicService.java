package com.geekban.geekbandandroidproject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by Administrator on 2020/2/28.
 */

public class MusicService extends Service {
    private static final String TAG = MusicService.class.getSimpleName();
    private MediaPlayer mMediaPlayer;
    private IBinder mIBinder = new LocalBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate");
        mMediaPlayer = MediaPlayer.create(this,R.raw.dadi);

        // 即将发的的intent，要用的时候可以用
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,new Intent(this,MusicButtonActivity.class),0);
        Notification.Builder mBuilder =
                new Notification.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("我是标题")
                        .setContentText("我是文本");

        mBuilder.setContentIntent(pendingIntent);
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
// Builds the notification and issues it.
        mNotifyMgr.notify(1, mBuilder.build());

        Log.d(TAG, "Activity thread id:" + Thread.currentThread().getId());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"onStartCommand");
        mMediaPlayer.start();
        return START_NOT_STICKY;
    }


    @Override
    public void onDestroy() {
        mMediaPlayer.stop();
        Log.i(TAG,"onDestroy");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //通过binder把数据传到ActivityService
        return mIBinder;
    }

    public class LocalBinder extends Binder{
        MusicService getService(){

            return MusicService.this;
        }

    }
    public int getMusicPlayProgress(){
        return 18;
    }
}
