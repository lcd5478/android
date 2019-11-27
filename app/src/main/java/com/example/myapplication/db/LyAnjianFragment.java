package com.example.myapplication.db;
        import android.app.AlertDialog;
        import android.app.ProgressDialog;
        import android.content.DialogInterface;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.CompoundButton;
        import android.widget.EditText;
        import android.widget.Switch;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.Nullable;
        import androidx.annotation.NonNull;
        import androidx.fragment.app.Fragment;
        import androidx.lifecycle.Observer;
        import androidx.lifecycle.ViewModelProviders;

        import com.example.myapplication.R;
        import com.example.myapplication.SQL.MyData;
        import com.example.myapplication.activity_dl_ZC_WJMA.loginActivity;

public class LyAnjianFragment extends Fragment {
        private Button button1,button2,button3,button4,button5,button6,button7,button8,button9,button10,button11,button12;
        private Switch switch_bianji;
    private static String[][] strings = new String[12][3];//strings[*][0] 为按下的数据，strings[*][1] 为抬起的数据，strings[*][2] 为名称的数据，
    private boolean  is_bianji;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_ly_anjian, container, false);

        button1 =root.findViewById(R.id.button1);
        button2 =root.findViewById(R.id.button2);
        button3 =root.findViewById(R.id.button3);
        button4 =root.findViewById(R.id.button4);
        button5 =root.findViewById(R.id.button5);
        button6 =root.findViewById(R.id.button6);
        button7 =root.findViewById(R.id.button7);
        button8 =root.findViewById(R.id.button8);
        button9 =root.findViewById(R.id.button9);
        button10 =root.findViewById(R.id.button10);
        button11 =root.findViewById(R.id.button11);
        button12 =root.findViewById(R.id.button12);
        switch_bianji=root.findViewById(R.id.switch_bianji);


        button1.setOnTouchListener(buttonListener1);
        button2.setOnTouchListener(buttonListener2);
        button3.setOnTouchListener(buttonListener3);
        button4.setOnTouchListener(buttonListener4);
        button5.setOnTouchListener(buttonListener5);
        button6.setOnTouchListener(buttonListener6);
        button7.setOnTouchListener(buttonListener7);
        button8.setOnTouchListener(buttonListener8);
        button9.setOnTouchListener(buttonListener9);
        button10.setOnTouchListener(buttonListener10);
        button11.setOnTouchListener(buttonListener11);
        button12.setOnTouchListener(buttonListener12);

        switch_bianji.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                is_bianji=isChecked;
            }
        });
        MyData myData =new MyData(getContext());
        strings = myData.load(loginActivity.name);
        String a =null;
        for(int i=0;i<12;i++)
            if (strings[i][2]!=null)
            {
                a=strings[i][2];
                switch (i) {
                    case 0:
                        button1.setText(a);
                        break;
                    case 1:
                        button2.setText(a);
                        break;
                    case 2:
                        button3.setText(a);
                        break;
                    case 3:
                        button4.setText(a);
                        break;
                    case 4:
                        button5.setText(a);
                        break;
                    case 5:
                        button6.setText(a);
                        break;
                    case 6:
                        button7.setText(a);
                        break;
                    case 7:
                        button8.setText(a);
                        break;
                    case 8:
                        button9.setText(a);
                        break;
                    case 9:
                        button10.setText(a);
                        break;
                    case 10:
                        button11.setText(a);
                        break;
                    case 11:
                        button12.setText(a);
                        break;
                    default:
                        break;
                }
            }
        return root;
    }

    private View.OnTouchListener buttonListener1 = new View.OnTouchListener() {
        public boolean onTouch(View arg0, MotionEvent event) {
            // TODO Auto-generated method stub
            int action = event.getAction();
            if(!is_bianji){
            if (action == MotionEvent.ACTION_DOWN) { // 按下
                if (strings[0][0]!=null){
                    FaSouData(strings[0][0]);
                }
            } else if (action == MotionEvent.ACTION_UP) { // 松开
                if(strings[0][1]!=null)
                FaSouData(strings[0][1]);
            }
            }else if(action == MotionEvent.ACTION_UP){
                TanChuangSheZheng(arg0,0);
            }
            return false;
        }
    };
    private View.OnTouchListener buttonListener2 = new View.OnTouchListener() {
        public boolean onTouch(View arg0, MotionEvent event) {
            // TODO Auto-generated method stub
            int action = event.getAction();
            if(!is_bianji){
                if (action == MotionEvent.ACTION_DOWN) { // 按下
                    if (strings[1][0]!=null){
                        FaSouData(strings[1][0]);
                    }
                } else if (action == MotionEvent.ACTION_UP) { // 松开
                    if (strings[1][1] !=null){
                    FaSouData(strings[1][1]);
                    }
                }
            }else if(action == MotionEvent.ACTION_UP){
                TanChuangSheZheng(arg0,1);
            }
            return false;
        }
    };
    private View.OnTouchListener buttonListener3 = new View.OnTouchListener() {
        public boolean onTouch(View arg0, MotionEvent event) {
            // TODO Auto-generated method stub
            int action = event.getAction();
            if(!is_bianji){
                if (action == MotionEvent.ACTION_DOWN) { // 按下
                    if (strings[2][0]!=null){
                        FaSouData(strings[2][0]);
                    }
                } else if (action == MotionEvent.ACTION_UP) { // 松开
                    if (strings[2][1]!=null)
                    FaSouData(strings[2][1]);
                }
            }else if(action == MotionEvent.ACTION_UP){
                TanChuangSheZheng(arg0,2);
            }
            return false;
        }
    };
    private View.OnTouchListener buttonListener4 = new View.OnTouchListener() {
        public boolean onTouch(View arg0, MotionEvent event) {
            // TODO Auto-generated method stub
            int action = event.getAction();
            if(!is_bianji){
                if (action == MotionEvent.ACTION_DOWN) { // 按下
                    if (strings[3][0]!=null){
                        FaSouData(strings[3][0]);
                    }
                } else if (action == MotionEvent.ACTION_UP) { // 松开
                    if (strings[3][1]!=null)
                    FaSouData(strings[3][1]);
                }
            }else if(action == MotionEvent.ACTION_UP){
                TanChuangSheZheng(arg0,3);
            }
            return false;
        }
    };
    private View.OnTouchListener buttonListener5 = new View.OnTouchListener() {
        public boolean onTouch(View arg0, MotionEvent event) {
            // TODO Auto-generated method stub
            int action = event.getAction();
            if(!is_bianji){
                if (action == MotionEvent.ACTION_DOWN) { // 按下
                    if (strings[4][0]!=null){
                        FaSouData(strings[4][0]);
                    }
                } else if (action == MotionEvent.ACTION_UP) { // 松开
                    if (strings[4][1]!=null)
                    FaSouData(strings[4][1]);
                }
            }else if(action == MotionEvent.ACTION_UP){
                TanChuangSheZheng(arg0,4);
            }
            return false;
        }
    };
    private View.OnTouchListener buttonListener6 = new View.OnTouchListener() {
        public boolean onTouch(View arg0, MotionEvent event) {
            // TODO Auto-generated method stub
            int action = event.getAction();
            if(!is_bianji){
                if (action == MotionEvent.ACTION_DOWN) { // 按下
                    if (strings[5][0]!=null){
                        FaSouData(strings[5][0]);
                    }
                } else if (action == MotionEvent.ACTION_UP) { // 松开
                    if (strings[5][1]!=null)
                    FaSouData(strings[5][1]);
                }
            }else if(action == MotionEvent.ACTION_UP){
                TanChuangSheZheng(arg0,5);
            }
            return false;
        }
    };
    private View.OnTouchListener buttonListener7 = new View.OnTouchListener() {
        public boolean onTouch(View arg0, MotionEvent event) {
            // TODO Auto-generated method stub
            int action = event.getAction();
            if(!is_bianji){
                if (action == MotionEvent.ACTION_DOWN) { // 按下
                    if (strings[6][0]!=null){
                        FaSouData(strings[6][0]);
                    }
                } else if (action == MotionEvent.ACTION_UP) { // 松开
                    if(strings[6][1]!=null)
                    FaSouData(strings[6][1]);
                }
            }else if(action == MotionEvent.ACTION_UP){
                TanChuangSheZheng(arg0,6);
            }
            return false;
        }
    };


    private View.OnTouchListener buttonListener8 = new View.OnTouchListener() {
        public boolean onTouch(View arg0, MotionEvent event) {
            // TODO Auto-generated method stub
            int action = event.getAction();
            if(!is_bianji){
                if (action == MotionEvent.ACTION_DOWN) { // 按下
                    if (strings[7][0]!=null){
                        FaSouData(strings[7][0]);
                    }
                } else if (action == MotionEvent.ACTION_UP) { // 松开
                    if (strings[7][1]!=null)
                    FaSouData(strings[7][1]);
                }
            }else if(action == MotionEvent.ACTION_UP){
                TanChuangSheZheng(arg0,7);
            }
            return false;
        }
    };
    private View.OnTouchListener buttonListener9 = new View.OnTouchListener() {
        public boolean onTouch(View arg0, MotionEvent event) {
            // TODO Auto-generated method stub
            int action = event.getAction();
            if(!is_bianji){
                if (action == MotionEvent.ACTION_DOWN) { // 按下
                    if (strings[8][0]!=null){
                        FaSouData(strings[8][0]);
                    }
                } else if (action == MotionEvent.ACTION_UP) { // 松开
                    if (strings[8][1]!=null)
                    FaSouData(strings[8][1]);
                }
            }else if(action == MotionEvent.ACTION_UP){
                TanChuangSheZheng(arg0,8);
            }
            return false;
        }
    };
    private View.OnTouchListener buttonListener10 = new View.OnTouchListener() {
        public boolean onTouch(View arg0, MotionEvent event) {
            // TODO Auto-generated method stub
            int action = event.getAction();
            if(!is_bianji){
                if (action == MotionEvent.ACTION_DOWN) { // 按下
                    if (strings[9][0]!=null){
                        FaSouData(strings[9][0]);
                    }
                } else if (action == MotionEvent.ACTION_UP) { // 松开
                    if (strings[9][1]!=null)
                    FaSouData(strings[9][1]);
                }
            }else if(action == MotionEvent.ACTION_UP){
                TanChuangSheZheng(arg0,9);
            }
            return false;
        }
    };
    private View.OnTouchListener buttonListener11 = new View.OnTouchListener() {
        public boolean onTouch(View arg0, MotionEvent event) {
            // TODO Auto-generated method stub
            int action = event.getAction();
            if(!is_bianji){
                if (action == MotionEvent.ACTION_DOWN) { // 按下
                    if (strings[10][0]!=null){
                        FaSouData(strings[10][0]);
                    }
                } else if (action == MotionEvent.ACTION_UP) { // 松开
                    if (strings[10][1]!=null)
                    FaSouData(strings[10][1]);
                }
            }else if (action == MotionEvent.ACTION_UP){
                TanChuangSheZheng(arg0,10);
            }
            return false;
        }
    };
    private View.OnTouchListener buttonListener12 = new View.OnTouchListener() {
        public boolean onTouch(View arg0, MotionEvent event) {
            // TODO Auto-generated method stub
            int action = event.getAction();
            if(!is_bianji){
                if (action == MotionEvent.ACTION_DOWN) { // 按下
                    if (strings[11][0]!=null){
                        FaSouData(strings[11][0]);
                    }
                } else if (action == MotionEvent.ACTION_UP) { // 松开
                    if (strings[11][1]!=null)
                    FaSouData(strings[11][1]);
                }
            }else if(action == MotionEvent.ACTION_UP){
                TanChuangSheZheng(arg0,11);
            }
            return false;
        }
    };
    private  void FaSouData(String s){
        if (LySetFragment.mConnectService == null || LySetFragment.mConnectService.getState() != BluetoothService.CONNECTED){
            Toast.makeText(this.getContext(), "未连接到任何蓝牙设备", Toast.LENGTH_SHORT).show();
        } else{
            byte[] bs;
            bs = s.getBytes();
            LySetFragment.mConnectService.write(bs);
        }
    }




    private void TanChuangSheZheng(View v, final int inInt){

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setIcon(R.drawable.shezhi);
        builder.setTitle("修改按钮");
        //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        View view = LayoutInflater.from(v.getContext()).inflate(R.layout.tanchuang, null);
        //    设置我们自己定义的布局文件作为弹出框的Content
        builder.setView(view);

        final EditText username = (EditText)view.findViewById(R.id.editText_boutt_text);
        final EditText uptext = (EditText)view.findViewById(R.id.editText_boutt_up);
        final EditText downtext = (EditText)view.findViewById(R.id.editText_boutt_down);
            if (strings[inInt][0]!=null)
          uptext.setText(strings[inInt][0]);
            if (strings[inInt][1]!=null)
          downtext.setText(strings[inInt][1]);
            if(strings[inInt][2]!=null)
          username.setText(strings[inInt][2]);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String a = username.getText().toString();
                String b = uptext.getText().toString();
                String c = downtext.getText().toString();
                strings[inInt][0] = b;
                strings[inInt][1] = c;
                strings[inInt][2] =a;
                switch (inInt) {
                    case 0:
                        button1.setText(a);
                        break;
                    case 1:
                        button2.setText(a);
                        break;
                    case 2:
                        button3.setText(a);
                        break;
                    case 3:
                        button4.setText(a);
                        break;
                    case 4:
                        button5.setText(a);
                        break;
                    case 5:
                        button6.setText(a);
                        break;
                    case 6:
                        button7.setText(a);
                        break;
                    case 7:
                        button8.setText(a);
                        break;
                    case 8:
                        button9.setText(a);
                        break;
                    case 9:
                        button10.setText(a);
                        break;
                    case 10:
                        button11.setText(a);
                        break;
                    case 11:
                        button12.setText(a);
                        break;
                    default:
                        break;
                }
                MyData myData =new MyData(getContext());
                myData.save(loginActivity.name,strings);
                //   Toast.makeText(this.getActivity(), strings[0]+"我"+strings[1]+"是的"+strings[2], Toast.LENGTH_SHORT).show();
                //    return strings;
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();


    }


}