package com.example.myapplication.db;
        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.icu.text.StringSearch;
        import android.os.Bundle;
        import android.renderscript.ScriptGroup;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.CompoundButton;
        import android.widget.EditText;
        import android.widget.SeekBar;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.Nullable;
        import androidx.annotation.NonNull;
        import androidx.databinding.DataBindingUtil;
        import androidx.fragment.app.Fragment;
        import androidx.lifecycle.Observer;
        import androidx.lifecycle.ViewModelProviders;
        import com.example.myapplication.R;
        import com.example.myapplication.SQL.MyData;
        import com.example.myapplication.activity_dl_ZC_WJMA.loginActivity;
        import com.example.myapplication.databinding.FragmentLyKaiguanBinding;
        import com.github.mikephil.charting.data.Entry;
        import com.github.mikephil.charting.data.LineData;
        import com.github.mikephil.charting.data.LineDataSet;

        import java.util.ArrayList;
        import java.util.List;

public class LyKaiguanFragment extends Fragment {
    KeyBlueViewModel keyBlueViewModel;
   private FragmentLyKaiguanBinding binding;
   private static boolean isbianji;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //View root = inflater.inflate(R.layout.fragment_ly_kaiguan, container, false);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_ly_kaiguan,container,false);
        keyBlueViewModel = ViewModelProviders.of(requireActivity()).get(KeyBlueViewModel.class);
        isbianji =false;
        binding.setData(keyBlueViewModel);
        binding.setLifecycleOwner(this);
        binding.seekBar.setProgress(keyBlueViewModel.getJingDuTiao2_now().getValue());
        binding.seekBar2.setProgress(keyBlueViewModel.getJingDuTiao1_now().getValue());
        binding.seekBar3.setProgress(keyBlueViewModel.getJingDuTiao3_now().getValue()*10/keyBlueViewModel.getJingDuTiao3_max().getValue());
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {//进度条改变
                keyBlueViewModel.getJingDuTiao2_now().setValue(i);
                String string =keyBlueViewModel.getJingDuTiao_ap().getValue()[1]+"-"+i;
                FaSouData(string);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (isbianji)
                    TanChuangSheZheng_jingdutiao(getView(),1);


            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        binding.seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {//进度条改变
                keyBlueViewModel.getJingDuTiao1_now().setValue(i);
                String string =keyBlueViewModel.getJingDuTiao_ap().getValue()[0]+"-"+i;
                FaSouData(string);
            }
            /*

             * 按住seekbar时的事件监听处理

             * */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (isbianji)
                    TanChuangSheZheng_jingdutiao(getView(),0);

            }
            @Override
            /*

             * 放开seekbar时的时间监听处理

             * */
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        binding.seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {//进度条改变
                keyBlueViewModel.getJingDuTiao3_now().setValue(i*keyBlueViewModel.getJingDuTiao3_max().getValue()/10);
                String string =keyBlueViewModel.getJingDuTiao_ap().getValue()[2]+"-"+i*keyBlueViewModel.getJingDuTiao3_max().getValue()/10;
                FaSouData(string);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (isbianji)
                    TanChuangSheZheng_jingdutiao(getView(),2);
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        binding.switch8.setChecked(keyBlueViewModel.getBluekey1_zt().getValue()[0]);
        binding.switch9.setChecked(keyBlueViewModel.getBluekey1_zt().getValue()[1]);
        binding.switch10.setChecked(keyBlueViewModel.getBluekey1_zt().getValue()[2]);
        binding.switch11.setChecked(keyBlueViewModel.getBluekey1_zt().getValue()[3]);
        binding.switch12.setChecked(keyBlueViewModel.getBluekey1_zt().getValue()[4]);
        binding.switch13.setChecked(keyBlueViewModel.getBluekey1_zt().getValue()[5]);
        binding.switch8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isbianji==true){
                    TanChuangSheZheng(buttonView,0);
                    binding.switch8.setChecked(false);
                    isChecked=false;
                }
                else {
                    if(isChecked)
                        FaSouData(keyBlueViewModel.getBluekey().getValue()[0][0]);
                    else
                        FaSouData(keyBlueViewModel.getBluekey().getValue()[0][1]);
                }
                boolean[] booleans=keyBlueViewModel.getBluekey1_zt().getValue();
                    booleans[0]=isChecked;
                keyBlueViewModel.getBluekey1_zt().setValue(booleans);

            }
        });
        binding.switch9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isbianji==true){
                    TanChuangSheZheng(buttonView,1);
                    binding.switch9.setChecked(false);
                    isChecked=false;
                }
                else {
                    if(isChecked)
                        FaSouData(keyBlueViewModel.getBluekey().getValue()[1][0]);
                    else
                        FaSouData(keyBlueViewModel.getBluekey().getValue()[1][1]);
                }
                boolean[] booleans=keyBlueViewModel.getBluekey1_zt().getValue();
                booleans[1]=isChecked;
                keyBlueViewModel.getBluekey1_zt().setValue(booleans);
            }
        });
        binding.switch10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isbianji==true){
                    TanChuangSheZheng(buttonView,2);
                    binding.switch10.setChecked(false);
                    isChecked=false;
                } else {
                    if(isChecked)
                        FaSouData(keyBlueViewModel.getBluekey().getValue()[2][0]);
                    else
                        FaSouData(keyBlueViewModel.getBluekey().getValue()[2][1]);
                }
                boolean[] booleans=keyBlueViewModel.getBluekey1_zt().getValue();
                booleans[2]=isChecked;
                keyBlueViewModel.getBluekey1_zt().setValue(booleans);
            }
        });
        binding.switch11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isbianji==true){
                    TanChuangSheZheng(buttonView,3);
                    binding.switch11.setChecked(false);
                    isChecked=false;
                }else {
                    if(isChecked)
                        FaSouData(keyBlueViewModel.getBluekey().getValue()[3][0]);
                    else
                        FaSouData(keyBlueViewModel.getBluekey().getValue()[3][1]);
                }
                boolean[] booleans=keyBlueViewModel.getBluekey1_zt().getValue();
                booleans[3]=isChecked;
                keyBlueViewModel.getBluekey1_zt().setValue(booleans);
            }
        });
        binding.switch12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isbianji==true){
                    TanChuangSheZheng(buttonView,4);
                    binding.switch12.setChecked(false);
                    isChecked=false;
                }else {
                    if(isChecked)
                        FaSouData(keyBlueViewModel.getBluekey().getValue()[4][0]);
                    else
                        FaSouData(keyBlueViewModel.getBluekey().getValue()[4][1]);
                }
                boolean[] booleans=keyBlueViewModel.getBluekey1_zt().getValue();
                booleans[4]=isChecked;
                keyBlueViewModel.getBluekey1_zt().setValue(booleans);
            }
        });
        binding.switch13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isbianji==true){
                    TanChuangSheZheng(buttonView,5);
                    binding.switch13.setChecked(false);
                    isChecked=false;
                }else {
                    if(isChecked)
                        FaSouData(keyBlueViewModel.getBluekey().getValue()[5][0]);
                    else
                        FaSouData(keyBlueViewModel.getBluekey().getValue()[5][1]);
                }
                boolean[] booleans=keyBlueViewModel.getBluekey1_zt().getValue();
                booleans[5]=isChecked;
                keyBlueViewModel.getBluekey1_zt().setValue(booleans);
            }
        });
        binding.switch14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
           isbianji =isChecked;
            }
        });





        return binding.getRoot();
    }

    private  void FaSouData(String s){
        if (LySetFragment.mConnectService == null || LySetFragment.mConnectService.getState() != BluetoothService.CONNECTED){
            Toast.makeText(this.getContext(), "未连接到任何蓝牙设备", Toast.LENGTH_SHORT).show();
        } else if(s==null) {
            return;
        }else{
            byte[] bs;
            bs = s.getBytes();
            LySetFragment.mConnectService.write(bs);
        }
    }




    private void TanChuangSheZheng(View v, final int inInt){

        final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setIcon(R.drawable.shezhi);
        builder.setTitle("修改开关");
        //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        View view = LayoutInflater.from(v.getContext()).inflate(R.layout.kaiguan_tanchuang, null);
        //    设置我们自己定义的布局文件作为弹出框的Content
        builder.setView(view);

        final EditText username = (EditText)view.findViewById(R.id.editText_boutt_text_key);
        final EditText uptext = (EditText)view.findViewById(R.id.editText_boutt_up_key);
        final EditText downtext = (EditText)view.findViewById(R.id.editText_boutt_down_key);
        switch (inInt) {
            case 0:
                username.setText(keyBlueViewModel.getBluekey1_name().getValue());break;
            case 1:
                username.setText(keyBlueViewModel.getBluekey2_name().getValue());break;
            case 2:
                username.setText(keyBlueViewModel.getBluekey3_name().getValue());break;
            case 3:
                username.setText(keyBlueViewModel.getBluekey4_name().getValue());break;
            case 4:
                username.setText(keyBlueViewModel.getBluekey5_name().getValue());break;
            case 5:
                username.setText(keyBlueViewModel.getBluekey6_name().getValue());break;
        }
        uptext.setText(keyBlueViewModel.getBluekey().getValue()[inInt][0]);
        downtext.setText(keyBlueViewModel.getBluekey().getValue()[inInt][1]);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String a = username.getText().toString();
                String b = uptext.getText().toString();
                String c = downtext.getText().toString();
                switch (inInt) {
                    case 0:
                        keyBlueViewModel.getBluekey1_name().setValue(a);break;
                    case 1:
                        keyBlueViewModel.getBluekey2_name().setValue(a);break;
                    case 2:
                        keyBlueViewModel.getBluekey3_name().setValue(a);break;
                    case 3:
                        keyBlueViewModel.getBluekey4_name().setValue(a);break;
                    case 4:
                        keyBlueViewModel.getBluekey5_name().setValue(a);break;
                    case 5:
                        keyBlueViewModel.getBluekey6_name().setValue(a);break;
                }
                String[][] strings=  keyBlueViewModel.getBluekey().getValue();
                strings[inInt][0]=b;
                strings[inInt][1]=c;
                keyBlueViewModel.getBluekey().setValue(strings);

                MyData myData =new MyData(getContext());

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
    private void TanChuangSheZheng_jingdutiao(View v, final int inInt){

        final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setIcon(R.drawable.shezhi);
        builder.setTitle("修改进度条");
        //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        View view = LayoutInflater.from(v.getContext()).inflate(R.layout.jingdutiao_tanchuang, null);
        //    设置我们自己定义的布局文件作为弹出框的Content
        builder.setView(view);

        final EditText username = (EditText)view.findViewById(R.id.editText_boutt_jingdutiao);
        final EditText uptext = (EditText)view.findViewById(R.id.editText_boutt_up_jingdutiao);
        final EditText downtext = (EditText)view.findViewById(R.id.editText_boutt_down_jingdutiao);
        switch (inInt){
            case 0:
                username.setText(keyBlueViewModel.getJingDuTiao1_name().getValue());
                uptext.setText(String.valueOf(keyBlueViewModel.getJingDuTiao1_max().getValue()));
                downtext.setText(keyBlueViewModel.getJingDuTiao_ap().getValue()[0]);
                break;
            case 1:
                username.setText(keyBlueViewModel.getJingDuTiao2_name().getValue());
                uptext.setText(String.valueOf(keyBlueViewModel.getJingDuTiao2_max().getValue()));
                downtext.setText(keyBlueViewModel.getJingDuTiao_ap().getValue()[1]);
                break;
            case 2:
                username.setText(keyBlueViewModel.getJingDuTiao3_name().getValue());
                uptext.setText(String.valueOf(keyBlueViewModel.getJingDuTiao3_max().getValue()));
                downtext.setText(keyBlueViewModel.getJingDuTiao_ap().getValue()[2]);
                break;

        }

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String a = username.getText().toString();
                String b = uptext.getText().toString();
                String c = downtext.getText().toString();
                switch (inInt) {
                    case 0:
                        keyBlueViewModel.getJingDuTiao1_name().setValue(a);
                        keyBlueViewModel.getJingDuTiao1_max().setValue(Integer.valueOf(b));
                        break;
                    case 1:
                        keyBlueViewModel.getJingDuTiao2_name().setValue(a);
                        keyBlueViewModel.getJingDuTiao2_max().setValue(Integer.valueOf(b));
                        break;
                    case 2:
                        keyBlueViewModel.getJingDuTiao3_name().setValue(a);
                        keyBlueViewModel.getJingDuTiao3_max().setValue(Integer.valueOf(b));
                        break;

                }
                String[] string= keyBlueViewModel.getJingDuTiao_ap().getValue();
                string[inInt] =c;
                keyBlueViewModel.getJingDuTiao_ap().setValue(string);

                MyData myData =new MyData(getContext());

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