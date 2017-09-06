package com.lvfq.code.database.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * SqlDataBaseHelper
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/9/6 上午10:03
 * @desc :
 */

public class SqlDataBaseHelper extends SQLiteOpenHelper {

    /**
     * 建表语句
     */
    private static final String CREATE_BOOK = "create table Book (" +
            "id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text)";

    /**
     * 创建 Type 表
     */
    private static final String CREATE_TYPE = "create table Type( id integer primary key autoincrement , type_name text , type_code)";

    private Context mContext;

    public SqlDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 新增表的时候，要在 onUpgrade 方法添加 drop 语句
        db.execSQL(CREATE_BOOK);    // 执行建表语句
        db.execSQL(CREATE_TYPE);
        Toast.makeText(mContext, "create Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Type");
        onCreate(db);
    }
}
