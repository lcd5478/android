package com.example.myapplication.activity_dl_ZC_WJMA;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Main_1Activity;
import com.example.myapplication.R;
import com.example.myapplication.SQL.AccountDao;
import com.example.myapplication.SQL.DatabaseHelper;
import com.example.myapplication.SQL.jiami;

public class loginActivity extends AppCompatActivity {
    Button btnlogin;  //登录按钮
    EditText editTextname,editTextpwd;//用户名 ， 密码
    TextView textViewWANGJI,textViewzhuce;
    CheckBox box_pwd,box_jizhu;
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnlogin = (Button) this.findViewById(R.id.button); //绑定按钮
        editTextname = (EditText) this.findViewById(R.id.editText7);//绑定用户名输入框
        editTextpwd = (EditText) this.findViewById(R.id.editText8);//绑定密码输入框
        box_pwd =findViewById(R.id.checkBox2);//绑定显示密码
        box_jizhu =findViewById(R.id.checkBox);//绑定记住密码
        btnlogin.setOnClickListener(new View.OnClickListener() { //添加按键监视器
         @Override
           public void onClick(View v) {
            login(); //调用登陆方法
        }
          });
        textViewWANGJI =(TextView) this.findViewById(R.id.textView4);
        textViewzhuce =(TextView) this.findViewById(R.id.textView5);
        textViewzhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZhuChe();
            }

        });

        box_pwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    editTextpwd.setInputType(InputType.TYPE_CLASS_TEXT);
                }else {
                    editTextpwd.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
        AccountDao dao = new AccountDao(loginActivity.this);
       int id= dao.getInsertid("remember_the_password");
       int flag= dao.getInsertflag("remember_the_password");

       String string_user=dao.getExtractuser(id);
       editTextname.setText(string_user);
       if (flag==255){
           String denglv_pwd =dao.getInsertpwd("remember_the_password");
           editTextpwd.setText(denglv_pwd);
           box_jizhu.setChecked(true);
       }
       else {
           editTextpwd.setText("");
       }


    }


    public static String name;

    private void  login() //登陆
    {
         name=editTextname.getText().toString();//获取用户名
        String Pwd =editTextpwd.getText().toString();//获取密码
        AccountDao dao = new AccountDao(loginActivity.this);
        String S =dao.getIncomepwd(name);
        if (S==null)
        {
            Toast.makeText(this,"用户名或密码错误！",Toast.LENGTH_LONG).show();//弹出失败对话框
            return;
        }

        String string=jiami.jiami_liangchi(Pwd);
        if(S.equals(string))//判断用户名和密码
        {   int flag,id;
            if(box_jizhu.isChecked()){
                 flag =255; //就是一个是否记住密码的标志位
            }else {
                flag =0;
            }
            id=dao.getExtractid(name);
            dao.setInsertData(id,flag,Pwd);
            Toast.makeText(this,"登录成功！",Toast.LENGTH_LONG ).show();//弹出登录成功对话框
            Intent intent =new Intent(this, Main_1Activity.class);
            this.finish();
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"用户名或密码错误！",Toast.LENGTH_LONG).show();//弹出失败对话框
        }

    }
    private void  ZhuChe()
    {
        Intent intent=new Intent();
        intent.setClass(this,RegisterActivity.class);
        startActivity(intent);
    }
    public void ShowToast_1(View view)
    {
        this.view = view;
        Toast.makeText(this,"密码忘记了，就重新注册吧！暂不提供找回密码哦！",Toast.LENGTH_LONG).show();
    }



}
