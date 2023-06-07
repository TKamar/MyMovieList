package com.example.mymovielist.Services;

import com.example.mymovielist.Classes.Movie;
import com.example.mymovielist.Classes.MoviesListResults;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("3/movie/now_playing")
    Call<MoviesListResults> getAllMovies();

    @GET("3/movie/top_rated")
    Call<MoviesListResults> getTopMovies();


}
