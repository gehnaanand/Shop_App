package com.example.gehna.shopapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        final MyListAdapter adapter=new MyListAdapter(Stores_Available.this,R.layout.custom_list_row,list);
        //final ListAdapter adapter=new ArrayAdapter<String>(Stores_Available.this,android.R.layout.simple_list_item_1,list);
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
                        if(clickedBrand.toLowerCase().toString().trim().equals(owner1.getBrand().toLowerCase().toString().trim())&&clickedColor.toLowerCase().toString().trim().equals(owner1.getColor().toLowerCase()
                        .toString().trim())&&clickedStyle.toLowerCase().toString().trim().equals(owner1.getStyle().toLowerCase().toString().trim())) {
                           // list.add(" " + owner1.getBrand().toString());
                            list.add(" " + parentref);
                        }
                        else{
                            continue;
                        }
                    }

                    //listView.setAdapter(adapter);
                }
                listView.setAdapter(adapter);
                if(list.isEmpty()){
                    list.add("Not available in any stores!");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private class MyListAdapter extends ArrayAdapter<String>{
        private int layout;
        public MyListAdapter(@NonNull Context context, int resource,ArrayList<String> list) {
            super(context, resource,list);
            layout=resource;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder mainViewHolder=null;
            if(convertView==null){
                LayoutInflater inflater=LayoutInflater.from(getContext());
                convertView=inflater.inflate(layout,parent,false);
                ViewHolder viewHolder=new ViewHolder();
                viewHolder.textView=convertView.findViewById(R.id.text);
                viewHolder.button=convertView.findViewById(R.id.maps);
                viewHolder.textView.setText(getItem(position));
                viewHolder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(Stores_Available.this,MapsActivity.class);
                        String store_name=(String)getItem(position);
                        Toast.makeText(Stores_Available.this, ""+getItem(position), Toast.LENGTH_SHORT).show();
                        intent.putExtra("Store Name",store_name);
                        //go to maps activity
                        startActivity(intent);
                    }
                });
                convertView.setTag(viewHolder);
            }
            else {
                mainViewHolder=(ViewHolder)convertView.getTag();
                mainViewHolder.textView.setText(getItem(position));
            }
            return convertView;
            //return super.getView(position, convertView, parent);
        }
    }
    public class ViewHolder{

        TextView textView;
        Button button;
    }
}
