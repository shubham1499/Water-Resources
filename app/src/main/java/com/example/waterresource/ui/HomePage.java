package com.example.waterresource.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.waterresource.R;

public class HomePage extends AppCompatActivity {
Button transcendToInputPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        transcendToInputPage = (Button)findViewById(R.id.btnGoToInputPage);
        transcendToInputPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, InputDataPage1.class));
            }
        });
    }
}
