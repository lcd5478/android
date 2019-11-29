package com.example.myapplication.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.MainActivity;
import com.example.myapplication.Main_1Activity;
import com.example.myapplication.R;
import com.example.myapplication.SQL.AccountDao;
import com.example.myapplication.SQL.jiami;

public class HomeFragment extends Fragment {

   // private HomeViewModel homeViewModel;
    private TextView textView_1,textView_2;
    private Button button_mian;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       // homeViewModel =
            //    ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);





        //textView_1.setText(string);
        //TextView textView = root.findViewById(R.id.text_home);
       // Button button_mian= root.findViewById(R.id.button_mian);
       // homeViewModel.getText().observe(this, new Observer<String>() {
          //  @Override
          //  public void onChanged(@Nullable String s) {
               // textView.setText("66666");
          //  }
     //   });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);





    }
}