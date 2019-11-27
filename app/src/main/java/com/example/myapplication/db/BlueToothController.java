package com.example.myapplication.db;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

/**
    *蓝牙适配器
   * 2019/11/8./10
*/
public class BlueToothController {
    private BluetoothAdapter mAdapter;
    public BlueToothController()
    {
        mAdapter = BluetoothAdapter.getDefaultAdapter();
    }



    /**
     * 是否支持蓝牙
     *@return true 支持，false 不支持
     */

    public boolean isSupportBlueTooth()
    {
        if(mAdapter != null)
        {
            return  true;
        }
        else
        {
            return  false;
        }

    }
        /**
        * 判断当前蓝牙状态
        * @return true 打开，false 关闭
        *
        *
        * */

    public boolean getBlueTootStatus()
    {
        assert(mAdapter != null);
        return mAdapter.isEnabled();
    }

    /**
     * 打开蓝牙
     * @param activity
     * @param requestCode
     */
    public  void  turnOnBlueTooth(Activity activity,int requestCode)
    {
        Intent intent =new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
       activity.startActivityForResult(intent,requestCode);
       // mAdapter.enable();//调用系统去打开，不推荐
    }

    /**
     * 关闭蓝牙
     */
    public void turnoffBlueTooth() {
        mAdapter.disable();
    }

    /**
     * 打开蓝牙可见性 5mil
     * @param context
     */
    public void enableVisbly(Context context){
        Intent discoverableIntent =new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,300);
        context.startActivity(discoverableIntent);

    }

    /**
     * 查找设备
     */
    public  void  findDevice(){
        assert (mAdapter !=null);
        mAdapter.startDiscovery();
    }

    /**
     * 获取绑定设备
     * @return
     */
    public List<BluetoothDevice> getBondedDeviceList(){
        return  new ArrayList<>(mAdapter.getBondedDevices());
    }

}
