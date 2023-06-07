package com.example.mymovielist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mymovielist.Classes.Movie;
import com.example.mymovielist.Classes.MovieAdapter;
import com.example.mymovielist.Classes.MoviesListResults;
import com.example.mymovielist.Services.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static String API_KEY = "https://api.themoviedb.org/3/movie/popular?api_key=0ca1b003e8550baee324f2d0413eb7ac";
    static String movieTitle;
    static String movieImg;

    List<Movie> movieList;
    RecyclerView recyclerView;
    MovieAdapter adapter;
    Button topMoviesBtn;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        movieList = new ArrayList<>();
        initObj();
        fetchMovies();

        topMoviesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TopMovies.class));
            }
        });

    }

    private void initObj() {
//        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new MovieAdapter(movieList, this::onMovieClick, this);
        recyclerView.setAdapter(adapter);
    }

    private void fetchMovies() {

        Call<MoviesListResults> moviesResults = RetrofitClient.getRetrofitClient().getAllMovies();
        moviesResults.enqueue(new Callback<MoviesListResults>() {
            @Override
            public void onResponse(Call<MoviesListResults> call, Response<MoviesListResults> response) {
                if(response.isSuccessful() && response.body() != null){
//                    List<Movie> resultMovies = response.body().moviesResults;
//                    for (int i = 0; i < resultMovies.size(); i++) {
//                    Movie tmpMovie = new Movie(resultMovies.get(i).getTitle(), resultMovies.get(i).getImage());
//                    movieList.add(tmpMovie);
//                    }
                    movieList.addAll(response.body().moviesResults);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MoviesListResults> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void findViews() {
        recyclerView = findViewById(R.id.RECYCLE_MOVIE_LIST);
        topMoviesBtn = findViewById(R.id.BTN_TOP_MOVIES);
    }

    public void onMovieClick(View view, int position){
        movieTitle = movieList.get(position).getTitle();
        movieImg = movieList.get(position).getImage();
        startActivity(new Intent(MainActivity.this, MovieDisplay.class));
    }
}