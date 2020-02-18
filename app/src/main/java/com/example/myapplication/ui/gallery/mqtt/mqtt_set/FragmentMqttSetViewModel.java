package com.example.myapplication.ui.gallery.mqtt.mqtt_set;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FragmentMqttSetViewModel extends ViewModel {
    private MutableLiveData<String> host_IP,host;//IP地址和tcp
    private MutableLiveData<String> userName,passWord;//用户名和密码
    private MutableLiveData<String> mqtt_id,host_Port;//ID
    private MutableLiveData<Boolean> mqtt_connect_flag;
    private MutableLiveData<String> mqtt_sub_topic,mqtt_pub_topic;//订阅与发布
    private MutableLiveData<Integer> mqtt_sub_quality,mqtt_pub_quality;//订阅的消息质量和发布的消息质量和端口号
    public FragmentMqttSetViewModel() {

    }

    public MutableLiveData<Boolean> getMqtt_connect_flag() {
        if(mqtt_connect_flag==null){
            mqtt_connect_flag=new MutableLiveData<>();
            mqtt_connect_flag.setValue(false);
        }
        return mqtt_connect_flag;
    }

    public MutableLiveData<String> getHost_IP() {
        if (host_IP==null){
            host_IP = new MutableLiveData<>();

            host_IP.setValue("111.229.142.149");
        }
        return host_IP;
    }

    public MutableLiveData<String> getHost_Port() {
        if(host_Port==null){
            host_Port=new MutableLiveData<>();
            host_Port.setValue("1883");
        }
        return host_Port;
    }

    public MutableLiveData<String> getHost() {
        if(host==null)
            host=new MutableLiveData<>();
            String string = "tcp://"+getHost_IP().getValue()+":"+getHost_Port().getValue();
            host.setValue(string);

        return host;
    }

    public MutableLiveData<String> getMqtt_id() {
        if(mqtt_id==null){
            mqtt_id=new MutableLiveData<>();
            mqtt_id.setValue("");
        }
        return mqtt_id;
    }

    public MutableLiveData<String> getUserName() {
        if(userName==null){
            userName=new MutableLiveData<>();
            userName.setValue("");
        }
        return userName;
    }

    public MutableLiveData<String> getPassWord() {
        if(passWord==null){
            passWord=new MutableLiveData<>();
            passWord.setValue("");
        }
        return passWord;
    }

    public MutableLiveData<String> getMqtt_pub_topic() {
        if(mqtt_pub_topic==null){
            mqtt_pub_topic=new MutableLiveData<>();
            mqtt_pub_topic.setValue("");
        }
        return mqtt_pub_topic;
    }

    public MutableLiveData<String> getMqtt_sub_topic() {
        if (mqtt_sub_topic==null){
            mqtt_sub_topic=new MutableLiveData<>();
            mqtt_sub_topic.setValue("");
        }
        return mqtt_sub_topic;
    }

    public MutableLiveData<Integer> getMqtt_pub_quality() {
        if (mqtt_pub_quality==null){
            mqtt_pub_quality=new MutableLiveData<>();
            mqtt_pub_quality.setValue(0);
        }
        return mqtt_pub_quality;
    }

    public MutableLiveData<Integer> getMqtt_sub_quality() {
        if (mqtt_sub_quality==null){
            mqtt_sub_quality=new MutableLiveData<>();
            mqtt_sub_quality.setValue(0);
        }
        return mqtt_sub_quality;
    }



    // TODO: Implement the ViewModel
}
