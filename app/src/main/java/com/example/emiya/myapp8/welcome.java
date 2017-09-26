package com.example.emiya.myapp8;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //隱藏標題欄
        getSupportActionBar().hide();

        //執行畫面跳轉
        mHandler.sendEmptyMessageDelayed(GOTO_Login_ACTIVITY, 3000); //3秒跳轉
    }
    private static final int GOTO_Login_ACTIVITY = 0;
    private Handler mHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {

            switch (msg.what)
            {
                case GOTO_Login_ACTIVITY:
                    Intent intent = new Intent(welcome.this, Login.class);

                    //將原本Activity的換成MainActivity

                    startActivity(intent);
                    finish();
                    break;

                default:
                    break;
            }
        }

    };

}
