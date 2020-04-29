package com.geekban.geekbandandroidproject.xml;

import android.text.TextUtils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/3/7.
 */

public class SAXParseHandler extends DefaultHandler{
    public static final String ITEM = "item";
    List<WebURL>mWebURLS;
    WebURL mWebURL;
    int type = 1;
    boolean mIsItem = false;//默认是false

    public SAXParseHandler() {
        super();
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        mWebURLS = new ArrayList<>();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        mWebURL = new WebURL();
        if (TextUtils.equals(localName, ITEM)){
            for (int i = 0; i <attributes.getLength() ; i++) {
                if (TextUtils.equals(attributes.getLocalName(i),"id")){
                    mWebURL.setID(Integer.valueOf(attributes.getValue(i)));
                }

            }
                mIsItem = true;

        }
        mIsItem =false;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (TextUtils.equals(localName, ITEM)){
            mWebURLS.add(mWebURL);

        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String content = String.valueOf(ch, start, length);
        if (mIsItem){
            mWebURL.setContent(content);
            mIsItem =false;
        }
    }

    public  List<WebURL> getXMLList() {
        //获取到读取的数据
        return mWebURLS;

    }
}
