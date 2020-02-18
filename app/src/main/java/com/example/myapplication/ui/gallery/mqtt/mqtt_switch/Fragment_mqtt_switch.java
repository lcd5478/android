package com.example.myapplication.ui.gallery.mqtt.mqtt_switch;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.db.KeyBlueViewModel;

public class Fragment_mqtt_switch extends Fragment {

    private FragmentMqttSwitchViewModel mViewModel;

    public static Fragment_mqtt_switch newInstance() {
        return new Fragment_mqtt_switch();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mqtt_switch_fragment, container, false);


        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FragmentMqttSwitchViewModel.class);
        // TODO: Use the ViewModel
    }

}
