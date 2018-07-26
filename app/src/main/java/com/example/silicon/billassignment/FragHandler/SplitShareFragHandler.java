package com.example.silicon.billassignment.FragHandler;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.silicon.billassignment.Calculate;
import com.example.silicon.billassignment.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class SplitShareFragHandler extends Fragment {
    private Calculate calculateShareBill;

    public SplitShareFragHandler(){
        calculateShareBill = new Calculate();
    }
    public void setTotalBill(float totalBill) {
        this.calculateShareBill.setTotalAmount(totalBill);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_split_share, container, false);
        final Spinner numberOfPersonsDropDown = (Spinner)rootView.findViewById(R.id.totalPersonsSpinner);
        final SeekBar tipPercentageSeekbar = rootView.findViewById(R.id.fragment_calculate_tipPercentage_seekbar);
        final TextView seekbarPercentage = rootView.findViewById(R.id.fragment_calculate_seekpercentage_txt);
        final TextView amountEachPersonPayTxt = rootView.findViewById(R.id.fragment_calculate_each_pay_txt);
        final EditText tiptxt = rootView.findViewById(R.id.fragment_calculate_tip_txt);
        final TextView totalBillTxt = rootView.findViewById(R.id.fragment_calculate_total_bill_txt);

        String[] no_of_persons = {"1","2","3","4","5","6","7","8","9","10"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,no_of_persons);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numberOfPersonsDropDown.setAdapter(adapter);
        numberOfPersonsDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                calculateShareBill.setTotalPersons(position+1);
                amountEachPersonPayTxt.setText(calculateShareBill.calculateShareAmount()+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        tipPercentageSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(!fromUser)
                    return;
                seekbarPercentage.setText(progress+"%");
                tiptxt.setText(((((float)progress/100)*calculateShareBill.getTotalAmount()))+"");

                calculateShareBill.setTipAmount(Float.parseFloat(tiptxt.getText().toString().trim()));
                amountEachPersonPayTxt.setText(calculateShareBill.calculateShareAmount()+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        tiptxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                float tipTotalPercentage = 0;
                try{
                     tipTotalPercentage = Float.parseFloat(s.toString().trim())/calculateShareBill.getTotalAmount();
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }

                if (tipTotalPercentage <= 1){
                    tipPercentageSeekbar.setProgress((int)(tipTotalPercentage * 100));
                }
                else{
                    tipPercentageSeekbar.setProgress(100);
                }
                if(tipTotalPercentage <= 0){
                    tipTotalPercentage = 0;
                    tipPercentageSeekbar.setProgress(0);
                }
                seekbarPercentage.setText((tipTotalPercentage * 100)+"%");

                try{
                    calculateShareBill.setTipAmount(Float.parseFloat(s.toString().trim()));
                }catch (Exception ex){
                    ex.printStackTrace();
                }

                amountEachPersonPayTxt.setText(calculateShareBill.calculateShareAmount() +"");
            }
        });

        //totalBill/Integer.parseInt(numberOfPersonsDropDown.getSelectedItem().toString());
        amountEachPersonPayTxt.setText(calculateShareBill.calculateShareAmount()+"");
        totalBillTxt.setText("Total Bill: "+calculateShareBill.getTotalAmount());
        return rootView;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
