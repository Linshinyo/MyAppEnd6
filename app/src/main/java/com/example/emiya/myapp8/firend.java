package com.example.emiya.myapp8;

import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
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

/**
 * Created by Emiya on 2017/9/15.
 */

public class firend extends Fragment
{
    ListView friendlist;
    String[] pFriendid;
    EditText friendname;
    TextView show;
    String str1;
    RelativeLayout RL;
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        rootView = inflater.inflate(R.layout.firend, container, false);

        LoadFriendList();


        //加好友按鈕
        Button fab = (Button) rootView.findViewById(R.id.button2);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String fn=friendname.getText().toString();
                String inputStr="";
                new1 n1 = (new1) getActivity().getApplicationContext();
                //使用GET方式經由網址傳送參數

                String urlString =n1.getconntion()+"/app_friendADD.php?memberid="+n1.getuser()+"&friendid="+fn;



                try
                {
                    URL url = new URL(urlString);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();


                    InputStream is = connection.getInputStream();
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                    StringBuilder sb = new StringBuilder();

                    while ((inputStr = streamReader.readLine()) != null) {

                        sb.append(inputStr);
                    }
                    str1 = sb.toString();
                    Toast.makeText(getActivity(),str1,Toast.LENGTH_SHORT).show();

                }
                catch (IOException e)
                {
                    // writing exception to log
                    e.printStackTrace();
                }

                LoadFriendList();

            }


        });

        return rootView;

    }

    private void LoadFriendList()
    {
        friendlist = rootView.findViewById(R.id.LV2);
        friendname = rootView.findViewById(R.id.editText);
        show = rootView.findViewById(R.id.textView4);
        new1 n2 = (new1)getActivity().getApplicationContext();
        new1 n1 = (new1)getActivity().getApplicationContext();
        String urlString = n2.getconntion()+"/app_friendGET.php?memberid="+n1.getuser();
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
            pFriendid = new String[jArr.length()];

            //分門別類  pFriendid (好友),
            for (int i = 0; i < jArr.length(); i++)
            {
                pFriendid[i] = jArr.getJSONObject(i).getString("friendid");

            }
            if(pFriendid.length==0)
            {

                pFriendid=new String[]{"你沒有朋友!!!"};

            }
            ArrayAdapter<String> a1=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,pFriendid)
            {
                @Override  //設定ListView的顏色
                public View getView(int position, View convertView, ViewGroup parent) {
                    TextView textView = (TextView) super.getView(position, convertView, parent);
                    textView.setTextColor(Color.WHITE);
                    return textView;
                }
            };
            friendlist.setAdapter(a1);

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
    }


}
