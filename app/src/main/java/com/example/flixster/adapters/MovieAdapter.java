package com.example.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.media.VolumeShaper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.R;

import java.util.List;

import models.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    Context context;        //used to inflate views
    List<Movie> movies;     //Movie defined in another class which has desired view info

    public MovieAdapter (Context context, List<Movie> movies){
        this.context=context;
        this.movies=movies;
    }

    //involves inflating a layout from xml and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView=LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    //populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get the movie at passed in position
        Movie movie=movies.get(position);           //class Movie defined in another class
        //Bind the movie into viewholder
        holder.bind(movie);             //must create the bind fxn
    }

    @Override
    public int getItemCount() {

        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
                                //define each view,retrieve them with id,and set(bind) them to something.
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvOverview=itemView.findViewById(R.id.tvOverview);
            ivPoster=itemView.findViewById(R.id.ivPoster);

        }

        public void bind(Movie movie) {     //binding each view here
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());

            String imageUrl;
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageUrl = movie.getBackdropPath();
            }
                else{
                    imageUrl=movie.getPosterPath();
                }
            Glide.with(context).load(imageUrl).into(ivPoster); //added glide dependency to gradle
            }

        }
    }


