package com.example.gehna.shopapp;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AboutActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private  SlideAdapter myadapter;
    private Button prev, next, finish;
    private LinearLayout nDotLayout;
    private TextView[] nDots;
    private int nCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        myadapter = new SlideAdapter(this);
        nDotLayout = (LinearLayout) findViewById(R.id.nDotLayout);
        prev = findViewById(R.id.prev);
        next = findViewById(R.id.next);
        finish = findViewById(R.id.finish);
        viewPager.setAdapter(myadapter);
        addDotsIndicator(0);
        viewPager.addOnPageChangeListener(viewListener);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(nCurrentPage + 1);

            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(nCurrentPage - 1);
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutActivity.this, HomeActivity.class));
            }
        });
    }



    public void addDotsIndicator(int position){
        nDots = new TextView[5];
        nDotLayout.removeAllViews();

        for(int i = 0; i < nDots.length; i++) {
            nDots[i] = new TextView(this);
            nDots[i].setText(Html.fromHtml("&#8226"));
            nDots[i].setTextSize(35);
            nDots[i].setTextColor(getResources().getColor(R.color.black));

            nDotLayout.addView(nDots[i]);





        }

        if(nDots.length > 0){
            nDots[position].setTextColor(getResources().getColor(R.color.dark_grey));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            nCurrentPage = position;

            if(position == 0){

                next.setEnabled(true);
                prev.setEnabled(false);
                finish.setEnabled(false);
                prev.setVisibility(View.INVISIBLE);
                finish.setVisibility(View.INVISIBLE);
                next.setVisibility(View.VISIBLE);
                next.setText("Next");
                prev.setText("");
            } else if (position == nDots.length - 1){
                finish.setEnabled(true);
                next.setEnabled(false);
                prev.setEnabled((true));
                prev.setVisibility(View.VISIBLE);
                next.setVisibility(View.INVISIBLE);
                finish.setVisibility(View.VISIBLE);
                finish.setText("Finish");
                prev.setText("Back");
            } else {
                next.setEnabled(true);
                prev.setEnabled((true));
                finish.setEnabled(false);
                prev.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                finish.setVisibility(View.INVISIBLE);

                next.setText("Next");
                prev.setText("Back");

            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
