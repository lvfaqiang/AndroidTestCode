package com.lvfq.code.database.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lvfq.code.R;
import com.lvfq.library.utils.LvV;

/**
 * SqliteActivity
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/9/6 上午10:07
 * @desc :
 */

public class SqliteActivity extends AppCompatActivity implements View.OnClickListener {

    private SqlDataBaseHelper mHelper;
    private Button tv_create_Book;
    private Button tv_add_Book;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        // 初始化 Helper
        // context , db name , cursor , version
        mHelper = new SqlDataBaseHelper(this, "SqlTest.db", null, 2);
        tv_create_Book = LvV.find(this, R.id.tv_create_Book);
        tv_add_Book = LvV.find(this, R.id.tv_add_Book);

        tv_create_Book.setOnClickListener(this);
        tv_add_Book.setOnClickListener(this);
        LvV.find(this, R.id.tv_update_Book).setOnClickListener(this);
        LvV.find(this, R.id.tv_delete_Book).setOnClickListener(this);
        LvV.find(this, R.id.tv_select_Book).setOnClickListener(this);
    }

    /**
     * 创建表
     */
    private void createBook() {
        mHelper.getWritableDatabase();
    }

    /**
     * 添加图书
     */
    private void addBook() {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", "第一行代码");
        values.put("author", "郭霖");
        values.put("price", 79);
        values.put("pages", 569);
        db.insert("Book", null, values);    // 添加第一条

        values.clear();

        values.put("name", "Android疯狂讲义");
        values.put("author", "忘记了");
        values.put("price", 60);
        values.put("pages", 500);
        db.insert("Book", null, values);    // 添加第一条

    }

    /**
     * 更新
     */
    private void updateBook() {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put("name", "第一行代码");
        values.put("author", "郭");
//        values.put("price", 79);
//        values.put("pages", 569);

        db.update("Book", values, "pages > ? ", new String[]{"500"});
    }


    /**
     * 删除数据
     */
    private void deleteBook() {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete("Book", "pages > ?", new String[]{"560"});
    }

    /**
     * 查询数据
     */
    private void selectBook() {
        SQLiteDatabase db = mHelper.getWritableDatabase();

        // 查询方法一：

        // 参数： 表名，查询的列名 ，约束条件，占位符提供的值， 需要groupBy的列 ， 对groupBy 后的结果进一步约束 ， 查询结果的排序方式。
        Cursor cursor = db.query("Book", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                Log.i("lfq", "book Name : " + name);
            } while (cursor.moveToNext());
        }
        cursor.close();

        //查询方法二：
//        db.rawQuery("select * from Book", null);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_create_Book:
                createBook();
                break;
            case R.id.tv_add_Book:
                addBook();
                break;
            case R.id.tv_update_Book:
                updateBook();
                break;
            case R.id.tv_delete_Book:
                deleteBook();
                break;
            case R.id.tv_select_Book:
                selectBook();
                break;
        }
    }
}
