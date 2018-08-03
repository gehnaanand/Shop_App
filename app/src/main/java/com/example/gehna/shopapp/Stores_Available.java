package com.example.gehna.shopapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Stores_Available extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores__available);

        final ListView listView=findViewById(R.id.listview);
        final String clickedBrand,clickedColor,clickedStyle;

        Intent intent=getIntent();
        clickedBrand=intent.getStringExtra("Brand");
        clickedColor=intent.getStringExtra("Color");
        clickedStyle=intent.getStringExtra("Style");

        FirebaseDatabase database;
        DatabaseReference ref;
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("Documents");

        final ArrayList<String> list=new ArrayList<>();
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(Stores_Available.this,android.R.layout.simple_list_item_1,list);
        ref.addValueEventListener(new ValueEventListener() {
            owner owner1=new owner();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds1:dataSnapshot.getChildren()) {
                    for (DataSnapshot ds : ds1.getChildren()) {
                        owner1 = ds.getValue(owner.class);
                        //Log.d(" ", owner1.getBrand().toString());
                        String parentref=ds.getRef().getParent().getKey();
                        //Log.d("Parent= ",parentref);
                        if(clickedBrand.toLowerCase().toString().equals(owner1.getBrand().toString())&&clickedColor.toLowerCase().toString().equals(owner1.getColor()
                        .toString())&&clickedStyle.toLowerCase().toString().equals(owner1.getStyle().toString())) {
                           // list.add(" " + owner1.getBrand().toString());
                            list.add(" " + parentref);
                        }
                        else{
                            continue;
                        }
                    }
                    listView.setAdapter(adapter);
                    //listView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
