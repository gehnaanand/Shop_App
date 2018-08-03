package com.example.gehna.shopapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.acl.Owner;

public class Store_Register extends AppCompatActivity {

    EditText store_name,email,phone,password,confirm_pass;
    Button register;
    ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store__register);

        store_name=findViewById(R.id.store_name);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        password=findViewById(R.id.password);
        confirm_pass=findViewById(R.id.confirm_pass);
        //longitude=findViewById(R.id.longitude);
        //latitude=findViewById(R.id.latitude);
        register=findViewById(R.id.register);
        progressBar=findViewById(R.id.progressBar);

        auth=FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String password_str=password.getText().toString();
                final String email_str=email.getText().toString();
                final String cpassword=confirm_pass.getText().toString();
                final String store_name_str=store_name.getText().toString();


                if (TextUtils.isEmpty(email_str) && TextUtils.isEmpty(password_str)){
                    Toast.makeText(Store_Register.this, "Enter Email and Password", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(email_str)){
                    Toast.makeText(Store_Register.this, "Enter Email", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password_str)){
                    Toast.makeText(Store_Register.this, "Enter Password", Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(cpassword)){
                    Toast.makeText(Store_Register.this, "Confirm Password", Toast.LENGTH_SHORT).show();
                }else if(!password.getText().toString().equals(cpassword)){
                    Toast.makeText(Store_Register.this, "Password does not match", Toast.LENGTH_SHORT).show();
                } else if(password_str.length() < 6){
                    Toast.makeText(Store_Register.this, "Password must be minimum 6 characters!", Toast.LENGTH_SHORT).show();
                } else {
                    auth.createUserWithEmailAndPassword(email_str, password_str).addOnCompleteListener(Store_Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                        //owner owner1=new owner(store_name_str,password_str);
                        //Toast.makeText(Store_Register.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);

                        /*FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference users = database.getReference("users");
                        users.push().setValue(owner1);*/

                            if (!task.isSuccessful()) {
                                Toast.makeText(Store_Register.this, "Authentication failed." + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(Store_Register.this, Store_Login.class));
                                finish();
                            }
                        }
                    });

                }
            }
        });


    }


}

