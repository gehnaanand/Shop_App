package com.example.gehna.shopapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

public class ReviewActivity extends AppCompatActivity {
    int ratingScore = 3;
    String suggestions;
    DatabaseReference ratingRef;
    FirebaseAuth auth;
    Button submit;
    SmileRating smileRating;
    EditText suggestionsET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        final EditText suggestionET = findViewById(R.id.suggestionsET);
        ratingRef = FirebaseDatabase.getInstance().getReference("Ratings");
        auth = FirebaseAuth.getInstance();
        suggestionsET = findViewById(R.id.suggestionsET);
        final String user = auth.getCurrentUser().getUid();
        submit = findViewById(R.id.submit);
        smileRating = findViewById(R.id.smile_rating);







        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                switch (smiley) {
                    case SmileRating.BAD:
                        ratingScore = 2;
                        
                        break;
                    case SmileRating.GOOD:
                        ratingScore = 4;
                        break;
                    case SmileRating.GREAT:
                        ratingScore = 5;
                        break;
                    case SmileRating.OKAY:
                        ratingScore = 3;
                        break;
                    case SmileRating.TERRIBLE:
                        ratingScore = 1;
                        break;
                }


            }
        });


        smileRating.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
            @Override
            public void onRatingSelected(int level, boolean reselected) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suggestions = suggestionET.getText().toString();
                final Rating rating = new Rating(ratingScore,suggestions);
                DatabaseReference userRef = ratingRef.child(user);
                //DatabaseReference databaseReference = userRef.push();
                userRef.setValue(rating);
                suggestionET.setText("");
                smileRating.setSelectedSmile(BaseRating.GOOD, true);
                Toast.makeText(ReviewActivity.this, "Ratings Submitted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
