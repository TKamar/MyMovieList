package com.example.mymovielist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class TopMovies extends AppCompatActivity {

    List<Movie> movieList;
    RecyclerView recyclerView;
    MovieAdapter adapter;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_movies);
        movieList = new ArrayList<>();
        findViews();
        initObj();
        fetchMovies();
    }

    private void fetchMovies() {


        Call<MoviesListResults> moviesResults = RetrofitClient.getRetrofitClient().getTopMovies();
        moviesResults.enqueue(new Callback<MoviesListResults>() {

            @Override
            public void onResponse(Call<MoviesListResults> call, Response<MoviesListResults> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Movie> resultMovies = response.body().moviesResults;
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
                Toast.makeText(TopMovies.this, "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initObj() {
//        layoutManager = new LinearLayoutManager();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new MovieAdapter(movieList, this::onMovieClick, this);
        recyclerView.setAdapter(adapter);
    }

    private void findViews() {
        recyclerView = findViewById(R.id.TOP_MOVIES_RECYCLE);
    }

    public void onMovieClick(View view, int position){
        MainActivity.movieTitle = movieList.get(position).getTitle();
        MainActivity.movieImg = movieList.get(position).getImage();
        startActivity(new Intent(TopMovies.this, MovieDisplay.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}