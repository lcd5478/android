package com.example.myapplication.activity_dl_ZC_WJMA;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class SplashActivity extends AppCompatActivity {
    Button buttonjingru;
    @SuppressLint("StringFormatMatches")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        buttonjingru =this.findViewById(R.id.button4);
        buttonjingru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Splash();
            }
        });
        if(!Permission_Active.Permissions_State(this))
        {
           if (!Permission_Active.Permissions_State(this))
           {
               this.finish();
               System.exit(0);
           }
        }


    }


    private void  Splash()
    {
        Intent intent=new Intent();
        intent.setClass(this,loginActivity.class);
        this.finish();
        startActivity(intent);

    }

}

