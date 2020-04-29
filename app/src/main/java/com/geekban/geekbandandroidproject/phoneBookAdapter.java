package com.geekban.geekbandandroidproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/2/23.
 */

public class phoneBookAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    //private String[] mNames = {"小明","小花"};
    private List<UserInfo> mUserInfos = new ArrayList<>();


    public phoneBookAdapter(Context context, List<UserInfo> userInfos) {
        mContext = context;
        mUserInfos = userInfos;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        //有多少条数据
        return  mUserInfos.size();

    }

    @Override
    public Object getItem(int position) {
        //返回某一条数据对象
        return  mUserInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        //某一条数据项目的id
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //返回一个视图和数据之间进行绑定
        // position位置，哪一条视图
        // convertView这个视图
        //parent 属于谁的
        //每一行数据显示在界面，用户能够看到时不需要刷新，直接缓存

        ViewHolder viewHolder;
        if (convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.item_phone_book_friend,null);
            viewHolder = new ViewHolder();
            //获取控件
            viewHolder.nameTextView = convertView.findViewById(R.id.name_text_view);
            viewHolder.ageTextView = convertView.findViewById(R.id.age_text_view);
            viewHolder.avatarImageView = convertView.findViewById(R.id.avatar_image_view);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();

        }



        //和数据进行绑定
        viewHolder.nameTextView.setText(mUserInfos.get(position).getUsername());
        viewHolder.ageTextView.setText(String.valueOf(mUserInfos.get(position).getAge()));
        viewHolder.avatarImageView.setImageResource(R.drawable.ic_launcher);


        return convertView;
    }

    class ViewHolder{
        TextView nameTextView;
        TextView ageTextView ;
        ImageView avatarImageView;
    }
    //刷新数据
 public void refreshData(List<UserInfo> userInfos){
        mUserInfos = userInfos;
        notifyDataSetChanged();
    }
}
