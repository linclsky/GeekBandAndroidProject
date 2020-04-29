package com.geekban.geekbandandroidproject;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/2/20.
 */

public class UserInfo implements Serializable{
    private String mUsername;
    private int mAge;
    private String mAvatarUrl;
    private float mWeight;

    public String getUsername() {
        return mUsername;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }

    public float getWeight() {
        return mWeight;
    }

    public void setWeight(float weight) {
        mWeight = weight;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public UserInfo(String username, int age) {
        mUsername = username;
        mAge = age;

    }
}
