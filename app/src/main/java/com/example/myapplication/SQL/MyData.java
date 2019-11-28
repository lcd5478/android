package com.example.myapplication.SQL;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class MyData {

    private Context context;
    public MyData(Context context){
        this.context=context;
    }

    /**
     * 写入数据
     * @文件名 user 文件名
     * @二维字符串数组 strings 二维字符串数组
     */
    public void save(String user,String[][] strings){
        String meun ="Blue"+user;
        SharedPreferences shp=context.getSharedPreferences(meun,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =shp.edit();
        for (int a=0;a<12;a++){
            for(int b=0;b<3;b++){
                String S =String.format("s%d%d",a,b);
                editor.putString(S,strings[a][b]);
            }
        }
       editor.apply();

    }

    /**
     * 读取数据
     * @文件名 user
     * @二维字符串数组
     */
    public String[][] load(String user){
        String[][] strings= new String[12][3];

        String meun ="Blue"+user;
        try {
        SharedPreferences shp =context.getSharedPreferences(meun,Context.MODE_PRIVATE);
        for (int a=0;a<12;a++){
            for (int b=0;b<3;b++){
                String S =String.format("s%d%d",a,b);
                strings[a][b]=shp.getString(S, String.valueOf(0));
            }
        }
        } catch (Exception e) {
            Log.e("shared", "load: "+e);
            e.printStackTrace();
        }

        for (int m=0;m<12;m++){
            if (strings[m][2] == "0");
            else
                return strings;
        }
            for (int t = 0; t < 12; t++)
                strings[t][2] = " ";

        return strings;
    }






    /**
     * 写入数据
     * @文件名 user 文件名
     * @二维字符串数组 strings 二维字符串数组
     */
    public void savekey(String user,String[][] strings){
        String meun ="Bluekey"+user;
        SharedPreferences shp=context.getSharedPreferences(meun,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =shp.edit();
        for (int a=0;a<6;a++){
            for(int b=0;b<2;b++){
                String S = String.format("s%d%d",a,b);
                editor.putString(S,strings[a][b]);
            }
        }
        editor.apply();

    }
    /**
     * 写入数据
     * @文件名 user 文件名
     * @二维字符串数组 strings 一维字符串数组
     */
    public void savekey_ss(String user,String[] strings){
        String meun ="Bluekey"+user;
        SharedPreferences shp=context.getSharedPreferences(meun,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =shp.edit();
        for (int a=0;a<3;a++){
            String S = String.format("ss%d",a);
            editor.putString(S,strings[a]);
        }
        editor.apply();

    }
    /**
     * 写入数据
     * @文件名 user 文件名
     * @字符串 strings 字符串
     * @S 字符串名称
     */
    public void savekey_s(String user,String strings,String S){
        String meun ="Bluekey"+user;
        SharedPreferences shp=context.getSharedPreferences(meun,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =shp.edit();
        editor.putString(S,strings);
        editor.apply();

    }

    /**
     *
     * @param user 读取单个字符串
     * @param S 字符串名称
     * @return 字符串
     */
    public String loadkey_s(String user,String S){
        String strings= new String();

        String meun ="Bluekey"+user;
        try {

            SharedPreferences shp =context.getSharedPreferences(meun,Context.MODE_PRIVATE);
            strings=shp.getString(S, String.valueOf(0));
        } catch (Exception e) {
            Log.e("shared", "load: "+e);
            e.printStackTrace();
        }
        return strings;
    }
    /**
     * 读取数据
     * @文件名 user
     * @二维字符串数组
     */
    public String[][] loadkey(String user){
        String[][] strings= new String[6][2];

        String meun ="Bluekey"+user;
        try {
            SharedPreferences shp =context.getSharedPreferences(meun,Context.MODE_PRIVATE);
            for (int a=0;a<6;a++){
                for (int b=0;b<2;b++){
                    String S =String.format("s%d%d",a,b);
                    strings[a][b]=shp.getString(S, String.valueOf(0));
                }
            }
        } catch (Exception e) {
            Log.e("shared", "load: "+e);
            e.printStackTrace();
        }
        return strings;
    }
    public String[] loadkey_ss(String user){
        String[] strings= new String[6];

        String meun ="Bluekey"+user;
        try {
            SharedPreferences shp =context.getSharedPreferences(meun,Context.MODE_PRIVATE);
            for (int a=0;a<3;a++){

                    String S =String.format("ss%d",a);
                    strings[a]=shp.getString(S, String.valueOf(0));

            }
        } catch (Exception e) {
            Log.e("shared", "load: "+e);
            e.printStackTrace();
        }
        return strings;
    }




}
