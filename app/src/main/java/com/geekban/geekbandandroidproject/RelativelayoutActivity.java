package com.geekban.geekbandandroidproject;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.geekban.geekbandandroidproject.aidl.AIDLActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class RelativelayoutActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = RelativelayoutActivity.class.getSimpleName();


    private Button mListViewbutton;
    private Button mGridViewbutton;
    private Button mTestViewbutton;
    private Button mFragmentbutton;
    private Button mHandlerbutton;
    private Button mMusicebutton;
    private Button mBroadcastbutton;
    private Button mWebViewbutton;
    private Button mDatabasebutton;
    private Button mNetWorkbutton;
    private Button mThreadbutton;
    private Button mAidlButton;
    private Button mSensorButton;
    private Button mMapbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_layout);
        mListViewbutton = findViewById(R.id.list_view_button);
        mListViewbutton.setOnClickListener(this);
        mGridViewbutton = findViewById(R.id.grid_view_button);
        mGridViewbutton.setOnClickListener(this);
        mTestViewbutton = findViewById(R.id.test_view_button);
        mTestViewbutton.setOnClickListener(this);
        mFragmentbutton = findViewById(R.id.fragment_button);
        mFragmentbutton.setOnClickListener(this);
        mHandlerbutton = findViewById(R.id.handlern_button);
        mHandlerbutton.setOnClickListener(this);
        mMusicebutton = findViewById(R.id.musice_button);
        mMusicebutton.setOnClickListener(this);
        mBroadcastbutton = findViewById(R.id.broadcast_button);
        mBroadcastbutton.setOnClickListener(this);
        mWebViewbutton = findViewById(R.id.web_view_button);
        mWebViewbutton.setOnClickListener(this);
        mDatabasebutton = findViewById(R.id.database_button);
        mDatabasebutton.setOnClickListener(this);
        mNetWorkbutton =  findViewById(R.id.net_work_button);
        mNetWorkbutton.setOnClickListener(this);
        mThreadbutton = findViewById(R.id.thread_button);
        mThreadbutton.setOnClickListener(this);
        mAidlButton = findViewById(R.id.button_aidl);
        mAidlButton.setOnClickListener(this);
        mSensorButton = findViewById(R.id.sensor_button);
        mSensorButton.setOnClickListener(this);
        mMapbutton = findViewById(R.id.map_button);
        mMapbutton.setOnClickListener(this);


        testFileDemo();
    }
    //测试文件的演示
    private void testFileDemo() {
        String string = "这里创建了fileOutputStream";
        //创建文件internal storage 内部存储
        File file = new File(getFilesDir(),"test.txt");
        try {
          boolean isSuccess =  file.createNewFile();
        } catch (IOException e) {
            Log.i(TAG,"文件创建不成功"+e.toString());
            e.printStackTrace();
        }
        try {
            FileOutputStream fileOutputStream = openFileOutput("text2", Context.MODE_PRIVATE);
            try {
                fileOutputStream.write(string.getBytes());
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //判断SD卡存储是否可用
        String state = Environment.getExternalStorageState();
        if(TextUtils.equals(state,Environment.MEDIA_MOUNTED)){

        }

    }
    void testAssets() throws IOException {
        WebView webView = new WebView(this);
        webView.loadUrl("file:///android_asset/test.html");
        try {
            //open的只能是文件，不能是文件夹
            InputStream inputStream = getResources().getAssets().open("test.html");
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(RelativelayoutActivity.this,"文件读取异常",Toast.LENGTH_LONG).show();
        }
        //读列表
        String[] filenames = getAssets().list("image");
        //读图片
        InputStream inputStream = getAssets().open("image/dog.jpg");
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(bitmap);
        //读音乐
        AssetFileDescriptor assetFileDescriptor =  getAssets().openFd("dadi.mp3");
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),
                assetFileDescriptor.getStartOffset(),
                assetFileDescriptor.getLength());
        mediaPlayer.prepare();
        mediaPlayer.start();

    }
    void testResFile(){
        InputStream inputStream = getResources().openRawResource(R.raw.dadi);
    }
    void testSdCard(){
        //读sd卡
        File file = new File("/sdcard/test/a.txt");
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        Environment.getDataDirectory();//获取android中的data 数据 目录
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.list_view_button:
                startActivity(new Intent(RelativelayoutActivity.this,ListViewDemoActivity.class));
                break;
            case R.id.grid_view_button:
                startActivity(new Intent(RelativelayoutActivity.this,GridViewDemoActivity.class));
                break;
            case R.id.test_view_button:
                startActivity(new Intent(RelativelayoutActivity.this,TestViewButtonActivity.class));
                break;
            case R.id.fragment_button:
                startActivity(new Intent(RelativelayoutActivity.this,TestFragmentActivity.class));
                break;
            case R.id.handlern_button:
                startActivity(new Intent(RelativelayoutActivity.this,HandlerButtonActivity.class));
                break;
            case R.id.musice_button:
                startActivity(new Intent(RelativelayoutActivity.this,MusicButtonActivity.class));
                break;
            case R.id.broadcast_button:
                startActivity(new Intent(RelativelayoutActivity.this,SendBroadcastActivity.class));
                break;
            case R.id.web_view_button:
                startActivity(new Intent(RelativelayoutActivity.this,WebViewButtonActivity.class));
                break;
            case R.id.database_button:
                startActivity(new Intent(RelativelayoutActivity.this,DatabaseButtonActivity.class));
                break;
            case R.id.net_work_button:
                startActivity(new Intent(RelativelayoutActivity.this,NetworkActivity.class));
                break;
            case R.id.thread_button:
                startActivity(new Intent(RelativelayoutActivity.this,ThreadActivity.class));
                break;
            case R.id.button_aidl:
                startActivity(new Intent(RelativelayoutActivity.this,AIDLActivity.class));
                break;
            case R.id.sensor_button:
                startActivity(new Intent(RelativelayoutActivity.this,SensorManagerActivity.class));
                break;
            case R.id.map_button:
                startActivity(new Intent(RelativelayoutActivity.this,MyMapActivity.class));
                break;
        }

    }
}
