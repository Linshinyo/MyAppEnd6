package com.example.emiya.myapp8;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Emiya on 2017/9/15.
 */

public class information extends Fragment implements ListView.OnItemClickListener
{

    ListView LV1;
    String[] ptitleArr,ptimeArr,pNameArr,plocationArr,pIntroductionArr, pNumberofpeopleArr,pgroup_id,platitude,plongitude,ptimeArrB;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.information, container, false);

        LV1 = rootView.findViewById(R.id.LV1);
        LV1.setOnItemClickListener(this);
        //開團的圖示設定
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent();
                intent.setClass(getActivity(), Open.class);
                startActivity(intent);
//                getActivity().finish();
            }
        });


        new1 n2 = (new1)getActivity().getApplicationContext();
        String urlString = n2.getconntion()+"/app_productGet.php";

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


                JSONArray jArr = new JSONArray(str);
                pNameArr = new String[jArr.length()];
                ptitleArr = new String[jArr.length()];
                ptimeArr = new String[jArr.length()];
                plocationArr = new String[jArr.length()];
                pNumberofpeopleArr = new String[jArr.length()];
                pIntroductionArr = new String[jArr.length()];
                pgroup_id = new String[jArr.length()];
                platitude = new String[jArr.length()];
                plongitude = new String[jArr.length()];
                ptimeArrB = new String[jArr.length()];


                //分門別類  pNameArr (團主), ptitleArr(標題), ptimeArr(時間), plocationArr(地點), pNumberofpeopleArr(人數), pIntroductionArr(內文)
                for (int i = 0; i < jArr.length(); i++)
                {
                    JSONObject o = jArr.getJSONObject(i);
                    pNameArr[i] = o.getString("username");
                    ptitleArr[i] = o.getString("title");
                    ptimeArr[i] = o.getString("timeA");
                    plocationArr[i] = o.getString("location");
                    pNumberofpeopleArr[i] = o.getString("Numberofpeople");
                    pIntroductionArr[i] = o.getString("Introduction");
                    pgroup_id[i] = o.getString("group_id");
                    platitude[i] = o.getString("latitude");
                    plongitude[i] = o.getString("longitude");
                    ptimeArrB[i] = o.getString("timeB");


                }

                ArrayAdapter<String> a1=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,ptitleArr)
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




        return rootView;

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        if (LV1.getItemAtPosition(i)!=null)
        {
            Intent intent = new Intent();
            intent.setClass(getActivity(), content.class);
            intent.putExtra("ET1", ptitleArr[i]);
            intent.putExtra("ET2", ptimeArr[i]);
            intent.putExtra("ET3", pNameArr[i]);
            intent.putExtra("ET4", plocationArr[i]);
            intent.putExtra("ET5", pIntroductionArr[i]);
            intent.putExtra("ET6", pNumberofpeopleArr[i]);
            intent.putExtra("ET7", pgroup_id[i]);
            intent.putExtra("ET8", platitude[i]);
            intent.putExtra("ET9", plongitude[i]);
            intent.putExtra("ET10", ptimeArrB[i]);
            getActivity().startActivity(intent);
        }

    }

}
