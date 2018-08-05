package com.example.gehna.shopapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<ShopItem> mShopList,colorlist,styleList,sizeList;
    private ShopAdapter mAdapter,colorAdapter,styleAdapter,sizeAdapter;
    private String clickedBrand,clickedColor,clickedStyle,clickedSize;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initList();

        //Spinner code
        Spinner spinnerShops=findViewById(R.id.spinner_shops);
        Spinner spinnerColor=findViewById(R.id.spinner_color);
        Spinner spinnerStyle=findViewById(R.id.spinner_style);
        Spinner spinnerSize = findViewById(R.id.spinner_size);

        mAdapter=new ShopAdapter(this,mShopList);
        colorAdapter=new ShopAdapter(this,colorlist);
        styleAdapter=new ShopAdapter(this,styleList);
        sizeAdapter=new ShopAdapter(this,sizeList);

        spinnerShops.setAdapter(mAdapter);
        spinnerColor.setAdapter(colorAdapter);
        spinnerStyle.setAdapter(styleAdapter);
        spinnerSize.setAdapter(sizeAdapter);

        spinnerShops.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ShopItem clickedItem =(ShopItem) parent.getItemAtPosition(position);
                clickedBrand=clickedItem.getShopName();
                //Toast.makeText(HomeActivity.this, clickedShopName +" Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ShopItem clickedItem=(ShopItem) parent.getItemAtPosition(position);
                clickedColor=clickedItem.getShopName();
                //Toast.makeText(HomeActivity.this, clickedShopName +" Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerStyle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ShopItem clickedItem=(ShopItem) parent.getItemAtPosition(position);
                clickedStyle=clickedItem.getShopName();
                //Toast.makeText(HomeActivity.this, clickedShopName +" Selected", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ShopItem clickedItem=(ShopItem)parent.getItemAtPosition(position);
                clickedSize=clickedItem.getShopName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Button submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,Stores_Available.class);
                intent.putExtra("Brand",clickedBrand);
                intent.putExtra("Color",clickedColor);
                intent.putExtra("Style",clickedStyle);
                intent.putExtra("Size",clickedSize);
                startActivity(intent);
            }
        });

    }

    private void initList(){
        mShopList=new ArrayList<>();
        mShopList.add(new ShopItem("Forever 21",R.mipmap.forever21_logo));
        mShopList.add(new ShopItem("H&M",R.mipmap.h_m));
        mShopList.add(new ShopItem("Zara",R.mipmap.zara_logo));
        mShopList.add(new ShopItem("Nautica",R.mipmap.nautica_logo));

        colorlist=new ArrayList<>();
        colorlist.add(new ShopItem("Black",R.mipmap.black));
        colorlist.add(new ShopItem("Brown",R.mipmap.brown));
        colorlist.add(new ShopItem("Grey",R.mipmap.grey));
        colorlist.add(new ShopItem("Lavender",R.mipmap.lavender));
        colorlist.add(new ShopItem("Magenta",R.mipmap.magenta));
        colorlist.add(new ShopItem("Red",R.mipmap.red));
        colorlist.add(new ShopItem("Turquoise Blue",R.mipmap.turq_blue));
        colorlist.add(new ShopItem("White",R.mipmap.white));
        colorlist.add(new ShopItem("Yellow",R.mipmap.yellow));

        styleList=new ArrayList<>();
        styleList.add(new ShopItem("Pant",R.mipmap.pant));
        styleList.add(new ShopItem("Shirts",R.mipmap.shirt));
        styleList.add(new ShopItem("T-Shirts",R.mipmap.tshirt));
        styleList.add(new ShopItem("Tops",R.mipmap.tops));
        styleList.add(new ShopItem("Shorts",R.mipmap.shorts));
        styleList.add(new ShopItem("Dress",R.mipmap.dress));

        sizeList=new ArrayList<>();
        sizeList.add(new ShopItem("XS",R.drawable.xs));
        sizeList.add(new ShopItem("S",R.drawable.s));
        sizeList.add(new ShopItem("M",R.drawable.m));
        sizeList.add(new ShopItem("L",R.drawable.l));
        sizeList.add(new ShopItem("XL",R.drawable.xl));
        sizeList.add(new ShopItem("XXL",R.drawable.xxl));
        sizeList.add(new ShopItem("XXXL",R.drawable.xxxl));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(this,HomeActivity.class));
            // Handle the camera action
        } else if (id == R.id.nav_search) {


        } else if (id == R.id.nav_popular) {

        } else if (id == R.id.nav_wishlist) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_logout) {

            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
