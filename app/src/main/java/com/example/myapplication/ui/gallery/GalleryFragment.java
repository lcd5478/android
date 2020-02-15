package com.example.myapplication.ui.gallery;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.adapter.ViewPagerAdapter;
import com.example.myapplication.db.BlueBoxingFragment;
import com.example.myapplication.db.LyAnjianFragment;
import com.example.myapplication.db.LyKaiguanFragment;
import com.example.myapplication.db.LySetFragment;
import com.example.myapplication.db.LyduihuaFragment;
import com.example.myapplication.ui.gallery.mqtt.mqtt_duihua.Fragment_mqtt_dialogue;
import com.example.myapplication.ui.gallery.mqtt.mqtt_key.Fragment_mqtt_key;
import com.example.myapplication.ui.gallery.mqtt.mqtt_set.Fragment_mqtt_set;
import com.example.myapplication.ui.gallery.mqtt.mqtt_switch.Fragment_mqtt_switch;
import com.example.myapplication.ui.gallery.mqtt.mqtt_waveform.Fragment_mqtt_waveform;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GalleryFragment extends Fragment {
    private    ImageView imageView_2;
    private GalleryViewModel galleryViewModel;
    private ViewPager viewPager;
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
        viewPager = root.findViewById(R.id.ViewPager_slidshow_mqtt);

        ViewPagerAdapter adapter =new ViewPagerAdapter(getChildFragmentManager());
        Fragment_mqtt_dialogue f2 = new Fragment_mqtt_dialogue();
        Fragment_mqtt_key f3 =new Fragment_mqtt_key();
        Fragment_mqtt_set f1 = new Fragment_mqtt_set();
        Fragment_mqtt_switch f4 =new Fragment_mqtt_switch();
        Fragment_mqtt_waveform f5 =new Fragment_mqtt_waveform();
        adapter.addFragment(f1);
        adapter.addFragment(f2);
        adapter.addFragment(f3);
        adapter.addFragment(f4);
        adapter.addFragment(f5);
        viewPager.setAdapter(adapter);

        final BottomNavigationView bottomNavigationView = root.findViewById(R.id.navigation_mqtt);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.lanya_shezhi_mqtt:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.lanya_duihua_mqtt:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.lanya_anjian_mqtt:
                        viewPager.setCurrentItem(2);
                        return true;
                    case R.id.lanya_kaiguan_mqtt:
                        viewPager.setCurrentItem(3);
                        return true;
                    case R.id.lanya_boxingtu_mqtt:
                        viewPager.setCurrentItem(4);
                }
                return true;
            }
        });

        /**

         * 滑动监听

         */
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            //页面在滑动中
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            //选中的页面
            @Override
            public void onPageSelected(int position) {
                //   bottomNavigationView.inflateMenu(position);
                bottomNavigationView.setSelectedItemId(bottomNavigationView.getMenu().getItem(position).getItemId());
            }
            //页面的状态
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
           return root;
    }

}