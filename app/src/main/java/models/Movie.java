package models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;


@Parcel
public class Movie {        //has all the info/image to display for each movie entry
    String posterPath;
    String title;
    String overview;            //creating each and then getting from json file
    String backdropPath;
    double rating;
    int movieId;

    public Movie(){}     //empty constructor needed by the parceler library

    public Movie(JSONObject jsonObject) throws JSONException {     //constructor
        posterPath=jsonObject.getString("poster_path");
        title=jsonObject.getString("title");
        overview=jsonObject.getString("overview");
        backdropPath=jsonObject.getString("backdrop_path");
        rating=jsonObject.getDouble(("vote_average"));
        movieId=jsonObject.getInt("id");
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

    public double getRating(){
        return rating;
    }

    public int getMovieId() {
        return movieId;
    }
}
