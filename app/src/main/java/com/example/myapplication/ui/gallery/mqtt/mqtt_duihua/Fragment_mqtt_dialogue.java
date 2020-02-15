package com.example.myapplication.ui.gallery.mqtt.mqtt_duihua;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

public class Fragment_mqtt_dialogue extends Fragment {

    private FragmentMqttDialogueViewModel mViewModel;

    public static Fragment_mqtt_dialogue newInstance() {
        return new Fragment_mqtt_dialogue();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mqtt_dialogue_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FragmentMqttDialogueViewModel.class);
        // TODO: Use the ViewModel
    }

}
