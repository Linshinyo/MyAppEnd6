package com.example.emiya.myapp8;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Emiya on 2017/9/15.
 */

public class profile extends Fragment implements Button.OnClickListener
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.profile, container, false);
        Button BT1 = rootView.findViewById(R.id.BT1);
        BT1.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onClick(View view)
    {
        Intent intent = new Intent(getActivity(), Login.class);
        getActivity().startActivity(intent);
        getActivity().finish();

    }
}
