package com.example.myapplication.db;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.Main_1Activity;
import com.example.myapplication.SQL.MyData;
import com.example.myapplication.activity_dl_ZC_WJMA.loginActivity;

public class KeyBlueViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> Bluekey1_name,Bluekey2_name,Bluekey3_name,Bluekey4_name,Bluekey5_name,Bluekey6_name; //开关名称
    private MutableLiveData<String[][]> Bluekey; //开关状态变化发送的内容
    private MutableLiveData<String> JingDuTiao1_name,JingDuTiao2_name,JingDuTiao3_name;
    private MutableLiveData<Integer> JingDuTiao1_max,JingDuTiao2_max,JingDuTiao3_max;
    private MutableLiveData<Integer> JingDuTiao1_now,JingDuTiao2_now,JingDuTiao3_now;
    private MutableLiveData<boolean[]> Bluekey1_zt;
    private MutableLiveData<String[]> JingDuTiao_ap;
    private MutableLiveData<Context> TIM;
    public KeyBlueViewModel() {

    }

    public MutableLiveData<boolean[]> getBluekey1_zt(){
        if (Bluekey1_zt==null){
            Bluekey1_zt =new MutableLiveData<>();
            boolean[] booleans =new boolean[6];
            for(int a=0;a<6;a++){
                booleans[a]=false;
            }
            Bluekey1_zt.setValue(booleans);
        }
        return Bluekey1_zt;
    }
    public MutableLiveData<String[]> getJingDuTiao_ap(){
        if (JingDuTiao_ap==null){
            JingDuTiao_ap =new MutableLiveData<>();
            String[] booleans =new String[3];
            for(int a=0;a<3;a++){
                booleans[a]="";
            }
            JingDuTiao_ap.setValue(booleans);
        }
        return JingDuTiao_ap;
    }
    public MutableLiveData<Integer> getJingDuTiao1_max(){
        if (JingDuTiao1_max==null){
            JingDuTiao1_max =new MutableLiveData<>();
            JingDuTiao1_max.setValue(100);
        }
        return JingDuTiao1_max;
    }
    public MutableLiveData<Integer> getJingDuTiao2_max(){
        if (JingDuTiao2_max==null){
            JingDuTiao2_max =new MutableLiveData<>();
            JingDuTiao2_max.setValue(100);
        }
        return JingDuTiao2_max;
    }
    public MutableLiveData<Integer> getJingDuTiao3_max(){
        if (JingDuTiao3_max==null){
            JingDuTiao3_max =new MutableLiveData<>();
            JingDuTiao3_max.setValue(100);
        }
        return JingDuTiao3_max;
    }
    public MutableLiveData<Integer> getJingDuTiao1_now(){
        if (JingDuTiao1_now==null){
            JingDuTiao1_now =new MutableLiveData<>();
            JingDuTiao1_now.setValue(50);
        }
        return JingDuTiao1_now;
    }
    public MutableLiveData<Integer> getJingDuTiao2_now(){
        if (JingDuTiao2_now==null){
            JingDuTiao2_now =new MutableLiveData<>();
            JingDuTiao2_now.setValue(50);
        }
        return JingDuTiao2_now;
    }
    public MutableLiveData<Integer> getJingDuTiao3_now(){
        if (JingDuTiao3_now==null){
            JingDuTiao3_now =new MutableLiveData<>();
            JingDuTiao3_now.setValue(50);
        }
        return JingDuTiao3_now;
    }
    public MutableLiveData<String[][]> getBluekey(){ //开关状态消息
        if (Bluekey==null) {
            Bluekey = new MutableLiveData<>();
            String[][] S = new  String[6][2];
            for (int a=0;a<6;a++){
                for (int b=0;b<2;b++){
                    S[a][b] ="";
                }
            }
            Bluekey.setValue(S);
        }
        return Bluekey;
    }

    public MutableLiveData<String> getJingDuTiao1_name(){
        if (JingDuTiao1_name ==null){
            JingDuTiao1_name =new MutableLiveData<>();
            JingDuTiao1_name.setValue("可控进度条一");
        }
        return JingDuTiao1_name;
    }
    public MutableLiveData<String> getJingDuTiao2_name(){
        if (JingDuTiao2_name ==null){
            JingDuTiao2_name =new MutableLiveData<>();
            JingDuTiao2_name.setValue("可控进度条二");
        }
        return JingDuTiao2_name;
    }
    public MutableLiveData<String> getJingDuTiao3_name(){
        if (JingDuTiao3_name ==null){
            JingDuTiao3_name =new MutableLiveData<>();
            JingDuTiao3_name.setValue("可控进度条三");
        }
        return JingDuTiao3_name;
    }

    public MutableLiveData<String> getBluekey1_name(){
        if (Bluekey1_name==null) {
            Bluekey1_name = new MutableLiveData<>();
            Bluekey1_name.setValue("开关一");
        }
        return Bluekey1_name;
    }
    public MutableLiveData<String> getBluekey2_name(){
        if (Bluekey2_name==null) {
            Bluekey2_name = new MutableLiveData<>();
            Bluekey2_name.setValue("开关二");
        }
        return Bluekey2_name;
    }
    public MutableLiveData<String> getBluekey3_name(){
        if (Bluekey3_name==null) {
            Bluekey3_name = new MutableLiveData<>();
            Bluekey3_name.setValue("开关三");
        }
        return Bluekey3_name;
    }    public MutableLiveData<String> getBluekey4_name(){
        if (Bluekey4_name==null) {
            Bluekey4_name = new MutableLiveData<>();
            Bluekey4_name.setValue("开关四");
        }
        return Bluekey4_name;
    }    public MutableLiveData<String> getBluekey5_name(){
        if (Bluekey5_name==null) {
            Bluekey5_name = new MutableLiveData<>();
            Bluekey5_name.setValue("开关五");
        }
        return Bluekey5_name;
    }    public MutableLiveData<String> getBluekey6_name(){
        if (Bluekey6_name==null) {
            Bluekey6_name = new MutableLiveData<>();
            Bluekey6_name.setValue("开关六");
        }
        return Bluekey6_name;
    }


    public LiveData<String> getText() {
        return mText;
    }
}
