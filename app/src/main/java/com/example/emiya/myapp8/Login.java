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

public class Login extends AppCompatActivity
{

    EditText ET1, ET2;
    private String user, pass;
    String str;
    new1 n1 ;
    new1 n2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ET1 = (EditText)findViewById(R.id.ET1);
        ET2 = (EditText)findViewById(R.id.ET2);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        n2 = (new1)getApplicationContext();
        n2.setconntion("http://125.224.11.37");



    }

    public void login(View ln)
    {
        user=ET1.getText().toString();
        pass=ET2.getText().toString();

        n1 = (new1)getApplicationContext();
        n1.setuser(user);


        String inputStr="";
        String urlString = n2.getconntion()+"/applogin.php?id="+user+"&pw="+pass;
        if (user.length()==0||pass.length()==0)
        {
            Toast.makeText(this,"請輸入帳號密碼!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            try
            {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();


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
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(this,"登入成功",Toast.LENGTH_SHORT).show();
                finish();
            }
            else {
                Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
            }
        }



    }

    public void register(View rr)
    {

        Intent intent = new Intent(Login.this,Register.class);
        startActivity(intent);
        finish();
    }
}
