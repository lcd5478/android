package com.example.myapplication.db;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

public class BlueBoxingViewModel extends ViewModel {
    MutableLiveData<List<Entry> >  entityList;


    public MutableLiveData<List<Entry>> getEntityList(){
        if(entityList==null){
            entityList = new MutableLiveData<List<Entry>>();
            List<Entry> ent = new ArrayList<>();
            ent.clear();
            entityList.setValue(ent);
        }
        return  entityList ;
    }
    // TODO: Implement the ViewModel
}
