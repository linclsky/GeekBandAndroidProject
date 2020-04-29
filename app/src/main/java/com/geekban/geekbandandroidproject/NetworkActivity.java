package com.geekban.geekbandandroidproject;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mEditText;
    private Button mButton;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        findView();
        setListeners();


    }



    private void findView() {
        mEditText = findViewById(R.id.net_work_edit_text);
        mButton = findViewById(R.id.net_work_button);
        mTextView = findViewById(R.id.new_work_text_view);
    }

    private void setListeners() {
        mButton.setOnClickListener(this);
    }

    private String getEditTextUrl() {

        return mEditText != null ? mEditText.getText().toString() : "";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.net_work_button:
                String url = getEditTextUrl();
                //请求网络数据
                // 1 .申请网络权限
                //String data = requestData(url);
                new RequestNetworkDataTask().execute(url);

                break;
        }

    }

    private String requestData(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(30000);//设置超时时间
            connection.setRequestMethod("GET");//请求方法
            connection.connect();//开始请求
            int responseCode = connection.getResponseCode();//响应代码
            String responseMessage = connection.getResponseMessage();//响应消息
            String result = null;
            if (responseCode == HttpURLConnection.HTTP_OK){
                InputStream inputStream  = connection.getInputStream();//请求的流
                Reader reader = new InputStreamReader(inputStream,"UTF-8");
                char[] buffer = new char[2048];
                reader.read(buffer);
                result = new String(buffer);

            }



            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            //线程里不要做ui的事
           // Toast.makeText(NetworkActivity.this,"非法的URL",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            //Toast.makeText(NetworkActivity.this,"读写错误 ",Toast.LENGTH_SHORT).show();
        }
        return null;
    }
    //异步任务处理
    class  RequestNetworkDataTask extends AsyncTask<String,Integer,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //主线程
            //UI loading
        }

        @Override
        protected String doInBackground(String[] params) {
            String result = requestData(params[0]);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //执行完之后的主线程中
            mTextView.setText(result);
        }
    }
}
