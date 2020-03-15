package com.example.waterresource.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.waterresource.R;

import java.util.ArrayList;
import java.util.List;

public class InputDataPage1 extends AppCompatActivity {

    Spinner sourceType,ownershipType,seasonalityType,useOfSource;
    Button btnTranscendToInputPage2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_page_1);
        sourceType = (Spinner)findViewById(R.id.spinnerSourceType);
        ownershipType = (Spinner)findViewById(R.id.spinnerOwnershipType);
        seasonalityType = (Spinner)findViewById(R.id.spinnerSeasonalityType);
        useOfSource= (Spinner)findViewById(R.id.spinnerUseOfSource);
        btnTranscendToInputPage2 = (Button)findViewById(R.id.btnGoToInputPage2);


        List<String> spinnerArraySourceType =  new ArrayList<String>();
        spinnerArraySourceType.add("Select");
        spinnerArraySourceType.add("BoreWell(विहीर)");
        spinnerArraySourceType.add("DugWell(विहीर)");
        spinnerArraySourceType.add("Spring(झरा)");
        spinnerArraySourceType.add("Excavation(बांधकामाचा खड्डा)");
        spinnerArraySourceType.add("Rock_Exposure(उघडा पडलेला खडक)");

        List<String> spinnerArrayOwnerhipType =  new ArrayList<String>();
        spinnerArrayOwnerhipType.add("Select");
        spinnerArrayOwnerhipType.add("Private(खाजगी)");
        spinnerArrayOwnerhipType.add("Society");
        spinnerArrayOwnerhipType.add("Government(शासकीय)");

        List<String> spinnerArraySeasonalityType =  new ArrayList<String>();
        spinnerArraySeasonalityType.add("Select");
        spinnerArraySeasonalityType.add("Perennial(बारमाही)");
        spinnerArraySeasonalityType.add("Seasonal(हंगामी)");




        List<String> spinnerArrayUseOfSourceType =  new ArrayList<String>();
        spinnerArrayUseOfSourceType.add("Select");
        spinnerArrayUseOfSourceType.add("Drinking & domestic &  Gardening(पिण्यासासठी आणि घरगुती आणि बागेच्या वापरासाठी)");
        spinnerArrayUseOfSourceType.add("Drinking(पिण्यासाठी)");
        spinnerArrayUseOfSourceType.add("Gardening(बागेसाठी)");
        spinnerArrayUseOfSourceType.add("Commercial(व्यावसायिक)");
        spinnerArrayUseOfSourceType.add("Drinking & domestic(पिण्यासासठी आणि घरगुती वापरासाठी)");

        ArrayAdapter<String> adapterSourceType = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArraySourceType);
        adapterSourceType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        ArrayAdapter<String> adapterOwnerShipType = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArrayOwnerhipType);
        adapterOwnerShipType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        ArrayAdapter<String> adapterSeasonalityType = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArraySeasonalityType);
        adapterSeasonalityType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        ArrayAdapter<String> adapterUseOfSourceType = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArrayUseOfSourceType);
        adapterUseOfSourceType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sourceType.setAdapter(adapterSourceType);
        ownershipType.setAdapter(adapterOwnerShipType);
        seasonalityType.setAdapter(adapterSeasonalityType);
        useOfSource.setAdapter(adapterUseOfSourceType);


        btnTranscendToInputPage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=null;
                Information info = new Information();

                if(sourceType.getSelectedItemPosition()!=0) {
                    info.setTypeOfSource(sourceType.getSelectedItem().toString().split("\\(")[0]);
                }
                if(ownershipType.getSelectedItemPosition()!=0){
                    info.setOwnership(ownershipType.getSelectedItem().toString().split("\\(")[0]);
                }
                if(seasonalityType.getSelectedItemPosition()!=0){
                    info.setSeasonality(seasonalityType.getSelectedItem().toString().split("\\(")[0]);
                }
                if(useOfSource.getSelectedItemPosition()!=0){
                    info.setUseOfSource(useOfSource.getSelectedItem().toString().split("\\(")[0]);
                }

                if(sourceType.getSelectedItemPosition()==1 || sourceType.getSelectedItemPosition()==2){
                    //if selected dugWell or BoreWell then go to secondPage.
                    intent1 = new Intent(getApplicationContext(),InputDataPage2.class);
                    intent1.putExtra("info1",info);
                    intent1.putExtra("selectedSource",(int)sourceType.getSelectedItemPosition());
                }else{
                    //if selected other than dugwell or borewell then go to page3 where photo is clicked.
                    intent1 = new Intent(getApplicationContext(),InputDataPage3.class);
                    intent1.putExtra("info2",info);
                    intent1.putExtra("selectedSource",(int)sourceType.getSelectedItemPosition());
                }
                startActivity(intent1);
            }
        });

    }
}