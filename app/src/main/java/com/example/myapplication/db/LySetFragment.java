package com.example.myapplication.db;
        import android.Manifest;
        import android.app.Activity;
        import android.bluetooth.BluetoothAdapter;
        import android.bluetooth.BluetoothDevice;
        import android.bluetooth.BluetoothHeadset;
        import android.bluetooth.BluetoothProfile;
        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.ContextWrapper;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.os.Bundle;
        import android.os.Handler;
        import android.os.Message;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;
        import android.widget.Toolbar;

        import androidx.annotation.Nullable;
        import androidx.annotation.NonNull;
        import androidx.fragment.app.Fragment;
        import androidx.lifecycle.Observer;
        import androidx.lifecycle.ViewModelProviders;

        import com.example.myapplication.Main_1Activity;
        import com.example.myapplication.R;
        import com.mylhyl.acp.Acp;
        import com.mylhyl.acp.AcpListener;
        import com.mylhyl.acp.AcpOptions;
        import  com.example.myapplication.db.*;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Set;

        import static android.bluetooth.BluetoothProfile.HEADSET;

public class LySetFragment<textView1_shebe> extends Fragment {

    private static final String TAG = "DeviceList";
    private static final boolean DEBUG = true;
    public static final int REC_DATA = 2;
    public static final int CONNECTED_DEVICE_NAME = 4;
    public static final int BT_TOAST = 5;
    public static final int MAIN_TOAST = 6;


    public static BluetoothService mConnectService = null;
    static boolean isHEXsend=false,isHEXrec=false;

    private TextView RecDataView;
    public static String DEVICE_ADDRESS = "device address";

    // 已连接设备的名字
    public static String mConnectedDeviceName = null;
    //蓝牙连接服务对象
    public static BluetoothAdapter mBluetoothAdapter = null;


    // 标志字符串常量
    public static final String DEVICE_NAME = "device name";
    public static final String TOAST = "toast";


