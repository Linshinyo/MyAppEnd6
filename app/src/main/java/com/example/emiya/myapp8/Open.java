package com.example.emiya.myapp8;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;


public class Open extends AppCompatActivity implements OnClickListener, DatePickerDialog.OnDateSetListener, Spinner.OnItemSelectedListener
{

    Calendar c= Calendar.getInstance();
    EditText ET1, ET2, ET3, ET4, ET5;
    ImageView look_map;
    String lat, lon, b1;
    Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);
        ET1 = (EditText)findViewById(R.id.ET1);
        ET2 = (EditText)findViewById(R.id.ET2);
        ET3 = (EditText)findViewById(R.id.ET3);
        ET4 = (EditText)findViewById(R.id.ET4);
        ET5 = (EditText)findViewById(R.id.ET5);
        sp = (Spinner)findViewById(R.id.spinner);


        String[] timeBArr=new String[]{"00:00","01:00","02:00", "03:00","04:00","05:00", "06:00","07:00","08:00", "09:00","10:00","11:00", "12:00","13:00","14:00", "15:00","16:00","17:00", "18:00","19:00","20:00", "21:00","22:00","23:00"};
        ArrayAdapter<String> a1=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, timeBArr) {
            @Override  //設定ListView的顏色
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                return textView;
            }
        };
        sp.setAdapter(a1);
        sp.setOnItemSelectedListener(this);

        ET2.setOnClickListener(this);



        look_map=(ImageView)findViewById(R.id.imageview);
        look_map.setOnClickListener(this);



        Intent map_locat=getIntent();

        ET1.setText(map_locat.getStringExtra("ET1"));
        ET2.setText(map_locat.getStringExtra("ET2"));
        ET3.setText(map_locat.getStringExtra("a3"));
        ET4.setText(map_locat.getStringExtra("ET4"));
        ET5.setText(map_locat.getStringExtra("ET5"));
        lat=map_locat.getStringExtra("a4");
        lon=map_locat.getStringExtra("a5");
        //String a = map_locat.getStringExtra("a2");
        //new1 n2 = (new1)getApplicationContext();
        //n2.setlatitude(a);



    }


    //開團資訊傳送到information.class的設定
    public Void go(View v)
    {
        String inputStr="";
        String mtitle=ET1.getText().toString();
        String mtimeA=ET2.getText().toString();
        String mlocation=ET3.getText().toString();
        String mNumberofpeople=ET4.getText().toString();
        String mIntroduction=ET5.getText().toString();
        Intent intent = this.getIntent();
        //String user = intent.getStringExtra("ET9");
        new1 n2 = (new1)getApplicationContext();
        new1 n1 = (new1)getApplicationContext();



        //使用GET方式經由網址傳送參數
        String urlString = n2.getconntion()+"/app_productAdd.php?"+"title="+mtitle+"&timeA="+mtimeA+"&timeB="+b1+"&location="+mlocation+"&Numberofpeople="+mNumberofpeople+ "&Introduction="+mIntroduction+"&username="+n1.getuser()+"&latitude="+lat+"&longitude="+lon;


        if (mNumberofpeople.matches("")||mNumberofpeople.matches("0"))
        {
            Toast.makeText(this,"ERROR",Toast.LENGTH_SHORT).show();
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
            intent.setClass(Open.this, MainActivity.class);
            startActivity(intent);
            finish();

        }






//        Intent intent = new Intent();
//        intent.putExtra("ET1", ET1.getText().toString());
//        intent.putExtra("ET2", ET2.getText().toString());
//        intent.putExtra("ET3", ET3.getText().toString());




        return null;
    }

    @Override
    public void onClick(View view)
    {
        if(view==ET2)
        {
            new DatePickerDialog(this,this,c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
        }

        else
        {
            Intent it = new Intent(this, MapsActivity.class);
            it.putExtra("ET1", ET1.getText().toString());
            it.putExtra("ET2", ET2.getText().toString());
            it.putExtra("ET4", ET4.getText().toString());
            it.putExtra("ET5", ET5.getText().toString());
            startActivity(it);
            finish();
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2)
    {
        ET2.setText( i+ "/"+ (i1+1) +"/"+ i2  );

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        b1 = sp.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
