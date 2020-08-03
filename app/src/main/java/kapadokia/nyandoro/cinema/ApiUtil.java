package kapadokia.nyandoro.cinema;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

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
}
