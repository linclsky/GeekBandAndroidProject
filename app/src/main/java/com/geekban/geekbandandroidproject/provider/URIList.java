package com.geekban.geekbandandroidproject.provider;

import com.geekban.geekbandandroidproject.database.DatabaseHelper;

/**
 * Created by Administrator on 2020/3/5.
 */

public class URIList {

    public static final String CONTENT = "content://";
    public static final String AUTHORITY= "com.geekban.geekbandandroidproject";
    //content://com.geekban.geekbandandroidproject/user 1
    //content://com.geekban.geekbandandroidproject/book 2
    public static final String USER_URI = CONTENT + AUTHORITY + "/" + DatabaseHelper.USER_TABLE_NAME;
    public static final String BOOK_URI = CONTENT + AUTHORITY + "/" + DatabaseHelper.BOOK_TABLE_NAME;

}
