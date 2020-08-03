package kapadokia.nyandoro.cinema;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;

import kapadokia.nyandoro.cinema.model.Movie;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);
    }



    public class MovieBackgroundTask extends AsyncTask<URL, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String result = null;

            try {
                result = ApiUtil.getJson(searchUrl);
            }catch (Exception e){
                Log.d("Error", e.toString());
            }

            return  result;
        }

        @Override
        protected void onPostExecute(String s) {

            if (s ==null){
                return;
            }else {

                ArrayList<Movie> movies = ApiUtil.getMoviesFromJson(s);

                textView.setText(movies.toString());
            }

        }
    }
}