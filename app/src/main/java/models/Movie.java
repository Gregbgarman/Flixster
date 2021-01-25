package models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//basic java
public class Movie {        //has all the info/image to display for each movie entry
    String posterPath;
    String title;
    String overview;            //creating each and then getting from json file
    String backdropPath;

    public Movie(JSONObject jsonObject) throws JSONException {     //constructor
        posterPath=jsonObject.getString("poster_path");
        title=jsonObject.getString("title");
        overview=jsonObject.getString("overview");
        backdropPath=jsonObject.getString("backdrop_path");
    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException{
        List<Movie> movies=new ArrayList<>();
        for (int i=0;i<movieJsonArray.length();i++){                //this array has all movies
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));     //adding movies to List<Movie> movies
        }                           //passing movieJsonArray into Movie constructor to get the 3 data segments
        return movies;
    }

    public String getPosterPath() {

        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }

    public String getTitle() {

        return title;
    }

    public String getOverview() {

        return overview;
    }

    public String getBackdropPath(){
        return String.format("https://image.tmdb.org/t/p/w342/%s",backdropPath);
    }
}
