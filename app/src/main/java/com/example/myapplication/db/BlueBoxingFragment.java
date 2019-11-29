package com.example.myapplication.db;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.myapplication.Main_1Activity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.BlueBoxingFragmentBinding;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class BlueBoxingFragment extends Fragment {

    private BlueBoxingViewModel mViewModel;
    private BlueBoxingFragmentBinding binding;
    private boolean isstart=false;
    public static boolean iskanyaxinghuang=false;
    public static String string_xingxi;
  //  public static BlueBoxingFragment newInstance() {
    //    return new BlueBoxingFragment();
   // }
  final List<Entry> entityList=new ArrayList<>();
    int i_num = 0;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.blue_boxing_fragment, container, false);
        binding.linechart.setNoDataText("暂无数据");
        binding.linechart.setDrawGridBackground(false);//绘图区后面的背景矩形将绘制
        binding.linechart.setDrawBorders(false);//禁止绘制图表边框的线
        //binding.linechart.getLineData();
        Linsten linsten =new Linsten();
        linsten.start();


       // for(int i=0;i<20;i++){
          //  entityList.add(new Entry(i, (float) (Math.random()*300+0)));
       // }
        LineDataSet lineDataSet = new LineDataSet(entityList,"直线一");
        LineData lineData = new LineData(lineDataSet);
        binding.linechart.setData(lineData);
        binding.linechart.invalidate();





        binding.button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


             //   entityList.add(new Entry(i[0], (float) (Math.random()*300+0)));
                entityList.clear();
                i_num=0;
                LineDataSet set1;

                set1 = (LineDataSet) binding.linechart.getData().getDataSetByIndex(0);

                set1.setValues(entityList);

                //刷新数据
                binding.linechart.getData().notifyDataChanged();
                binding.linechart.notifyDataSetChanged();
                binding.linechart.invalidate();
            }
        });
        binding.checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isstart=isChecked;
            }
        });



        return binding.getRoot();

    }

   private static void tim(){

   }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BlueBoxingViewModel.class);
        // TODO: Use the ViewModel
    }


    public  class Linsten extends Thread{
        String[] ti;
        String[] ti_nen;
        @Override
        public void run() {

            while(!false){
                if(isstart){
                    if(iskanyaxinghuang){
                        iskanyaxinghuang=false;
                        if (string_xingxi!=null){
                            //本来想写可以同时画多条折线，但是奈何技术有限，目前可以画一条，数据处理已经写了多条
                            //数据遵循烈酒（FireWater)协议
                         //   String S="ABC:56,-85,15.325";
                            ti = string_xingxi.split(":");

                            //ti_nen =ti[1].split(",");
                            try {
                                BlueBoxingFragment.this.getActivity().runOnUiThread(updateThread);

                            }catch (Exception e){
                                Log.e("BlueBoxingFragment", "******:"+ e);
                            }

                            string_xingxi=null;
                        }


                    }
                }

            }


        }
        Runnable updateThread = new Runnable()
        {
            @Override
            public void run()
            {
                //更新UI
                try {
                    i_num+=1;
                    LineDataSet set1;
                    entityList.add(new Entry(i_num,Float.valueOf(ti[1])));
                    set1 = (LineDataSet) binding.linechart.getData().getDataSetByIndex(0);

                    set1.setValues(entityList);

                    //刷新数据
                    binding.linechart.getData().notifyDataChanged();
                    binding.linechart.notifyDataSetChanged();
                    binding.linechart.invalidate();

                }catch (Exception E){
                    Log.e("556","E:"+E);
                }

            }
        };
    }

}
