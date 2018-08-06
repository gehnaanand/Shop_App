package com.example.gehna.shopapp;

public class Rating {
    int ratingScore;
    String suggestion;

    public Rating(){

    }

    public Rating(int ratingScore, String suggestion){
        this.ratingScore = ratingScore;
        this.suggestion = suggestion;

    }

    public int getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(int ratingScore) {
        this.ratingScore = ratingScore;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
}
