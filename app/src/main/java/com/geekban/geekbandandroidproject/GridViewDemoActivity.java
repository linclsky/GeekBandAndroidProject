package com.geekban.geekbandandroidproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GridViewDemoActivity extends AppCompatActivity {

    private GridView mPhoneBookGridView;
    private List<UserInfo> mUserInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_demo);
        mUserInfos = new ArrayList<>();
        mUserInfos.add(new UserInfo("小明",12));
        mUserInfos.add(new UserInfo("小白",13));
        mUserInfos.add(new UserInfo("小红",14));
        mUserInfos.add(new UserInfo("小绿",15));

        mPhoneBookGridView = findViewById(R.id.grid_view);
        final phoneBookAdapter phoneBookAdapter = new phoneBookAdapter(GridViewDemoActivity.this, mUserInfos);
        mPhoneBookGridView.setAdapter(phoneBookAdapter);
        phoneBookAdapter.notifyDataSetChanged();
        mPhoneBookGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                {
                    //新建另外一批数据
                    //替换掉第一批数据
                    //刷新listview，让它更新自己的视图
                    mUserInfos.clear();
                    mUserInfos.add(new UserInfo("新1",12));
                    mUserInfos.add(new UserInfo("新2",13));
                    mUserInfos.add(new UserInfo("新3",14));
                    mUserInfos.add(new UserInfo("新4",15));
                    phoneBookAdapter.refreshData(mUserInfos);
                }
                Toast.makeText(GridViewDemoActivity.this,"我点击了：" + mUserInfos.get(position).getUsername(),Toast.LENGTH_SHORT).show();
            }
        });
        mPhoneBookGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridViewDemoActivity.this,"我长按了：" + mUserInfos.get(position).getUsername(),Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
