package com.geekban.geekbandandroidproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListViewDemoActivity extends AppCompatActivity implements View.OnClickListener {

    public  int mDataCounts = 10;
    public static final String LIST_VIEW_DATA_COUNTS = "list_view_data_counts";
    public static final String PREFERENCE_NAME = "preference_name";
    private ListView mPhoneBookListView;
    private List<UserInfo> mUserInfos;
    private phoneBookAdapter mPhoneBookAdapter;
    private EditText mConuntEditText;
    private Button mConfirmButton;

    private void findViews() {
        mPhoneBookListView = findViewById(R.id.list_view);
        mConuntEditText = findViewById(R.id.data_counts_edit_text);
        mConfirmButton = findViewById(R.id.confirm_button);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_demo);
        findViews();
        mPhoneBookAdapter = setData();
       // mPhoneBookAdapter.notifyDataSetChanged();//更新数据
        setListeners(mPhoneBookAdapter);
    }

    private void setListeners(final phoneBookAdapter phoneBookAdapter) {
        mPhoneBookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                {
//                    //按住0行
//                    //新建另外一批数据
//                    //替换掉第一批数据
//                    //刷新listview，让它更新自己的视图
                    mUserInfos.clear();
                    mUserInfos.add(new UserInfo("新1",12));
                    mUserInfos.add(new UserInfo("新2",13));
                    mUserInfos.add(new UserInfo("新3",14));
                    mUserInfos.add(new UserInfo("新4",15));
                    phoneBookAdapter.refreshData(mUserInfos);
                    phoneBookAdapter.notifyDataSetChanged();
               }
                Toast.makeText(ListViewDemoActivity.this,"我点击了：" + mUserInfos.get(position).getUsername(),Toast.LENGTH_SHORT).show();
            }
        });
        mPhoneBookListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewDemoActivity.this,"我长按了：" + mUserInfos.get(position).getUsername(),Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        mConfirmButton.setOnClickListener(this);
    }


    @NonNull
    private phoneBookAdapter setData() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        mDataCounts = sharedPreferences.getInt(LIST_VIEW_DATA_COUNTS,5);
        mConuntEditText.setText(String.valueOf(mDataCounts));
        mUserInfos = new ArrayList<>();
        for (int i = 0; i < mDataCounts; i++){
            mUserInfos.add(new UserInfo("小明",12));

        }


        final phoneBookAdapter phoneBookAdapter = new phoneBookAdapter(ListViewDemoActivity.this, mUserInfos);
        mPhoneBookListView.setAdapter(phoneBookAdapter);
        return phoneBookAdapter;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.confirm_button:
                String countString = mConuntEditText.getText().toString();
                int mDataCounts = Integer.valueOf(countString);
                refreshListView(mDataCounts);
                saveData2Preference(mDataCounts);

                break;

        }
;
    }

    private void saveData2Preference(int dataCounts) {
        //系统会自动帮我们创建一个xml文件,名字是preference_name
        SharedPreferences sharedPreferences = ListViewDemoActivity.this.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(LIST_VIEW_DATA_COUNTS,dataCounts);
        //提交，地址是data/data/包名/shared_prefs
        editor.commit();
        //和网络相关，和IO操作相关的，都要用异步
        //后台写数据，另开线程
        //editor.apply();
        //删除
//        editor.remove(LIST_VIEW_DATA_COUNTS);
//        editor.commit();
        sharedPreferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

            }
        });
    }

    private void refreshListView(int mDataCounts) {
        mUserInfos.clear();
        for (int i = 0; i < mDataCounts; i++){
            mUserInfos.add(new UserInfo("小花",12));
            mPhoneBookAdapter.refreshData(mUserInfos);
            mPhoneBookAdapter.notifyDataSetChanged();
        }
    }
}
