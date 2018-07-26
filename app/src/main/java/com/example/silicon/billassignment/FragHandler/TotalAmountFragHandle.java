package com.example.silicon.billassignment.FragHandler;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.silicon.billassignment.R;



public class TotalAmountFragHandle extends Fragment {

    private OnFragmentInteractionListener fragListener;

    public TotalAmountFragHandle() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_total_amount, container, false);

        final EditText txt_total_amount = rootView.findViewById(R.id.txt_total_amount);
        Button btn_continue = rootView.findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    float totalAmount = Float.parseFloat(txt_total_amount.getText().toString());
                    if( totalAmount < 1) return;

                    SplitShareFragHandler splitShareFrag = new SplitShareFragHandler();
                    splitShareFrag.setTotalBill(totalAmount);

                    FragmentTransaction fragmentTransaction =  getActivity().getSupportFragmentManager().beginTransaction();
                    if(fragmentTransaction == null) return;
                    fragmentTransaction.add(R.id.fragment_container_content_view,splitShareFrag);

                    fragmentTransaction.commitAllowingStateLoss();
                    fragmentTransaction.remove(TotalAmountFragHandle.this);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            fragListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragListener = null;
    }

    public interface OnFragmentInteractionListener {
    }
}
