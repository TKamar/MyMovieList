package com.example.mymovielist.Interfaces;

import com.example.mymovielist.Classes.Movie;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("")
    Call<List<Movie>> getAllMvies();

    @GET("")
    Call<List<Movie>> getTopMvies();


}
