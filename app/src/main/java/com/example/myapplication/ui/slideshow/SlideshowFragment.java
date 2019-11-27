package com.example.myapplication.ui.slideshow;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.Main_1Activity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.ViewPagerAdapter;
import com.example.myapplication.db.BlueBoxingFragment;
import com.example.myapplication.db.LyAnjianFragment;
import com.example.myapplication.db.LyKaiguanFragment;
import com.example.myapplication.db.LySetFragment;
import com.example.myapplication.db.LyduihuaFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import static androidx.navigation.Navigation.findNavController;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private ViewPager viewPager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_slideshow, container, false);


       // NavController navController = findNavController(root.this, R.id.nav_host_fragment);
       // NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
      //  NavigationUI.setupWithNavController(navigationView, navController);



         viewPager = root.findViewById(R.id.ViewPager_slidshow);

        ViewPagerAdapter adapter =new ViewPagerAdapter(getChildFragmentManager());
        LySetFragment f11 = new LySetFragment();
        LyduihuaFragment f2 =new LyduihuaFragment();
        LyAnjianFragment f3 = new LyAnjianFragment();
        LyKaiguanFragment f4 =new LyKaiguanFragment();
        BlueBoxingFragment f5 =new BlueBoxingFragment();
        adapter.addFragment(f11);
        adapter.addFragment(f2);
        adapter.addFragment(f3);
        adapter.addFragment(f4);
        adapter.addFragment(f5);
        viewPager.setAdapter(adapter);

         final BottomNavigationView bottomNavigationView = root.findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.lanya_shezhi:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.lanya_duihua:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.lanya_anjian:
                        viewPager.setCurrentItem(2);
                        return true;
                    case R.id.lanya_kaiguan:
                        viewPager.setCurrentItem(3);
                        return true;
                    case R.id.lanya_boxingtu:
                        viewPager.setCurrentItem(4);
                }
                return true;
            }
        });

        //setupViewPager();
        //viewPager =root.findViewById(R.id.navigation);
        //  try{

        //      Glide.with(this).load("https://pics6.baidu.com/feed/a9d3fd1f4134970af30485870e9b66cda6865dfe.jpeg?token=6a73353f0bbd6125dc1bc4e235cdb331&s=2DE6E8161C6178881E490CEF0300702E").into(mimage_wl);

        // }catch (Exception e){
        //   Log.d("************", ""+e.getMessage());
       //  }
      //
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



    public void setupViewPager(){
        ViewPagerAdapter adapter =new ViewPagerAdapter(getChildFragmentManager());
        LySetFragment f1 = new LySetFragment();
        LyduihuaFragment f2 =new LyduihuaFragment();
        LyAnjianFragment f3 = new LyAnjianFragment();
        LyKaiguanFragment f4 =new LyKaiguanFragment();
        adapter.addFragment(f1);
        adapter.addFragment(f2);
        adapter.addFragment(f3);
        adapter.addFragment(f4);
        viewPager.setAdapter(adapter);

        Fragment videoFragment = new LySetFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
      //  transaction.add(R.id.nav_host_fragment_and_fragment, videoFragment).commit();
      //  FragmentManager fm=getChildFragmentManager();
        //FragmentTransaction ft=fm.beginTransaction();
    //    if(!LySetFragment.isAdded()){
       // ft.add(R.id.viewPager, LySetFragment).commit();
        }






    }
