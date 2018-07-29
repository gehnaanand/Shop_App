package com.example.gehna.shopapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private EditText mail, password, cpassword;
    private Button registerButton;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mail = findViewById(R.id.emailET);
        password = findViewById(R.id.passwordET);
        cpassword = findViewById(R.id.confirmpasswordET);
        registerButton = findViewById(R.id.registerButton);
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mailStr = mail.getText().toString().trim();
                String passwordStr = password.getText().toString().trim();

                if(!password.getText().toString().equals(cpassword.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                }
                if (!TextUtils.isEmpty(mailStr) && !TextUtils.isEmpty(passwordStr)) {

                    firebaseAuth.createUserWithEmailAndPassword(mailStr, passwordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            } else {
                                Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {

                    if (TextUtils.isEmpty(mailStr) && TextUtils.isEmpty(passwordStr)){
                        Toast.makeText(RegisterActivity.this, "Enter Email and Password", Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(mailStr)){
                        Toast.makeText(RegisterActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(passwordStr)){
                        Toast.makeText(RegisterActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


    }

