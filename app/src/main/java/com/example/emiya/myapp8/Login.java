package com.example.emiya.myapp8;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login extends AppCompatActivity
{

    EditText ET1, ET2;
    private String user, pass;
    String str, inputStr;
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
        n2.setconntion("http://220.134.78.67");



    }

    public void login(View ln)
    {
        user=ET1.getText().toString();
        pass=ET2.getText().toString();

        n1 = (new1)getApplicationContext();
        n1.setuser(user);



//        String urlString = n2.getconntion()+"/applogin.php;
        if (user.length()==0||pass.length()==0)
        {
            Toast.makeText(this,"請輸入帳號密碼!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            // 要POST的資料
            String data = "id=" + user + "&pw=" + pass;

            loginOfPost(data);

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
    public String loginOfPost(String data)
    {
        HttpURLConnection conn = null;
        try
        {
            // 連線網址
            URL mURL = new URL("http://220.134.78.67/applogin.php");
            // 使用HttpURLConnection連線
            conn = (HttpURLConnection) mURL.openConnection();
            //將方法設置為POST
            conn.setRequestMethod("POST");
            // 允許對伺服器輸出資料
            conn.setDoOutput(true);
            // 允許伺服器輸入資料
            conn.setDoInput(true);
            //串流輸出，將資料轉為Bytes格式
            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();
            //取得伺服器回傳的串流資料
            InputStream is = conn.getInputStream();
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            StringBuilder sb = new StringBuilder();
            while ((inputStr = streamReader.readLine()) != null) {
                sb.append(inputStr);
            }
            str = sb.toString();

            is.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
