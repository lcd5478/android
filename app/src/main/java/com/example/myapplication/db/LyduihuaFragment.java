package com.example.myapplication.db;
        import android.os.Build;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.WindowManager;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.Nullable;
        import androidx.annotation.NonNull;
        import androidx.fragment.app.Fragment;
        import androidx.lifecycle.Observer;
        import androidx.lifecycle.ViewModelProviders;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.myapplication.Main_1Activity;
        import com.example.myapplication.R;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.logging.Handler;
        import java.util.logging.LogRecord;

public class LyduihuaFragment extends Fragment {

      public static List<Message> msgList = new ArrayList<>();
      private static final boolean DEBUG = false;

      public  static EditText inputText,timeTask_ms;
      private Button send,button_qingkong;
      public static RecyclerView msgRecyclerView;
      public static MsgAdapter adapter;
      private CheckBox box_zhibiao,box_time,box_hex;
      private Message msg1 = null;
      int anInt_zb;

      //isHEXsend 判断是以什么方式发送 false ASCLL  true 16进制
    static boolean isHEXsend=false,isHEXrec=false;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lyduihua, container, false);

        if(msgList.isEmpty()) {
       initMsgs(msg1);
        }
        //绑定控件
        inputText = root.findViewById(R.id.editText_input);
        send = root.findViewById(R.id.button_fasou);
        msgRecyclerView = root.findViewById(R.id.Recycler_duihua);
        box_zhibiao =root.findViewById(R.id.checkBox_zhibiao);
        button_qingkong =root.findViewById(R.id.button_qingkong);
        timeTask_ms = root.findViewById(R.id.editText_time);
        box_time =root.findViewById(R.id.checkBox_time);

        button_qingkong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!msgList.isEmpty()){ //判断列表里面有没有元素
                    msgList.clear();
                }
            }
        });
        //box_hex =root.findViewById(R.id.checkBox_hex);
        //布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        //为滑动框设置布局管理器，设置适配器
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        //为发送按钮设置监听事件
        send.setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View view) {
                 String content = inputText.getText().toString();
                 //如果输入不为空
                 if (LySetFragment.mConnectService == null || LySetFragment.mConnectService.getState() != BluetoothService.CONNECTED){
                     Toast.makeText(view.getContext(), "未连接到任何蓝牙设备", Toast.LENGTH_SHORT).show();
                     return;
                 }
                 else if (content == null || LySetFragment.mConnectService == null || content.equals(""))
                     return ;
                 else if (!"".equals(content)) {
                     if(sendMessage(content)){

                         //通知列表有新数据插入 这样数据才能在recyclerview中显示
                         adapter.notifyItemInserted(msgList.size() - 1);
                         //定位将显示的数据定位到最后一行，保证可以看到最后一条消息
                         msgRecyclerView.scrollToPosition(msgList.size() - 1);
                         inputText.setText("");
                     }
                 }
             }
         });


      /*  box_hex.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String content = inputText.getText().toString();
                if (isChecked){
                    if(!"".equals(content)) {
                        String ST = convertStringToHex(content);
                        inputText.setText(ST);
                        isHEXsend=true;
                        isHEXrec=true;
                    }
                }else {
                    String ST =  convertHexToString(content);
                    inputText.setText(ST);
                    isHEXsend=false;
                    isHEXrec=false;
                }
            }
        });*/



        /**
         * 定时选择框组件监听方法
         * 开启相应的时间任务
         */

        box_time.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (LySetFragment.mConnectService == null || LySetFragment.mConnectService.getState() != BluetoothService.CONNECTED){
                    Toast.makeText(buttonView.getContext(), "未连接到任何蓝牙设备", Toast.LENGTH_SHORT).show();
                    box_time.setChecked(false);

                }
                else if (LySetFragment.mConnectService == null) {
                    box_time.setChecked(false);

                }
                else if (isChecked){
                    String s=timeTask_ms.getText().toString();
                    //新建时间任务
                    if(s.length()!=0){
                        timeTask=new timeThread(Integer.valueOf(s));
                    }
                    else timeTask=new timeThread(1000);
                    //启动定时任务
                    timeTask.start();
                }else if (!isChecked) {
                    timeTask.interrupt();
                }
            }
        });

        return root;
    }


    //初始化消息列表
     private void initMsgs(Message msg1) {
         msg1 = new Message("Hallo", Message.TYPE_RECEIVED);
        msgList.add(msg1);
    }

    /**
     * 发送字符串
     * @param Str2Senda 欲发送的字符串.
     */
    private boolean sendMessage(String Str2Senda){

        try {
            Message msg = new Message(Str2Senda, Message.TYPE_SEND);
            msgList.add(msg);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("myDebugt","imeThread end"+e);

        }

        String Str2Send;
        if(box_zhibiao.isChecked()){
             Str2Send = "\t"+Str2Senda;
        }
        else {
            Str2Send =Str2Senda;
        }
        if (Str2Send.length() == 0) {
            return false;
        }
        if (LySetFragment.mConnectService == null || LySetFragment.mConnectService.getState() != BluetoothService.CONNECTED){
            Toast.makeText(this.getContext(), "未连接到任何蓝牙设备", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (Str2Send == null || LySetFragment.mConnectService == null || Str2Send.equals(""))
            return false;
        byte[] bs;
        if (!isHEXsend){
            bs = Str2Send.getBytes();
            LySetFragment.mConnectService.write(bs);
            return  true;
        } else {
            for (char c : Str2Send.toCharArray()) {
                if (!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F') || c == ' ')) {
                    Toast.makeText(this.getContext(), "发送内容含非法字符", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            bs = Str2Send.getBytes();
            LySetFragment.mConnectService.write(bs);
            return true;
        }
    }

    public static String convertStringToHex(String str){
        char[] chars = str.toCharArray();
        StringBuffer hex = new StringBuffer();
        for(int i = 0; i < chars.length; i++){
            hex.append(Integer.toHexString((int)chars[i]));
        }
        return hex.toString();
    }

    public static  String convertHexToString(String hex){
        StringBuilder sb = new StringBuilder();

        StringBuilder temp = new StringBuilder();
        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for( int i=0; i<hex.length()-1; i+=2 ){
            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char)decimal);
            temp.append(decimal);
        }
        return sb.toString();
    }

    timeThread timeTask=null;
    private class timeThread extends Thread{
        private int sleeptime;
        timeThread(int militime){
            super();
            sleeptime=militime;
        }
		/*byte[] buffer={'a','v','c','d','f','a','v','c','d','f','a','v','c',
				'a','v','c','d','f','a','v','c','d','f','a','v','c',
				'a','v','c','d','f','a','v','c','d','f','a','v','c',
				'a','v','c','d','f','a','v','c','d','f','a','v','c'
				,'d','f','a','v','c','d','f','a','v','c','d','f','\n'};*/

        @Override
        public void run(){
            while(!isInterrupted()){
                if(DEBUG) Log.i("myDebug", "timeThread start");

                //mHandler.obtainMessage(MainActivity.REC_DATA,buffer.length,-1,buffer).sendToTarget();
                try {
                    sendMessage(inputText.getText().toString());
                    Thread.sleep(sleeptime);
                    LyduihuaFragment.this.getActivity().runOnUiThread(updateThread);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.e("myDebug", "timeThread end"+e);
                    break;
                }
            }
            if(DEBUG)Log.i("myDebug", "timeThread end");
        }

        Runnable updateThread = new Runnable()
        {
            @Override
            public void run()
            {
                //更新UI
                try {
                    //通知列表有新数据插入 这样数据才能在recyclerview中显示
                    adapter.notifyItemInserted(msgList.size() - 1);
                    //定位将显示的数据定位到最后一行，保证可以看到最后一条消息
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);
                }catch (Exception E){
                    Log.e("556","E:"+E);
                }

            }
        };


    }
}
