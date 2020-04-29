package com.geekban.geekbandandroidproject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TestFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragment);
        FragmentManager fragmentManager = getFragmentManager();//声明一个管理器
        //声明一个事务开始
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //声明一个视图
        TestFragment testFragment = new TestFragment();
        //将其添加到ViewGroup
        fragmentTransaction.add(R.id.frament_view,testFragment).commit();
        //fragmentTransaction.remove(testFragment).commit();

        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_text);
    }
}
