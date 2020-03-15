package com.example.waterresource.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.waterresource.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class loginPage extends AppCompatActivity {

    FirebaseAuth mAuth;
    Button btn_login_here;
    TextView btn_register_here;
    EditText username_here,password_here;
    DatabaseReference ref;
    Context mycontext;
    String password_str;
    String email_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mAuth = FirebaseAuth.getInstance();
        ref   = FirebaseDatabase.getInstance().getReference();
        btn_register_here = (TextView) findViewById(R.id.txtsignup);
        btn_login_here = (Button)findViewById(R.id.btnlogin);

        username_here = (EditText)findViewById(R.id.username_text);
        password_here = (EditText)findViewById(R.id.password_text);
        username_here.setText("shubhamfargade99@gmail.com");
        password_here.setText("123456");

        btn_register_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginPage.this, signupPage.class));
            }
        });


        btn_login_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email_str = username_here.getText().toString().trim();
                password_str = password_here.getText().toString().trim();

                if (TextUtils.isEmpty(email_str)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password_str)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                decideCategoryLogin();
            }
        });
    }
    public void decideCategoryLogin()
    {
        mAuth.signInWithEmailAndPassword(email_str, password_str)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Authentication Failure.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(),"Authentication Sucessful",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), HomePage.class);
                            startActivity(i);
                            username_here.setText("");
                            password_here.setText("");
                        }
                    }
                });

    }

}