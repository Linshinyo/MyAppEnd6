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

public class Register extends AppCompatActivity
{

    EditText userid, password, password1, phone, area;
    String str, inputStr;
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
        if (!mpw.equals(mpw2)){
            Toast.makeText(this,"兩次密碼不同!",Toast.LENGTH_SHORT).show();
            return;
        }

        else
        {
            //使用GET方式經由網址傳送參數
            new1 n2 = (new1)getApplicationContext();
//            String urlString = n2.getconntion()+"/app_register.php?id=" + mid + "&pw=" + mpw + "&pw2=" + mpw2 + "&telephone=" + mtel + "&address=" + maddr;

            String data = "id=" + mid + "&pw=" + mpw + "&pw2=" + mpw2 + "&telephone=" + mtel + "&address=" + maddr;
            loginOfPost(data);

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
    public String loginOfPost(String data)
    {
        HttpURLConnection conn = null;
        try
        {
            // 連線網址
            URL mURL = new URL("http://220.134.78.67/app_register.php");
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
