package com.example.myapplication.activity_dl_ZC_WJMA;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.SQL.AccountDao;
import com.example.myapplication.SQL.jiami;

public class RegisterActivity extends AppCompatActivity {
    Button buttonfanhui;
    EditText editTextuser,editTextpwd,editTextpwwd,editTextnick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        buttonfanhui =this.findViewById(R.id.button3);
        final AccountDao dao =new AccountDao(this);
        buttonfanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zhuchefanhui();
            }
        });
        editTextnick =findViewById(R.id.editText_nick);//昵称
        editTextuser = findViewById(R.id.editText_user);//用户名
        editTextpwd =findViewById(R.id.editText_pwd);//密码
        editTextpwwd =findViewById(R.id.editText_pwwd);//确认密码
        Button button =findViewById(R.id.button2_wanchengzhuc);
        editTextuser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(editTextuser.getText().toString().length()<6){
                    Toast.makeText(RegisterActivity.this,"用户名长度不得小于6",Toast.LENGTH_LONG).show();
                }
            }
        });

        editTextpwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(editTextpwd.getText().toString().length()<8){
                    Toast.makeText(RegisterActivity.this,"密码长度不得小于8",Toast.LENGTH_LONG).show();
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(editTextpwd.getText().toString().length()<8){
                    Toast.makeText(RegisterActivity.this,"密码长度不得小于8",Toast.LENGTH_LONG).show();
                    return;
                }
                if(!editTextpwd.getText().toString().equals(editTextpwwd.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"两次密码不同",Toast.LENGTH_LONG).show();
                    return;
                }
                if(editTextuser.getText().toString().length()<6){
                    Toast.makeText(RegisterActivity.this,"用户名长度不得小于6",Toast.LENGTH_LONG).show();
                    return;
                }
                if(editTextnick.getText().toString().length()<1){
                    Toast.makeText(RegisterActivity.this,"昵称不能为空",Toast.LENGTH_LONG).show();
                    return;
                }
                if(dao.getIncomepwd(editTextuser.getText().toString())!=null){
                    Toast.makeText(RegisterActivity.this,"这个用户名已经被注册了哦！",Toast.LENGTH_LONG).show();
                    return;
                }
              if(  dao.setadduser(editTextuser.getText().toString(),editTextpwd.getText().toString(),editTextnick.getText().toString())){
                 // System.out.println("注册成功");
                  Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_LONG).show();
                  Intent intent=new Intent();
                  intent.setClass(RegisterActivity.this,loginActivity.class);
                  RegisterActivity.this.finish();
                  startActivity(intent);
              }
            }
        });
    }
    private void  zhuchefanhui()
    {
        Intent intent=new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
    }
}