    public TextView textView1_shebe;
    private BluetoothAdapter mBtAdapter;
    private ArrayAdapter<String> mPairedDevices;
    private ArrayAdapter<String> mNewDevices;
    private Button scanButton;
    private CheckBox box1,box2;
    private TextView toolbar;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_ly_set, container, false);
        scanButton = (Button)root.findViewById(R.id.button_soushuo);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtDiscovery();

            }
        });
        mPairedDevices =new ArrayAdapter(this.getActivity(),R.layout.device_name);
        mNewDevices =new ArrayAdapter(this.getActivity(),R.layout.device_name);
        final ListView lv_ypd = root.findViewById(R.id.listview_ypd);
        final ListView lv_wpd = root.findViewById(R.id.listview_wpd);
        textView1_shebe =root.findViewById(R.id.text_shezhi);



        lv_wpd.setAdapter(mNewDevices);
        lv_ypd.setAdapter(mPairedDevices);
        lv_wpd.setOnItemClickListener(mDeviceClickListener);
        lv_ypd.setOnItemClickListener(mDeviceClickListener);
        ListViewStack(root);

        // 获取匹配设备列表
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
        // 将匹配的设备添加到列表显示

        if (pairedDevices.size() > 0)
        {
          //  findViewById(R.id.title_paired_devices).setVisibility(View.VISIBLE);
            for (BluetoothDevice device : pairedDevices)
            {
                mPairedDevices.add(device.getName() + "\n"
                        + device.getAddress());
            }
        }
        else
        {
            mPairedDevices.add("未搜到任何设备");
        }

        box1=root.findViewById(R.id.checkBox_ypd);
        box2=root.findViewById(R.id.checkBox_wpd);


        box1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                   // lv_wpd.setVisibility(View.GONE);
                    lv_ypd.setVisibility(View.GONE);
                }else {
                    lv_ypd.setVisibility(View.VISIBLE);
                }
            }
        });
        box2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){


                    lv_wpd.setVisibility(View.GONE);
                }else {
                    lv_wpd.setVisibility(View.VISIBLE);
                }

            }
        });




        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(this.getActivity(), "蓝牙不可用", Toast.LENGTH_LONG).show();
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Toast.makeText(this.getActivity(), "蓝牙未打开", Toast.LENGTH_LONG).show();
            Intent intent =new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            this.getActivity().startActivityForResult(intent,0);
        }
        if (mConnectService == null){

            mConnectService = new BluetoothService(mHandler);
            //toolbar.setTitle("蓝牙串口助手（未连接）");
            // toolbar.setText("未连接");
        }




        return root;
    }





    private void  ListViewStack(View root){

        // 注册蓝牙搜索广播
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        this.getActivity().registerReceiver(mReceiver, filter);
        IntentFilter stateChangeFilter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        IntentFilter connectedFilter = new IntentFilter(BluetoothDevice.ACTION_ACL_CONNECTED);
        IntentFilter disConnectedFilter = new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        this.getActivity().registerReceiver(mReceiver, stateChangeFilter);
        this.getActivity().registerReceiver(mReceiver, connectedFilter);
        this.getActivity().registerReceiver(mReceiver, disConnectedFilter);
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
    }
    /**
            * 蓝牙搜索方法
	 */
    private void BtDiscovery()
    {
        if (!mBluetoothAdapter.isEnabled()) {
            Toast.makeText(this.getActivity(), "蓝牙未打开", Toast.LENGTH_LONG).show();
            Intent intent =new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            this.getActivity().startActivityForResult(intent,0);
            return;
        }
        if (DEBUG) Log.d(TAG, "doDisc overy()");

        // 当正在搜索，则停止搜索
        if (mBtAdapter.isDiscovering())
        {
            mBtAdapter.cancelDiscovery();
            scanButton.setText("搜索设备");
        }
        // 否则开启蓝牙搜索
        else{
            scanButton.setText("正在搜索(点击停止)");
            //清空新搜索到设备的列表以免重复添加
            mNewDevices.clear();
            // 开始搜索设备
            mBtAdapter.startDiscovery();
        }
    }
    // 列表项点击监听方法
    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener()
    {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3)
        {
            if(mBluetoothAdapter.isEnabled()) {
                // 关闭蓝牙搜索以便进行蓝牙连接
                mBtAdapter.cancelDiscovery();
                // 列表项名称的最后17个字符为MAC地址
                String str = ((TextView) v).getText().toString();
                String address = str.substring(str.length() - 17);
                onActivityResult(address);
            }
        }
    };

    String[] hex_string_table=new String[256];
    private void init_hex_string_table(){
        for(int i=0;i<256;i++){
            if(i<16){
                hex_string_table[i]=" 0"+Integer.toHexString(i).toUpperCase();
            }else{
                hex_string_table[i]=" "+Integer.toHexString(i).toUpperCase();
            }
        }
    }
    private int align_num=0;//对齐字节数
    // 用于从线程获取信息的Handler对象
   private final Handler mHandler = new Handler(){


        StringBuffer sb=new StringBuffer();
        byte[] bs;
        float sWidth;

        int b,i,lineWidth=0,align_i=0;
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REC_DATA:
                    sb.setLength(0);
                    if(isHEXrec){
                        bs=(byte[])msg.obj;
                        for(i=0;i<msg.arg1;i++){
                            b=(bs[i]&0xff);
                            sb.append(hex_string_table[b]);
                        }
                    }else {
                        bs=(byte[])msg.obj;
                        char[] c=new char[msg.arg1];
                        for(i=0;i<msg.arg1;i++){
                            c[i]=(char)(bs[i]&0xff);
                            sb.append(c[i]);
                        }
                    }
                    com.example.myapplication.db.Message message =new com.example.myapplication.db.Message(sb.toString(), com.example.myapplication.db.Message.TYPE_RECEIVED);
                    LyduihuaFragment.msgList.add(message);
                    //通知列表有新数据插入 这样数据才能在recyclerview中显示
                    LyduihuaFragment.adapter.notifyItemInserted(LyduihuaFragment.msgList.size() - 1);
                    //定位将显示的数据定位到最后一行，保证可以看到最后一条消息
                    LyduihuaFragment.msgRecyclerView.scrollToPosition(LyduihuaFragment.msgList.size() - 1);
                    break;
                case CONNECTED_DEVICE_NAME:
                    // 提示已连接设备名
                    mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
                  //  Toast.makeText(this.getActivity(), "已连接到"
                           // + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                 //   Main_1Activity.this.setTitle("蓝牙串口助手(已连接)");
                 //  Main_1Activity.iten_clik(1,mConnectedDeviceName);
                   // textView1_shebe.setText("连接设备（已连接）");
                   // toolbar.setTitle("蓝牙串口助手（已连接）");
                   // string_111 ="连接设备（已连接）";
                   // toolbar.setText("已连接");
                    break;
                case BT_TOAST:
                  //  if(mConnectedDeviceName!=null)
                    //    Toast.makeText(getApplicationContext(), "与"+mConnectedDeviceName+
                     //           msg.getData().getString(TOAST),Toast.LENGTH_SHORT).show();
                   // else Toast.makeText(getApplicationContext(), "与"+target_device_name+
                     //       msg.getData().getString(TOAST),Toast.LENGTH_SHORT).show();
                 //   Main_1Activity.this.setTitle("蓝牙串口助手(未连接)");
                   // textView1_shebe.setText("连接设备（未连接）");
                   // string_111 ="连接设备（未连接）";
                   // toolbar.setTitle("蓝牙串口助手（未连接）");
                   // toolbar.setText("未连接");
                    mConnectedDeviceName=null;
                    break;
                case MAIN_TOAST:
                  //  Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
                    break;
            }
        }




 };

 private String target_device_name=null;

        public void onActivityResult(String address) {
            Log.e(TAG, "onActivityResult");
            // 提取蓝牙地址数据

                    // 获取设备
                    BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
                    target_device_name=device.getName();
                    if(target_device_name.equals(mConnectedDeviceName)){
                        Toast.makeText(this.getActivity(), "已连接"+mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    // 提示正在连接设备
                    Toast.makeText(this.getActivity(), "正在连接"+target_device_name, Toast.LENGTH_SHORT).show();
                    // 连接设备
                    mConnectService.connect(device);



        }

    // 蓝牙广播
    private final BroadcastReceiver mReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();

            // 发现设备
            if (BluetoothDevice.ACTION_FOUND.equals(action))
            {
                BluetoothDevice device ;
                // 获取蓝牙设备
                device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // 已匹配的跳过
                if (device.getBondState() != BluetoothDevice.BOND_BONDED)
                {
                    mNewDevices.add(device.getName() + "\n" + device.getAddress());
                }
            }
            //蓝牙搜索完成
            else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action))
            {
                scanButton.setText("搜索设备");
                if (mNewDevices.getCount() == 0)
                {
                    mNewDevices.add("未搜到任何设备");
                }
            }
            else if (action.equals(BluetoothDevice.ACTION_ACL_CONNECTED)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String name = device.getName();
                Toast.makeText(context, "已连接"+name, Toast.LENGTH_SHORT).show();
                //连接上了
            }
            else if (action.equals(BluetoothDevice.ACTION_ACL_DISCONNECTED)) {
                //蓝牙连接被切断
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String name = device.getName();
              //  Toast.showToast(name + "的连接被断开", getApplicationContext());
                Toast.makeText(context, name + "的连接被断开", Toast.LENGTH_SHORT).show();

                return;
            }

        }
    };




}


