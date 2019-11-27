package com.example.myapplication.db;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.BlueBoxingFragmentBinding;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class BlueBoxingFragment extends Fragment {

    private BlueBoxingViewModel mViewModel;
    private BlueBoxingFragmentBinding binding;
    public static BlueBoxingFragment newInstance() {
        return new BlueBoxingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.blue_boxing_fragment, container, false);
        List<Entry> entityList=new ArrayList<>();
        for(int i=0;i<24;i++){
            entityList.add(new Entry(i, (float) (Math.random()*300+0)));
        }
        LineDataSet lineDataSet = new LineDataSet(entityList,"直线一");
        LineData lineData=new LineData(lineDataSet);
        binding.linechart.setData(lineData);

        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BlueBoxingViewModel.class);
        // TODO: Use the ViewModel
    }

}
