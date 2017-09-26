package com.example.emiya.myapp8;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class content extends AppCompatActivity implements ImageView.OnClickListener
{


    TextView titleArr, NameArr, locationArr, timeArr, IntroductionArr, NumberofpeopleArr,TV1,timeArrB;
    Button BT1;
    String[] pgroup, pfriendname;
    ListView LV1;
    String inputStr="",lat, lon;
    ImageView minMap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        titleArr = (TextView)findViewById(R.id.titleArr);
        timeArr = (TextView)findViewById(R.id.timeArr);
        NameArr = (TextView)findViewById(R.id.NameArr);
        locationArr = (TextView)findViewById(R.id.locationArr);
        IntroductionArr = (TextView)findViewById(R.id.IntroductionArr);
        NumberofpeopleArr = (TextView)findViewById(R.id.NumberofpeopleArr);
        timeArrB = (TextView)findViewById(R.id.timeArrB);
        BT1 = (Button)findViewById(R.id.BT1);
        TV1 = (TextView)findViewById(R.id.TV1);
        LV1 = (ListView)findViewById(R.id.LV1);
        minMap = (ImageView)findViewById(R.id.IV1);
        minMap.setOnClickListener(this);

        //抓自己名稱


        Intent intent =this.getIntent();
        titleArr.setText(intent.getStringExtra("ET1"));
        timeArr.setText(intent.getStringExtra("ET2"));
        NameArr.setText(intent.getStringExtra("ET3"));
        locationArr.setText(intent.getStringExtra("ET4"));
        IntroductionArr.setText(intent.getStringExtra("ET5"));
        NumberofpeopleArr.setText(intent.getStringExtra("ET6"));
        String id = intent.getStringExtra("ET7");
        lat = intent.getStringExtra("ET8");
        lon = intent.getStringExtra("ET9");
        timeArrB.setText(intent.getStringExtra("ET10"));


        new1 n2 = (new1)getApplicationContext();
        String urlString = n2.getconntion()+"/app_joinGET.php?group_id="+id;

        String inputStr = "";
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
                is.close();
                String str = sb.toString();
                //解開JSON Array  從JSON Object中 用KEY把東西個別拿出來


                JSONArray jArr;
                jArr = new JSONArray(str);
                pgroup = new String[jArr.length()];
                pfriendname = new String[jArr.length()];



                //分門別類
                for (int i = 0; i < jArr.length(); i++)
                {
                    pgroup[i] = new JSONArray(str).getJSONObject(i).getString("group_id");
                    pfriendname[i] = new JSONArray(str).getJSONObject(i).getString("friend_name");

                }
                ArrayAdapter<String> a1=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,pfriendname)
                {
                    @Override  //設定ListView的顏色
                    public View getView(int position, View convertView, ViewGroup parent) {
                        TextView textView = (TextView) super.getView(position, convertView, parent);
                        textView.setTextColor(Color.WHITE);
                        return textView;
                    }
                };
                LV1.setAdapter(a1);





        }
        catch (IOException e)
        {
            // writing exception to log
            e.printStackTrace();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        int a= pfriendname.length;
        TV1.setText(""+a);



    }


    public void join (View jn)
    {
        Intent intent =this.getIntent();
        String id = intent.getStringExtra("ET7");
        new1 n1 = (new1)getApplicationContext();

        //使用GET方式經由網址傳送參數
        String urlString = n1.getconntion()+"/app_joinADD.php?group_id="+id+"&username="+n1.getuser();


        //判斷參加人數
        int a= pfriendname.length;
        int b=Integer.parseInt(intent.getStringExtra("ET6"));
        String name01 = intent.getStringExtra("ET3");
        String name02 = n1.getuser();
        if (name01.equals(name02)){
            Toast.makeText(this,"你就是房主",Toast.LENGTH_SHORT).show();

        }
        else if(a > b-1)
        {
            Toast.makeText(this,"人數已滿", Toast.LENGTH_SHORT).show();
        }
        else
        {
            try
            {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();


                InputStream is = connection.getInputStream();
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                while ((inputStr = streamReader.readLine()) != null)
                {
                    Toast.makeText(this,inputStr,Toast.LENGTH_SHORT).show();
                }

            }
            catch(IOException e)
            {
                // writing exception to log
                e.printStackTrace();
            }

        }
        finish();
        startActivity(getIntent());

    }

    @Override
    public void onClick(View view) {


        Intent it = new Intent(this, MapsActivity.class);
        it.putExtra("ET9", lat);
        it.putExtra("ET8", lon);

        startActivity(it);
    }
}
