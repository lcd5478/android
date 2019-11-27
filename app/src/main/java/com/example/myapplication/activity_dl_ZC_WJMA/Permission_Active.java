package com.example.myapplication.activity_dl_ZC_WJMA;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import android.util.Log;

import androidx.core.app.ActivityCompat;


public class Permission_Active {

    //申请读写权限

    // Log.d("Check_Permission", String.valueOf(Permission_Active.Check_Permission (this ,  permissions )));
    //        Permission_Active.Get_Permission(MainActivity.this);


    //一次申请不要超过3个
    public static String[]  permissions = {
               Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
               Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static boolean Permissions_State(Activity activity)
    {
        boolean permission_state = Check_Permission (activity ,  permissions );
        Log.d("Check_Permission", String.valueOf(permission_state));
        if(permission_state == false) {
            Get_Permission(activity);
        }
        if(Check_Permission (activity ,  permissions )) {
            //再次检查权限
            return true;
        }
        return false;
    }


    public static boolean Check_Permission (Activity activity , String[]  permissions )
    {
        for (String permission : permissions)
        {
            if (ActivityCompat.checkSelfPermission(activity,permission) != PackageManager.PERMISSION_GRANTED)
            {
                Log.d("Check_Permission", permission+"未获取权限！");//未获取权限
                return false;
            }
        }
        return true;
    }

    public static void Get_Permission(Activity activity) {
         /***检查是否有相应的权限 */
        if(Check_Permission(activity,permissions) ==false)
        {     //*** 请求权限  一次请求多个权限, 如果其他有权限是已经授予的将会自动忽略掉
            ActivityCompat.requestPermissions(activity,permissions , 1000);
         }
    }

}
