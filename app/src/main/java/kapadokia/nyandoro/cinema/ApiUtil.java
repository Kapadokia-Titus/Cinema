package kapadokia.nyandoro.cinema;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kapadokia.nyandoro.cinema.model.Movie;

public class ApiUtil {

    private static final String BASE_API_URL="http://api.themoviedb.org/3/movie/";
    private static final String KEY ="db7f024b088bcaa6db06ba9bf9e4e544";
    private static final  String API_KEY= "api_key";
    private static final  String RESULTS= "results";


    //
    public static URL buildUrl(){

        URL url = null;
        Uri uri = Uri.parse(BASE_API_URL).buildUpon()
                .appendQueryParameter(API_KEY,KEY)
                .build();

        try {
            url = new URL(uri.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

        return url;
    }

    // connecting to the Api
    public static String getJson(URL url) throws IOException{

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            InputStream inputStream = connection.getInputStream();
            Scanner scanner =new Scanner(inputStream);

            //if we want to read everything we set the delimeter to \\A
            // the \\A is a pattern which is a regular expression.
            scanner.useDelimiter("\\A");

            // checking if the url has data
            // true if it has, and false if it does'nt
            boolean hasData = scanner.hasNext();

            // check if data exists, hence return null.
            if (hasData == true){
                return scanner.next();
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d("error", e.toString());
            return null;
        }

        //finally close the connection calling the disconnect() method
        finally {
            connection.disconnect();
        }

    }

    // fetching movies from the json
    public static ArrayList<Movie> getMoviesFromJson(String json){
        final String POPULARITY = "popularity";
        final String VOTE_COUNT ="vote_count";
        final String VIDEO = "video";
        final String POSTER_PATH ="poster_path";
        final String ID = "id";
        final String ADULT= "adult";
        final String BACKDROP_PATH = "backdrop_path";
        final String ORIGINAL_LANGUAGE ="original_language";
        final String ORIGINAL_TITLE ="original_title";
        final String GENRE_IDS = "genre_ids";
        final String TITLE ="title";
        final String VOTE_AVERAGE="vote_average";
        final String OVERVIEW ="overview";
        final String RELEASE_DATE ="release_date";
        Integer genreNum;
        ArrayList<Movie> movies = new ArrayList<>();

        try {

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray(RESULTS);

            int totalMovies = jsonArray.length();
            for (int i=0; i<totalMovies; i++){

                JSONObject movieJson =jsonArray.getJSONObject(i);

                //getting array of gender ids
                try {
                    genreNum = movieJson.getJSONArray(GENRE_IDS).length();
                }catch (Exception e){
                    genreNum =0;
                    e.printStackTrace();
                }

                //create an array of int that will contain the genres
                List<Integer> genres = new ArrayList<>();

                // looping through the genre array
                for (int j =0; j<genreNum; j++){
                     genres.add((Integer) movieJson.getJSONArray(GENRE_IDS).get(j));
                }

                Movie movie =new Movie(movieJson.getDouble(POPULARITY),
                             movieJson.getInt(VOTE_COUNT),
                        movieJson.getBoolean(VIDEO),
                        movieJson.getString(POSTER_PATH),
                        movieJson.getInt(ID),
                        movieJson.getBoolean(ADULT),
                        movieJson.getString(BACKDROP_PATH),
                        movieJson.getString(ORIGINAL_LANGUAGE),
                        movieJson.getString(ORIGINAL_TITLE),
                        genres,
                        movieJson.getString(TITLE),
                        movieJson.getDouble(VOTE_AVERAGE),
                        movieJson.getString(OVERVIEW),
                        movieJson.getString(RELEASE_DATE));

                movies.add(movie);
            }
        }catch (Exception e){

        }

        return movies; 
    }
}
