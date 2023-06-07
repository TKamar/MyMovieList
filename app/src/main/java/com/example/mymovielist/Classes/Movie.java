package com.example.mymovielist.Classes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {

    @SerializedName("original_title")
    private String title;

    @SerializedName("poster_path")
    private String image;

    @SerializedName("results")
    List<Movie> movieList;

    public Movie(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public Movie setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Movie setImage(String image) {
        this.image = image;
        return this;
    }
}
