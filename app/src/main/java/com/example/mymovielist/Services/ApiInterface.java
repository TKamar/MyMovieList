package com.example.mymovielist.Services;

import com.example.mymovielist.Classes.Movie;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("3/movie/now-playing")
    Call<List<Movie>> getAllMovies();

    @GET("3/movie/top_rated")
    Call<List<Movie>> getTopMovies();


}
