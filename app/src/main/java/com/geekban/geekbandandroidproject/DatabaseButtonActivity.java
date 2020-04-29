package com.geekban.geekbandandroidproject;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.geekban.geekbandandroidproject.database.DatabaseHelper;
import com.geekban.geekbandandroidproject.provider.URIList;

public class DatabaseButtonActivity extends AppCompatActivity {

    private SQLiteDatabase mSqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_button);
        final DatabaseHelper databaseHelper = new DatabaseHelper(this);
        mSqLiteDatabase = databaseHelper.getReadableDatabase();



        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add IO操作，要后台操作，开线程
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper.USERNAME,"极客班");
                contentValues.put(DatabaseHelper.AGE,"123456");
                long code = mSqLiteDatabase.insert(DatabaseHelper.USER_TABLE_NAME,null,contentValues);
                if (code != -1){
                    Toast.makeText(DatabaseButtonActivity.this,"插入成功",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //query
        findViewById(R.id.query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //游标 要查找的数据集合
                Cursor cursor = mSqLiteDatabase.query(DatabaseHelper.USER_TABLE_NAME,null,null,null,null,null,null);
                if (cursor.moveToFirst()){
                    int count = cursor.getCount();
                    for (int i = 0; i < count; i++) {
                        String usreName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USERNAME));
                        String age = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.AGE));
                        Log.d(DatabaseButtonActivity.class.getSimpleName(),i + ":" + usreName +"|" + age );

                    }
                }

            }
        });
        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete
                String whereClauseString = "username=?";
                String[] whereArgsString = {"极客班"};
                mSqLiteDatabase.delete(DatabaseHelper.USER_TABLE_NAME,whereClauseString,whereArgsString);
            }
        });
        findViewById(R.id.updata).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //updata
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper.AGE,"100岁");
                String whereClauseString = "username=?";
                String[] whereArgsString = {"极客班"};
                mSqLiteDatabase.update(DatabaseHelper.USER_TABLE_NAME,contentValues,whereClauseString,whereArgsString);


            }
        });

        //事务操作，事务做完了，才会提交事务进行操作，优化数据库写入
        // 开始事务，此时，数据库会被锁定，其它线程用不了数据库
        mSqLiteDatabase.beginTransaction();

        try {
            //做操作
            for (int i = 0; i <1000 ; i++) {
                //插入数据
                //原生sql语句
                mSqLiteDatabase.execSQL("insert into user(username ,age) values ('刘小明','6岁')");
            }
            //一定要设置成功，
            mSqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭提交事务
            mSqLiteDatabase.endTransaction();
        }
        //查询通讯录
        ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse("content://com.android.contacts/contacts");

        //Uri uri = Uri.parse("content://com.GeekBandAndroidProject/table_name/10/user");

        Cursor cursor = contentResolver.query(uri,null,null,null,null);
        cursor = contentResolver.query(Uri.parse(URIList.USER_URI),null,null,null,null);


    }
}
