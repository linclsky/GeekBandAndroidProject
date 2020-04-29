package com.geekban.geekbandandroidproject;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ThreadActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String GEEK_BAND = "GeekBand";
    private TextView mTextView;
    private Button mDownloadButton;
    private ProgressBar mProgressBar;
    public static final String TAG = ThreadActivity.class.getSimpleName();
    private Handler mHandler = new DownloadHandler(this);

    public TextView getTextView() {
        return mTextView;
    }

    public ProgressBar getProgressBar() {
        return mProgressBar;
    }

    private static final String APK_URL = "https://download.sj.qq.com/upload/connAssitantDownload/upload/MobileAssistant_1.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        mTextView = findViewById(R.id.thread_text_view);


//        new Thread(new Runnable() {
//            //第一种方法
//            @Override
//            public void run() {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mTextView.setText("我在其它线程中来看你");
//                    }
//                });
//                //第二种方法
//                mTextView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        mTextView.setText("我在其它线程中来看你");
//                    }
//                });
//                //第三种
//                mTextView.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mTextView.setText("我在其它线程中来看你");
//                    }
//                },2000);
//                //第四种
//                new Handler(Looper.getMainLooper()).post(new Runnable() {
//                    @Override
//                    public void run() {
//                        mTextView.setText("我在其它线程中来看你");
//                    }
//                });
//
//
//            }
//        }).start();
//
//        new TestTask().execute(2,3,4);
//    }
//    class TestTask extends AsyncTask<Integer,Integer,String>{
//
//        @Override
//        protected void onPreExecute() {
//            //主线程，在 doInBackground之前
//            super.onPreExecute();
//            mTextView.setText("加载中");
//        }
//
//        @Override
//        protected String doInBackground(Integer... params) {
//            String result = String.valueOf(params[1] * 2 + 2);
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            //主线程，在doInBackgroud这后
//            mTextView.setText("加载完成,结果是" + result);
//        }

        //apk url:https://download.sj.qq.com/upload/connAssitantDownload/upload/MobileAssistant_1.apk
        mDownloadButton = findViewById(R.id.download_button);
        mProgressBar = findViewById(R.id.progressBar2);
        mDownloadButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.download_button:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        download(APK_URL);
                    }
                }).start();

                break;
        }

    }

    private void download(String apkUrl) {
        try {
            //先转换url的网址
            URL url = new URL(apkUrl);
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();//打开文件流
            int contentLenght = urlConnection.getContentLength();//要下载的文件大小
            Log.i(TAG,"下载文件大小是:" + contentLenght);
            //下载文件夹的路径
            String downloadFoldersName = Environment.getExternalStorageDirectory() +"/" + GEEK_BAND + "/";
            //实例化文件夹
            File file = new File(downloadFoldersName);
            //判断文件夹存不存在，不存在就创建
            if (!file.exists()){
                file.mkdir();
            }
            //下载文件的路径
            String fileName = downloadFoldersName + "test.apk";
            File apkFile = new File(fileName);
            //判断如果文件存在的话，就删除
            if (apkFile.exists()){
                apkFile.delete();
            }

            int downloadSize = 0;
            byte[] bytes = new byte[1024];
            int length = 0;
            //实例化输出流
            OutputStream outputStream = new FileOutputStream(fileName);
            while ((length = inputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,length);
                downloadSize += length;
                int progress = downloadSize * 100 / contentLenght;
                //更新ui
                Message message = mHandler.obtainMessage();
                message.obj = progress;
                message.what = 0;
                mHandler.sendMessage(message);
                Log.i(TAG,"下载进度是" + progress);
            }
            Log.i(TAG,"下载成功");
            inputStream.close();
            outputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG,"下载失败");
        }
    }
    public static class DownloadHandler extends Handler{
        //创建一个弱引用
        public final WeakReference<ThreadActivity> mActivity;

        public DownloadHandler(ThreadActivity activity) {
            mActivity = new WeakReference<ThreadActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ThreadActivity activity = mActivity.get();
            //处理消息
            switch (msg.what){
                case 0:
                    int progress = (int)msg.obj;
                    activity.getProgressBar().setProgress(progress);
                    activity.getTextView().setText("下载进度:" + progress);
                    if (progress == 100){
                        Toast.makeText(activity,"下载成功",Toast.LENGTH_SHORT).show();
                    }



                    break;
            }

        }
    }


}
