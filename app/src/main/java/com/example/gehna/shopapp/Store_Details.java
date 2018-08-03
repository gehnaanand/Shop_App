package com.example.gehna.shopapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Store_Details extends AppCompatActivity {
    String brand,color,style;
    EditText ebrand,estyle,ecolor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store__details);

        //final ArrayList<String> brands=new ArrayList<String>();
        Button submit =findViewById(R.id.submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ebrand=findViewById(R.id.brand);
                estyle=findViewById(R.id.style);
                ecolor=findViewById(R.id.color);

                brand=ebrand.getText().toString();
                color=ecolor.getText().toString();
                style=estyle.getText().toString();

                Intent intent=getIntent();
                String store_name=intent.getStringExtra("Store Name");
                //DatabaseReference store_details= FirebaseDatabase.getInstance().getReference("Store_Details");
                //String store_name_ref=store_details.child(store_name).getKey();


                final owner clothing=new owner(brand,color,style);
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("Documents");
                //DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Document");
                DatabaseReference storeRef= ref.child(store_name);
                DatabaseReference databaseReference=storeRef.push();
                databaseReference.setValue(clothing);
                ebrand.setText("");
                estyle.setText("");
                ecolor.setText("");
                //storeRef.push().setValue(clothing);

            }
        });



    }
}
