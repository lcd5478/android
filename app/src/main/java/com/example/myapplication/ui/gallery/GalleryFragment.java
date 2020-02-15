package com.example.myapplication.ui.gallery;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

public class GalleryFragment extends Fragment {
    private    ImageView imageView_2;
    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        //final TextView textView = root.findViewById(R.id.text_gallery);

        //imageView_2 =root.findViewById(R.id.imageViem_wl_mqtt);


        //Glide.with(this).load("https://tc.sinaimg.cn/maxwidth.800/tc.service.weibo.com/p1_pstatp_com/81599b6b246e4f27a29a82409df8c542.jpg").into(imageView_2);

        //galleryViewModel.getText().observe(this, new Observer<String>() {
           // @Override
           // public void onChanged(@Nullable String s) {
             //   textView.setText(s);
          //  }
       // });


           return root;
    }

}