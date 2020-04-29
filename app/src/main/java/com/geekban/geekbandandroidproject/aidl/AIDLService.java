package com.geekban.geekbandandroidproject.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.geekban.geekbandandroidproject.IMyAidlInterface;

/**
 * Created by Administrator on 2020/3/10.
 */

public class AIDLService extends Service {
    IMyAidlInterface.Stub mStub = new IMyAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String getName(String nickName) throws RemoteException {
            //通过IMyAidlInterface里新建getname方法，传过AIDLActivity里进行数据通信
            return nickName + "我是aidfl";
        }
    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mStub;
    }
}
