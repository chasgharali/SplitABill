package com.example.silicon.billassignment;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.silicon.billassignment.FragHandler.TotalAmountFragHandle;

public class MainActivity extends AppCompatActivity implements TotalAmountFragHandle.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            TotalAmountFragHandle totalAmountFragHandle = new TotalAmountFragHandle();
            FragmentTransaction fragmentTransaction =  this.getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_container_content_view, totalAmountFragHandle);
            fragmentTransaction.commitAllowingStateLoss();
        }catch (Exception ex){
                ex.printStackTrace();
        }
    }


}
