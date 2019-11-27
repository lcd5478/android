package com.example.myapplication.SQL;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.service.media.MediaBrowserService;


import java.util.List;
import java.util.Map;

public class AccountDao {
    private  DatabaseHelper helper;
    private SQLiteDatabase SQL_db;
    public  AccountDao(Context context){
         helper =new DatabaseHelper(context);
         SQL_db = helper.getReadableDatabase();
    }

    /**
     * 查询密码
     * @param string 用户名
     * @return 密码
     */
    public String getIncomepwd(String string){
        String sstring=null;
        Cursor cursor =SQL_db.query("accountincometype",new String[]{"user","password"},"user=?", new String[]{string},null,null,null);
        if (cursor.moveToNext()){
          sstring =  cursor.getString(cursor.getColumnIndex("password"));
        }
        else {
            return null;
        }

        return sstring;
    }

    /**
     * 查询id
     * @param string 用户名
     * @return id
     */
    public int getExtractid(String string){
        int id=-1;
        Cursor cursor =SQL_db.query("accountincometype",new String[]{"user","id"},"user=?", new String[]{string},null,null,null);
        if (cursor.moveToNext()){
            id =cursor.getInt(cursor.getColumnIndex("id"));
        }
        return id ;
    }

    /**
     * 查询用户名
     * @param id id
     * @return 用户名
     */
    public String getExtractuser(int id){

        Cursor cursor =SQL_db.query("accountincometype",new String[]{"user","id"},"id=?", new String[]{String.valueOf(id)},null ,null,null);
        if (cursor.moveToNext()){
            String S =cursor.getString(cursor.getColumnIndex("user"));
            return S;
        }
        return null;
    }

    /**
     * 修改记住密码的id
     * @param id
     * @param flag
     */
    public void setInsertData(int id ,int flag,String string){
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("flag",flag);
        values.put("pwd",string);
        SQL_db.update("jizhumima",values,"user=?",new String[]{"remember_the_password"});
    }

    /**
     * 查询记住密码的id
     * @param user
     * @return
     */
    public int  getInsertid(String user){
        int id=-1;
        Cursor cursor =SQL_db.query("jizhumima",new String[]{"user","id"},"user=?", new String[]{user},null,null,null);
        if (cursor.moveToNext()){
            id =cursor.getInt(cursor.getColumnIndex("id"));
        }
        return id ;
    }

    /**
     * 查询记住密码的标志位
     * @param user
     * @return
     */
    public int  getInsertflag(String user){
        int flag=-1;
        Cursor cursor =SQL_db.query("jizhumima",new String[]{"user","id","flag"},"user=?", new String[]{user},null,null,null);
        if (cursor.moveToNext()){
            flag =cursor.getInt(cursor.getColumnIndex("flag"));
        }
        return flag ;
    }

    public String  getInsertpwd(String user){
        String PWD=null;
        Cursor cursor =SQL_db.query("jizhumima",new String[]{"user","id","flag","pwd"},"user=?", new String[]{user},null,null,null);
        if (cursor.moveToNext()){
             PWD =cursor.getString(cursor.getColumnIndex("pwd"));
        }
        return PWD ;
    }


    public boolean setadduser(String user,String pwd,String nick){
        //创建存放数据的对象
        ContentValues values = new ContentValues();
        values.put("user",user);

        String string = jiami.jiami_liangchi(pwd);
        values.put("password",string);
        values.put("nickname",nick);
        //数据库执行插入命令
        SQL_db.insert("accountincometype",null,values);
        return  true;
    }

}
