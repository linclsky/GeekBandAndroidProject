package com.geekban.geekbandandroidproject.xml;

/**
 * Created by Administrator on 2020/3/7.
 */

public class WebURL {
    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    private int mID;
    private String mUrl;
    private String mContent;
}
