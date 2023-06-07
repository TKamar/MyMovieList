package com.example.mymovielist.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mymovielist.Interfaces.MovieListener;
import com.example.mymovielist.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> movieList;
    private static MovieListener movieListener;
    private Context context;

    public MovieAdapter(List<Movie> movieList, MovieListener movieListener, Context context) {
        this.movieList = movieList;
        this.movieListener = movieListener;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view, movieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        holder.movie_title.setText(movieList.get(position).getTitle());
        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+movieList.get(position).getImage())
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView movie_title;
        ImageView poster;
        MovieListener movieListener;

        public ViewHolder(@NonNull View itemView, MovieListener movieListener) {
            super(itemView);
            this.movieListener = movieListener;
            initObJ();
            findViews();
        }

        private void initObJ() {
            itemView.setOnClickListener(this);
        }

        private void findViews() {
            movie_title = itemView.findViewById(R.id.TXT_TITLE);
            poster = itemView.findViewById(R.id.IMG_POSTER_IMAGE);
        }

        @Override
        public void onClick(View v) {
            movieListener.onMovieClick(v, this.getAdapterPosition());
        }
    }
}
