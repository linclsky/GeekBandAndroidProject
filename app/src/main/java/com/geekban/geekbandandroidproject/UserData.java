package com.geekban.geekbandandroidproject;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2020/3/8.
 */

public class UserData {

    @SerializedName("title")
    private String mTitle;

    @SerializedName("content")
    private String mContent;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public User getUsert() {
        return mUsert;
    }

    public void setUsert(User usert) {
        mUsert = usert;
    }

    public List<String> getImages() {
        return mImages;
    }

    public void setImages(List<String> images) {
        mImages = images;
    }

    @SerializedName("user")

    private User mUsert;

    @SerializedName("images")
    private List<String> mImages;

    public class User{
        @SerializedName("id")
        private long mID;

        @SerializedName("name")
        private String mName;

        public long getID() {
            return mID;
        }

        public void setID(long ID) {
            mID = ID;
        }

        public String getName() {
            return mName;
        }

        public void setName(String name) {
            mName = name;
        }

        public String getAvatar() {
            return mAvatar;
        }

        public void setAvatar(String avatar) {
            mAvatar = avatar;
        }

        @SerializedName("avatar")
        private String mAvatar;
    }

}
