package com.example.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.myapplication.activity_dl_ZC_WJMA.RegisterActivity;
import com.example.myapplication.adapter.ViewPagerAdapter;
import com.example.myapplication.db.LyAnjianFragment;
import com.example.myapplication.db.LyKaiguanFragment;
import com.example.myapplication.db.LySetFragment;
import com.example.myapplication.db.LyduihuaFragment;
import com.example.myapplication.ui.send.SendFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static androidx.navigation.Navigation.findNavController;

public class Main_1Activity extends AppCompatActivity  {

    private AppBarConfiguration mAppBarConfiguration;


    // private ImageView mimage_wl;
  //  LySetFragment f1 = new LySetFragment();
 //   LyduihuaFragment f2 = new LyduihuaFragment();
  //  LyAnjianFragment f3 = new LyAnjianFragment();
    //LyKaiguanFragment f4 = new LyKaiguanFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


       // View act =findViewById(R.id.action_settings);
       // act.setOnClickListener(new View.OnClickListener() {
           // @Override
          //  public void onClick(View v) {
             //
         //   }
      //  });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        Main_1Activity.this.setTitle("");


      //  try{

      //      Glide.with(this).load("https://pics6.baidu.com/feed/a9d3fd1f4134970af30485870e9b66cda6865dfe.jpeg?token=6a73353f0bbd6125dc1bc4e235cdb331&s=2DE6E8161C6178881E490CEF0300702E").into(mimage_wl);

      //  }catch (Exception e){
       //     Log.d("************", ""+e.getMessage());
      //  }
       // LySetFragment.OnFragmentInteractionListener(){}


        registerBoradcastReceiver();


    }
    private void registerBoradcastReceiver() {
        //注册监听
        IntentFilter stateChangeFilter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        IntentFilter connectedFilter = new IntentFilter(BluetoothDevice.ACTION_ACL_CONNECTED);
        IntentFilter disConnectedFilter = new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        registerReceiver(stateChangeReceiver, stateChangeFilter);
        registerReceiver(stateChangeReceiver, connectedFilter);
        registerReceiver(stateChangeReceiver, disConnectedFilter);
    }
    private BroadcastReceiver stateChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            TextView textView_xianshi =findViewById(R.id.xian_shi_zhuang_tai);
            String action = intent.getAction();
            if (action.equals(BluetoothDevice.ACTION_ACL_CONNECTED)) {
                //连接上了
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String name = device.getName();
                textView_xianshi.setText("蓝牙：已连接："+name);
            } else if (action.equals(BluetoothDevice.ACTION_ACL_DISCONNECTED)) {
                //蓝牙连接被切断
                textView_xianshi.setText("蓝牙：未连接");
                return;
            }
        }
    };





   // public void onFragmentInteraction_4(Uri uri) {
    //    //Toast.makeText(this,"交流,角楼",Toast.LENGTH_LONG).show();
  //  }



 //   @Override
   // public boolean onChildNavigateUp() {
   //     Fragment fragment = getChildFragmentManager().findFragmentById(R.id.nav_host_fragment_and_fragment);
       // return NavHostFragment.findNavController(fragment).navigateUp();
    //}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_1, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();


    }






    public void setData(String test)
    {
        Intent intent1 =new Intent();
        intent1.setClass(this, MainActivity.class);
        startActivity(intent1);
    }

    public void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        LySetFragment f1 = new LySetFragment();
        LyduihuaFragment f2 = new LyduihuaFragment();
        LyAnjianFragment f3 = new LyAnjianFragment();
        LyKaiguanFragment f4 = new LyKaiguanFragment();
        adapter.addFragment(f1);
        adapter.addFragment(f2);
        adapter.addFragment(f3);
        adapter.addFragment(f4);
        viewPager.setAdapter(adapter);
    }



}
