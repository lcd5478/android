package com.example.myapplication;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
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

import java.io.File;
import java.io.FileNotFoundException;

import static android.bluetooth.BluetoothGattCharacteristic.PERMISSION_READ;
import static androidx.navigation.Navigation.findNavController;

public class Main_1Activity extends AppCompatActivity  {

    private AppBarConfiguration mAppBarConfiguration;

    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";
    private static final String CROP_IMAGE_FILE_NAME = "bala_crop.jpg";

     private ImageView mimage_wl;
    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;

    // 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
    private static int output_X = 100;
    private static int output_Y = 100;
    //改变头像的标记位
    private int new_icon=0xa3;
    private String mExtStorDir;
    private Uri mUriPath;
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

      mimage_wl =navigationView.findViewById(R.id.ne_header);
        //android:onClick=""
       // mimage_wl.setOnClickListener(new View.OnClickListener() {
          // @Override
         //  public void onClick(View v) {

         //  }
     //  });




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



    public void checkReadPermission(View V) {
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permission == PackageManager.PERMISSION_DENIED) {
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_READ);
        } else {
            choseHeadImageFromGallery();
        }
    }
        // 从本地相册选取图片作为头像
        private void choseHeadImageFromGallery() {
            // 设置文件类型    （在华为手机中不能获取图片，要替换代码）
        /*Intent intentFromGallery = new Intent();
        intentFromGallery.setType("image*//*");
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);*/

            Intent intentFromGallery = new Intent(Intent.ACTION_PICK, null);
            intentFromGallery.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {

        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplication(), "取消", Toast.LENGTH_LONG).show();
            return;
        }

        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                cropRawPhoto(intent.getData());
                break;

            case CODE_CAMERA_REQUEST:
                if (hasSdcard()) {
                    File tempFile = new File(
                            Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME);
//                    cropRawPhoto(Uri.fromFile(tempFile));
                    cropRawPhoto(getImageContentUri(tempFile));
                } else {
                    Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG)
                            .show();
                }

                break;

            case CODE_RESULT_REQUEST:
                /*if (intent != null) {
                    setImageToHeadView(intent);    //此代码在小米有异常，换以下代码
                }*/
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(mUriPath));
                    setImageToHeadView(intent,bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                break;
        }

        super.onActivityResult(requestCode, resultCode, intent);
    }

    /**
     * 裁剪原始的图片
     */
    public void cropRawPhoto(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        // 设置裁剪
        intent.putExtra("crop", "true");

        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        //intent.putExtra("return-data", true);

       // startActivityForResult(intent, CODE_RESULT_REQUEST); //直接调用此代码在小米手机有异常，换以下代码
       // String mLinshi = System.currentTimeMillis() + CROP_IMAGE_FILE_NAME;
        File mFile = new File(mExtStorDir, CROP_IMAGE_FILE_NAME);
//        mHeadCachePath = mHeadCacheFile.getAbsolutePath();
        try {
            if (mFile.exists()) {
                mFile.delete();
            }
            mFile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mUriPath = Uri.parse("file://" + mFile.getAbsolutePath());
        mUriPath = Uri.fromFile(mFile);
        //将裁剪好的图输出到所建文件中
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUriPath);
       // intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        //注意：此处应设置return-data为false，如果设置为true，是直接返回bitmap格式的数据，耗费内存。设置为false，然后，设置裁剪完之后保存的路径，即：intent.putExtra(MediaStore.EXTRA_OUTPUT, uriPath);
//        intent.putExtra("return-data", true);
      //  intent.putExtra("return-data", true);
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivityForResult(intent, CODE_RESULT_REQUEST);
    }
    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }
    public Uri getImageContentUri(File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[] { MediaStore.Images.Media._ID },
                MediaStore.Images.Media.DATA + "=? ",
                new String[] { filePath }, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }



    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private void setImageToHeadView(Intent intent,Bitmap b) {
        /*Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            headImage.setImageBitmap(photo);
        }*/
        try {
            if (intent != null) {
               // imageView_img
//                Bitmap bitmap = imageZoom(b);//看个人需求，可以不压缩
                mimage_wl.setImageBitmap(b);
                //mimage_wl.setImageDrawable(getResources().getDrawable(R.drawable.icon));
//                long millis = System.currentTimeMillis();
                /*File file = FileUtil.saveFile(mExtStorDir, millis+CROP_IMAGE_FILE_NAME, bitmap);
                if (file!=null){
                    //传递新的头像信息给我的界面
                    Intent ii = new Intent();
                    setResult(new_icon,ii);
                    Glide.with(this).load(file).apply(RequestOptions.circleCropTransform())
//                                .apply(RequestOptions.fitCenterTransform())
                            .apply(RequestOptions.placeholderOf(R.mipmap.user_logo)).apply(RequestOptions.errorOf(R.mipmap.user_logo))
                            .into(mIvTouxiangPersonal);
//                uploadImg(mExtStorDir,millis+CROP_IMAGE_FILE_NAME);
                    uploadImg(mExtStorDir,millis+CROP_IMAGE_FILE_NAME);
                }*/

            }
        } catch (Exception e) {
            Log.e("888888", "setImageToHeadView: "+e );
        }
    }


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
