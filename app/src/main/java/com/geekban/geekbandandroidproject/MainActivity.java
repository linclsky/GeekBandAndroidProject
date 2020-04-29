package com.geekban.geekbandandroidproject;

import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.geekban.geekbandandroidproject.xml.SAXParseHandler;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    public static final int RESULT_CODE = 1234;
    public static final String TAG = MainActivity.class.getSimpleName();
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText editText = findViewById(R.id.editText3);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i(TAG,"s:" + s.toString() + ",start" + start + ",count" + count + ",after" + after);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(TAG,"s:" + s.toString() + ",start" + start + ",count" + count + ",before" + before);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i(TAG,"s:" + s.toString() );
                if (s.toString().length() > 5){
                    Toast.makeText(MainActivity.this,"不能超过5个数字",Toast.LENGTH_LONG).show();
                }
            }
        });
        findViewById(R.id.seekBar).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(90);
        initView();
        handleIntentData();
//        try {
//            testSAXParse();
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (XmlPullParserException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }
    //parse file by SAX
    private void testSAXParse() throws ParserConfigurationException, SAXException, IOException, XmlPullParserException, JSONException {
        //实例化一个无参构造方法
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();
        SAXParseHandler saxParseHandler = new SAXParseHandler();
        xmlReader.setContentHandler(saxParseHandler);
        InputStream inputStream = getResources().openRawResource(R.raw.test);
        InputSource inputSource = new InputSource(inputStream);
        xmlReader.parse(inputSource);
        saxParseHandler.getXMLList();

        //简单写法
        XMLReader xmlReaderTest = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
        xmlReaderTest.setContentHandler(new SAXParseHandler()); //处理器处理xml文件
        xmlReaderTest.parse(new InputSource(getResources().openRawResource(R.raw.test)));//读取xml文件

        //pull xml的文件夹里才能读

        XmlResourceParser xmlResourceParser = getResources().getXml(R.xml.test);
        while (xmlResourceParser.getEventType() != XmlResourceParser.END_DOCUMENT){
            if(xmlResourceParser.getEventType() == XmlResourceParser.START_TAG){
                String tagName = xmlResourceParser.getName();
                if (TextUtils.equals(tagName,"item")){
                    String id = xmlResourceParser.getAttributeValue(null,"id");

                }

            }

        }
        //JSON
        InputStream is = getResources().openRawResource(R.raw.json);
        String jsonString = getStringByInputStream(is);
        JSONObject jsonObject = new JSONObject(jsonString);
        String title = jsonObject.getString("title");

        JSONObject userJSONObjet = jsonObject.getJSONObject("user");
        userJSONObjet.getLong("id");

        JSONArray jsonArray = jsonObject.getJSONArray("images");
        jsonArray.get(0);

        Gson gson = new Gson();
        UserData userData = gson.fromJson(jsonString,UserData.class);


    }

    private String getStringByInputStream(InputStream is) {
        return null;
    }


    private void initView() {
        mButton = findViewById(R.id.button_first);
        mButton.setOnClickListener(this);
    }

    private void handleIntentData() {
        Intent intent = getIntent();
        if (intent != null){
            String title = intent.getStringExtra(SplashActivity.TITLE);

            UserInfo userInfo = (UserInfo) intent.getSerializableExtra(SplashActivity.USER_INFO);
            setTitle("名字是:" + userInfo.getUsername());
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_first:
//                Intent intent = new Intent();
//                intent.putExtra(SplashActivity.TITLE,"我是主页，我送礼回来了");
//                setResult(RESULT_CODE,intent);
//                finish();

                startActivity(new Intent(MainActivity.this, RelativelayoutActivity.class));
                break;
        }

    }
}
