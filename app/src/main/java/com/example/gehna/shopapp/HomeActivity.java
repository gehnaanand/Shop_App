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
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<ShopItem> mShopList,colorlist,styleList;
    private ShopAdapter mAdapter,colorAdapter,styleAdapter;

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

        mAdapter=new ShopAdapter(this,mShopList);
        colorAdapter=new ShopAdapter(this,colorlist);
        styleAdapter=new ShopAdapter(this,styleList);

        spinnerShops.setAdapter(mAdapter);
        spinnerColor.setAdapter(colorAdapter);
        spinnerStyle.setAdapter(styleAdapter);

        spinnerShops.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ShopItem clickedItem =(ShopItem) parent.getItemAtPosition(position);
                String clickedShopName=clickedItem.getShopName();
                Toast.makeText(HomeActivity.this, clickedShopName +" Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ShopItem clickedItem=(ShopItem) parent.getItemAtPosition(position);
                String clickedShopName=clickedItem.getShopName();
                Toast.makeText(HomeActivity.this, clickedShopName +" Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerStyle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ShopItem clickedItem=(ShopItem) parent.getItemAtPosition(position);
                String clickedShopName=clickedItem.getShopName();
                Toast.makeText(HomeActivity.this, clickedShopName +" Selected", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initList(){
        mShopList=new ArrayList<>();
        mShopList.add(new ShopItem("Forever 21",R.drawable.forever21_logo));
        mShopList.add(new ShopItem("H&M",R.drawable.h_m));
        mShopList.add(new ShopItem("Zara",R.drawable.zara_logo));
        mShopList.add(new ShopItem("Nautica",R.drawable.nautica_logo));

        colorlist=new ArrayList<>();
        colorlist.add(new ShopItem("Black",R.drawable.black));
        colorlist.add(new ShopItem("Brown",R.drawable.brown));
        colorlist.add(new ShopItem("Grey",R.drawable.grey));
        colorlist.add(new ShopItem("Lavender",R.drawable.lavender));
        colorlist.add(new ShopItem("Magenta",R.drawable.magenta));
        colorlist.add(new ShopItem("Red",R.drawable.red));
        colorlist.add(new ShopItem("Turquoise Blue",R.drawable.turq_blue));
        colorlist.add(new ShopItem("White",R.drawable.white));
        colorlist.add(new ShopItem("Yellow",R.drawable.yellow));

        styleList=new ArrayList<>();
        styleList.add(new ShopItem("Pant",R.drawable.pant));
        styleList.add(new ShopItem("Shirts",R.drawable.shirt));
        styleList.add(new ShopItem("T-Shirts",R.drawable.tshirt));
        styleList.add(new ShopItem("Tops",R.drawable.tops));
        styleList.add(new ShopItem("Shorts",R.drawable.shorts));
        styleList.add(new ShopItem("Dress",R.drawable.dress));

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
