package com.example.waterresource.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

import static com.google.firebase.auth.FirebaseAuth.getInstance;

public class signupPage extends AppCompatActivity {
    EditText email,password,cpassword,firstname,lastname,mobilenumber;
    String   category=null;
    Button btn_register_here;
    private FirebaseAuth mAuth;
    private DatabaseReference reference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        firstname=(EditText)findViewById(R.id.txtfname);
        lastname=(EditText)findViewById(R.id.txtlname);
        mobilenumber=(EditText)findViewById(R.id.txtnumber);
        email =(EditText)findViewById(R.id.txtid);
        password  = (EditText)findViewById(R.id.txtpassword);
        cpassword = (EditText)findViewById(R.id.txtconfirmPassword);
        btn_register_here = (Button)findViewById(R.id.btnSignup);

        progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setMessage("Registration In Process...");

        mAuth = FirebaseAuth.getInstance();
        btn_register_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emailstr      =(email).getText().toString().trim();
                final String passwordstr   =(password).getText().toString().trim();
                final String conpasswordstr=(cpassword).getText().toString().trim();
                final String fname=(firstname).getText().toString().trim();
                final String lname=(lastname).getText().toString().trim();
                final String mobileno=(mobilenumber).getText().toString().trim();
                if (TextUtils.isEmpty(emailstr)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mobileno)) {
                    Toast.makeText(getApplicationContext(), "Enter mobile number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passwordstr)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (passwordstr.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!passwordstr.equals(conpasswordstr)){
                    Toast.makeText(getApplicationContext(), "Both the Passwords should be same!", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(emailstr, passwordstr)
                        .addOnCompleteListener(signupPage.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(signupPage.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    reference = FirebaseDatabase.getInstance().getReference().child("Users");
                                    (reference.child(getInstance().getUid())).child("firstname").setValue(fname);
                                    (reference.child(getInstance().getUid())).child("lastname").setValue(lname);
                                    (reference.child(getInstance().getUid())).child("email").setValue(emailstr);
                                    (reference.child(getInstance().getUid())).child("mobileno").setValue(mobileno);
                                    Toast.makeText(signupPage.this, "Registration Sucessful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), loginPage.class));
                                    finish();
                                }
                            }
                        });




            }
        });
    }
}