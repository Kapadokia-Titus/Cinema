package kapadokia.nyandoro.cinema;

import android.net.Uri;

import java.net.URL;

public class ApiUtil {

    private static final String BASE_API_URL="http://api.themoviedb.org/3/movie/";
    private static final String KEY ="db7f024b088bcaa6db06ba9bf9e4e544";
    private static final  String API_KEY= "api_key";


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
}
