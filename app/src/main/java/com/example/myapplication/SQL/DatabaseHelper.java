package com.example.myapplication.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private  static final  String Database_name="account138.db"; //数据库名
    private  static final  int Database_version =1; //版本号
    public DatabaseHelper(@Nullable Context context) {

        super(context, Database_name, null, Database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //建表
        //登录数据表
        String sql ="create table accountincometype(id integer primary key autoincrement,user text,password text,nickname,text)";
        db.execSQL(sql);
         sql ="create table jizhumima(user text,id integer,flag integer,pwd text)";
        db.execSQL(sql);
        //初始化数据
        initData(db);
    }

    private void initData(SQLiteDatabase db) {
        //写入管理员账户
        //
        String user ="root";//账户
        String pass ="1234";
        jiami jiami =new jiami();
        String password =jiami.jiami_liangchi(pass);
        String nick ="管理员";
        String sq1 =String.format("insert into accountincometype(user,password,nickname) values('"+user+"','"+password+"','"+nick+"')");
        db.execSQL(sq1);
        sq1 =String.format("insert into jizhumima(user,id,flag,pwd) values('remember_the_password',0,0,'')");
        db.execSQL(sq1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
