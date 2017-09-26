package com.example.emiya.myapp8;

import android.app.Application;

/**
 * Created by User on 2017/9/19.
 */

public class new1 extends Application
{
    private String user ,conntion;


    public void setuser(String user)
    {
        this.user = user;
    }


    public String getuser()
    {
        return user;
    }

    public void setconntion(String conntion)
    {
        this.conntion = conntion;
    }


    public String getconntion()
    {
        return conntion;
    }
}
