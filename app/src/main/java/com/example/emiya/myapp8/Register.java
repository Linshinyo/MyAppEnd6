package com.example.emiya.myapp8;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Register extends AppCompatActivity
{

    EditText userid, password, password1, phone, area;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        userid = (EditText)findViewById(R.id.userid);
        password = (EditText)findViewById(R.id.password);
        password1 = (EditText)findViewById(R.id.password1);
        phone = (EditText)findViewById(R.id.phone);
        area = (EditText)findViewById(R.id.area);
    }

    public void registers(View rs)
    {
        String inputStr="";
        String mid=userid.getText().toString();
        String mpw=password.getText().toString();
        String mpw2=password1.getText().toString();
        String mtel=phone.getText().toString();
        String maddr=area.getText().toString();

        if (mid.length()<4)
        {
            Toast.makeText(this,"帳號格式錯誤!",Toast.LENGTH_SHORT).show();
            return;
        }
        if (mpw.length()<6)
        {
            Toast.makeText(this,"密碼格式錯誤!",Toast.LENGTH_SHORT).show();
            return;
        }


        else
        {
            //使用GET方式經由網址傳送參數
            new1 n2 = (new1)getApplicationContext();
            String urlString = "http://1.168.33.119/app_register.php?id=" + mid + "&pw=" + mpw + "&pw2=" + mpw2 + "&telephone=" + mtel + "&address=" + maddr;


            try
            {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();


                InputStream is = connection.getInputStream();
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));


                StringBuilder sb = new StringBuilder();
                while ((inputStr = streamReader.readLine()) != null)
                {
                    sb.append(inputStr);
                }
                str = sb.toString();

            }
            catch(IOException e)
            {
                // writing exception to log
                e.printStackTrace();
            }

            if (str.matches("success"))
            {
                Toast.makeText(this,"註冊成功",Toast.LENGTH_SHORT).show();
                Intent it = new Intent(this, Login.class);
                startActivity(it);
                finish();
            }
            else
            {
                Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
            }
        }

    }
}
