package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.example.flixster.adapters.MovieAdapter;
import models.Movie;
import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    //api key here
    public static final String TAG="MainActivity";

    List<Movie> movies;
    RecyclerView rvMovies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvMovies=findViewById(R.id.rvMovies);
        movies=new ArrayList<>();

        //adapter-create adapter,set it on recycler view, then set layout manager
        final MovieAdapter movieAdapter=new MovieAdapter(this,movies);
        rvMovies.setAdapter(movieAdapter);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

        AsyncHttpClient client=new AsyncHttpClient();
        client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {

                Log.d(TAG,"onSuccess");
                JSONObject jsonObject=json.jsonObject;

                try {
                    JSONArray results = jsonObject.getJSONArray("results");      //gets all movies from results section in JSON
                    Log.i(TAG,"Results: " + results.toString());
                    movies.addAll(Movie.fromJsonArray(results));        //adding all movies and notifying.
                    movieAdapter.notifyDataSetChanged();            //fromJsonArray defined in Movie.java
                    Log.i(TAG, "Movies: " + movies.size());
                } catch (JSONException e) {
                    Log.e(TAG,"Hit json exception");
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                    Log.d(TAG,"onFailure");
            }
        });
    }
}