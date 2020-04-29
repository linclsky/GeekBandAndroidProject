
package com.geekban.geekbandandroidproject.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.geekban.geekbandandroidproject.IMyAidlInterface;
import com.geekban.geekbandandroidproject.R;
import com.geekban.geekbandandroidproject.messenger.MessengerService;


public class AIDLActivity extends AppCompatActivity {
    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //把ibinder service传进去
            mIMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);

        }


        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private IMyAidlInterface mIMyAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        findViewById(R.id.aidl_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIMyAidlInterface != null){
                    try {
                       String name =  mIMyAidlInterface.getName("接收到了AIDL");
                        Toast.makeText(AIDLActivity.this,name,Toast.LENGTH_LONG).show();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        bindService(new Intent(this, AIDLService.class),mServiceConnection, Context.BIND_AUTO_CREATE);
    }
}
