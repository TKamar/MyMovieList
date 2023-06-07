package com.example.mymovielist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieDisplay extends AppCompatActivity {

    private TextView movieTitle;
    private ImageView movieImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_display);
        findViews();
        initObj();
    }

    private void initObj() {
        movieTitle.setText(MainActivity.movieTitle);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500"+MainActivity.movieImg).into(movieImg);
    }

    private void findViews() {
        movieTitle = findViewById(R.id.TXT_MOVIES_TITLE_DISPLAY);
        movieImg = findViewById(R.id.IMG_POSTER_IMAGE_DISPLAY);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}