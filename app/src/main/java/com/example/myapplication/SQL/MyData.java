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
    public String[][] loadkey(String user){
        String[][] strings= new String[12][3];

        String meun ="Bluekey"+user;
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
}
