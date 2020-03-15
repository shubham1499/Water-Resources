package com.example.waterresource.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.waterresource.R;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class InputDataPage2 extends AppCompatActivity {

    Spinner pumpCapacityType;
    Button btnTranscendToInputPage3;
    Information info2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_page2);
        info2              = (Information) getIntent().getParcelableExtra("info1");

        pumpCapacityType = (Spinner)findViewById(R.id.spinnerPumpCapacityType);
        btnTranscendToInputPage3 = (Button)findViewById(R.id.btnGoToInputPage3);

        List<String> spinnerArraySourceType =  new ArrayList<String>();
        spinnerArraySourceType.add("Select");
        spinnerArraySourceType.add("1");
        spinnerArraySourceType.add("1.5");
        spinnerArraySourceType.add("3");
        spinnerArraySourceType.add("5");

        ArrayAdapter<String> adapterSourceType = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArraySourceType);
        adapterSourceType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pumpCapacityType.setAdapter(adapterSourceType);


        btnTranscendToInputPage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1;
                intent1 = new Intent(getApplicationContext(),InputDataPage3.class);
                String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                info2.setRecentUserId(FirebaseAuth.getInstance().getCurrentUser().getUid());
                info2.setCounter(1);
                info2.setTotalCount(1);
                info2.setDate(date);
                info2.setPumpCapacity(Double.parseDouble(pumpCapacityType.getSelectedItem().toString()));
                info2.setSummerDays(Integer.parseInt(((EditText)findViewById(R.id.edtSummerDays)).getText().toString()));
                info2.setSummerHours(Integer.parseInt(((EditText)findViewById(R.id.edtSummerHrs)).getText().toString()));
                info2.setWinterDays(Integer.parseInt(((EditText)findViewById(R.id.edtWinterDays)).getText().toString()));
                info2.setWinterHours(Integer.parseInt(((EditText)findViewById(R.id.edtWinterHrs)).getText().toString()));
                info2.setMonsoonDays(Integer.parseInt(((EditText)findViewById(R.id.edtMonsoonDays)).getText().toString()));
                info2.setMonsoonHours(Integer.parseInt(((EditText)findViewById(R.id.edtMonsoonHrs)).getText().toString()));

                intent1.putExtra("info2",info2);
                startActivity(intent1);
            }
        });

    }
}