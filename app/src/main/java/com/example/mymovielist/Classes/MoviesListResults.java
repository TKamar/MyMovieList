package com.example.mymovielist.Classes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesListResults {
    @SerializedName("results")
    public
    List<Movie> moviesResults;
}
