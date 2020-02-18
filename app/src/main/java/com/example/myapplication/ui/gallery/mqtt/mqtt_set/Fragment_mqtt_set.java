package com.example.myapplication.ui.gallery.mqtt.mqtt_set;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentLyKaiguanBinding;
import com.example.myapplication.databinding.FragmentMqttSetFragmentBinding;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Fragment_mqtt_set extends Fragment {

    private FragmentMqttSetViewModel mViewModel;
    private ScheduledExecutorService scheduler;
    private MqttClient client;
    private MqttConnectOptions options;
    private Handler handler;
    private FragmentMqttSetFragmentBinding binding;
    public static Fragment_mqtt_set newInstance() {
        return new Fragment_mqtt_set();
    }

    @SuppressLint("HandlerLeak")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

       // View root = inflater.inflate(R.layout.fragment_mqtt_set_fragment, container, false);
         binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mqtt_set_fragment, container, false);
        mViewModel = ViewModelProviders.of(this).get(FragmentMqttSetViewModel.class);
        mViewModel.getMqtt_connect_flag().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(mViewModel.getMqtt_connect_flag().getValue()){

                    binding.editText4.setEnabled(false);
                    binding.editText2.setEnabled(false);
                    binding.editText10.setEnabled(false);
                    binding.editText11.setEnabled(false);
                    binding.editText12.setEnabled(false);
                }
                else {

                    binding.editText4.setEnabled(true);
                    binding.editText2.setEnabled(true);
                    binding.editText10.setEnabled(true);
                    binding.editText11.setEnabled(true);
                    binding.editText12.setEnabled(true);
                }
            }
        });

        binding.setData(mViewModel);
        binding.setLifecycleOwner(this);
        handler = new Handler() {
            @SuppressLint("SetTextI18n")
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1: //开机校验更新回传
                        break;
                    case 2:  // 反馈回传

                        break;
                    case 3:  //MQTT 收到消息回传   UTF8Buffer msg=new UTF8Buffer(object.toString());


                        break;
                    case 30:  //连接失败
                        mViewModel.getMqtt_connect_flag().setValue(false);
                        Toast.makeText(Objects.requireNonNull(getView()).getContext(), "连接失败", Toast.LENGTH_SHORT).show();
                        break;
                    case 31:   //连接成功
                        mViewModel.getMqtt_connect_flag().setValue(true);
                        Toast.makeText(Objects.requireNonNull(getView()).getContext(), "连接成功", Toast.LENGTH_SHORT).show();
//                        try {
//                         //   client.subscribe(mqtt_sub_topic,1);
//                        } catch (MqttException e) {
//                            e.printStackTrace();
//                        }
                        break;
                    default:
                        break;
                }
            }
        };
        binding.button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Mqtt_disconnect();
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        });
        binding.button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mkin(getView());
            }
        });

//        scheduler = Executors.newSingleThreadScheduledExecutor();
//        scheduler.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                if (!client.isConnected()) {
//                    //Mqtt_connect();
//                    mViewModel.getMqtt_connect_flag().setValue(false);
//                    Toast.makeText(Objects.requireNonNull(getView()).getContext(), "连接以断开", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, 0 * 1000, 10 * 1000, TimeUnit.MILLISECONDS);
        return binding.getRoot();

    }

    public void mkin(View view)//连接方法
    {
        if (mViewModel.getHost_IP().getValue()==""){
            Toast.makeText(view.getContext(), "未填写IP", Toast.LENGTH_SHORT).show();

            return;
        }
        else if (mViewModel.getHost_Port().getValue()==null){
            Toast.makeText(view.getContext(), "未填写端口号", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(mViewModel.getMqtt_id().getValue()==null){
            Toast.makeText(view.getContext(), "未填写ID", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            if (mViewModel.getUserName().getValue()==null){
                mViewModel.getUserName().setValue("1234");
            }
            if(mViewModel.getPassWord().getValue()==null){
                mViewModel.getPassWord().setValue("1234");
            }
            Mqtt_init();

           // Mqtt_connect();
            startReconnect();
        }



    }
    private void Mqtt_disconnect() throws MqttException {
        try {
            if (client !=null){
                scheduler.shutdown();

                client.disconnect();
                mViewModel.getMqtt_connect_flag().setValue(false);
               // client.close();
            }

        }catch (MqttException e) {
            e.printStackTrace();

        }
    }

    public void Mqtt_init()
    {

        try {
            //host为主机名，test为clientid即连接MQTT的客户端ID，一般以客户端唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            client = new MqttClient(mViewModel.getHost().getValue(), mViewModel.getMqtt_id().getValue(),
                    new MemoryPersistence());
            //MQTT的连接设置
            options = new MqttConnectOptions();
            //设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(false);
            //设置连接的用户名
            options.setUserName(mViewModel.getUserName().getValue());
            //设置连接的密码
            options.setPassword(mViewModel.getPassWord().getValue().toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            //设置回调
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    //连接丢失后，一般在这里面进行重连
                    System.out.println("connectionLost----------");
                    //startReconnect();
                }
                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    //publish后会执行到这里
                    System.out.println("deliveryComplete---------"
                            + token.isComplete());
                }
                @Override
                public void messageArrived(String topicName, MqttMessage message)
                        throws Exception {
                    //subscribe后得到的消息会执行到这里面
                    System.out.println("messageArrived----------");
                    Message msg = new Message();
                    msg.what = 3;   //收到消息标志位
                    msg.obj = topicName + "---" + message.toString();
                    handler.sendMessage(msg);    // hander 回传
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void Mqtt_connect() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if(!(client.isConnected()) )  //如果还未连接
                    {
                        client.connect(options);
                        Message msg = new Message();
                        msg.what = 31;
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Message msg = new Message();
                    msg.what = 30;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }
    private void startReconnect() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (!client.isConnected()) {

                    Mqtt_connect();
//                    mViewModel.getMqtt_connect_flag().setValue(false);
//                    Toast.makeText(Objects.requireNonNull(getView()).getContext(), "连接以断开", Toast.LENGTH_SHORT).show();
                }
            }
        }, 0 * 1000, 10 * 1000, TimeUnit.MILLISECONDS);
    }
    private void publishmessageplus(String topic,String message2)
    {
        if (client == null || !client.isConnected()) {
            return;
        }
        MqttMessage message = new MqttMessage();
        message.setPayload(message2.getBytes());
        try {
            client.publish(topic,message);
        } catch (MqttException e) {

            e.printStackTrace();
        }
    }
}
