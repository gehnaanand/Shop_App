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
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button buttonLogin;
    private TextView textviewRegister, textviewForgot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(MainActivity.this, HomeActivity.class));

        }
        setContentView(R.layout.activity_main);



        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.login);
        textviewRegister = findViewById(R.id.register);
        textviewForgot = findViewById(R.id.forgot);
        progressBar = findViewById(R.id.progressBar);

        //Get Firebase auth instance

        auth = FirebaseAuth.getInstance();

        textviewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

        textviewForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ForgotPassActivity.class));
            }
        });
        
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();
                
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(MainActivity.this, "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this, "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }



                //authenticate user

                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if(!task.isSuccessful()){
                            //if there is error
                            if(password.length() < 6){
                                inputPassword.setError("Password too short, enter minimum 6 characters!");
                            } else {
                                Toast.makeText(MainActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {

                                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                                startActivity(intent);

                        }
                    }
                });


            }
        });


    }
}
