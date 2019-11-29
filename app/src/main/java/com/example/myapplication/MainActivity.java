package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.db.BlueToothController;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final boolean DEBUG = false;

    public static final int REC_DATA = 2;
    public static final int CONNECTED_DEVICE_NAME = 4;
    public static final int BT_TOAST = 5;
    public static final int MAIN_TOAST = 6;

    // 标志字符串常量
    public static final String DEVICE_NAME = "device name";
    public static final String TOAST = "toast";

    // 意图请求码
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;


    private BlueToothController mController = new BlueToothController();
    private Toast mtoast;
    private BroadcastReceiver receiver =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int state =intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,-1);
            switch (state){
                case  BluetoothAdapter.STATE_OFF:
                    showToast("STATE_OFF");
                    break;
                case  BluetoothAdapter.STATE_ON:
                    showToast("STATE_ON");
                    break;
                case  BluetoothAdapter.STATE_TURNING_OFF:
                    showToast("STATE_TURNING_OFF");
                    break;
                case  BluetoothAdapter.STATE_TURNING_ON:
                    showToast("STATE_TURNING_ON");
                    break;
                default:
                    showToast("Unkown STATE");
                    break;
            }
        }
    };
    TextView textView1,textView2;
    private String S;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter =new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);


        //Log.e(TAG, ti[0]);
        //Log.e(TAG, ti_nen[0]);
        //Log.e(TAG, ti_nen[1]);
        //Log.e(TAG, ti_nen[2]);
        //textView2.setText("text"+ti[0]+"TIM"+ti[1]+"KE"+ti[2]+"RT"+ti[3]);

        registerReceiver(receiver,filter);
    }
    public void  isSuppourtBlueTooth(View view)
    {
        boolean ret = mController.isSupportBlueTooth();
        showToast("是否支持蓝牙？"+ret);

    }
    public void  isBlueToothEnable(View view)
    {
        boolean ret =mController.getBlueTootStatus();
        showToast("蓝牙是否打开"+ ret);
    }
    public void  cequestTurnOnBlueTooth(View view)
    {
        mController.turnOnBlueTooth(this,0);
    }
    public void turnOffBlueTooth(View view)
    {
        mController.turnoffBlueTooth();
    }
    private void  showToast(String text){
        if(mtoast == null){
            mtoast = Toast.makeText(this,text,Toast.LENGTH_SHORT);
        }
        else {
            mtoast.setText(text);
        }
        mtoast.show();
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        if (resultCode == RESULT_OK){
            showToast("打开成功");
        }
        else {
            showToast("打开失败");
        }
    }
}
