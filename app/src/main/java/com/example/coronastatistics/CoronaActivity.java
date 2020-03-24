package com.example.coronastatistics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class CoronaActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnMap;
    private TextView txtStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setText("Google Maps");
        btnMap.setOnClickListener((View.OnClickListener) this);

        new AsyncCoronaTask().execute();
    }


    @Override
    public void onClick(View v) {
        String country = ("");
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }


    /** Uses AsyncTask to create a task away from the main UI thread (to avoid
     * a NetworkOnMainThreadException). This task takes a
     * URL string and uses it to create an HttpUrlConnection.
     * */
    private class AsyncCoronaTask extends AsyncTask<String, Void, String> {
        private String url;
        private String responseBody;
        private OkHttpClient client = new OkHttpClient();
        private Response response = null;
        private TextView txtStats = (TextView) findViewById(R.id.txtStats);

        @Override
        protected void onPreExecute() {
            txtStats.setText("Nichts");
        }

        @Override
        protected String doInBackground(String... urls) {
            Request request = new Request.Builder()
                    .url("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country=Canada")
                    .get()
                    .addHeader("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", "7e6ace8a6dmsh86c04f7b84eada4p15df6fjsn9c141732c6b9")
                    .build();

            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.e("Corona", String.valueOf(response.isSuccessful()));

            try {
                responseBody = response.body().string();
                Log.e("Corona", responseBody);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response.toString();
        }
        ;
        @Override
        protected void onPostExecute(String result) {

            // Parsing JSON Object according to its structure
            // https://www.tutorialspoint.com/android/android_json_parser.htm
            try {
                JSONObject jsonObject = new JSONObject(responseBody);

                // Getting JSON Node
                JSONObject data = jsonObject.getJSONObject("data");

                // Looping through all covid19Stats
                JSONArray covid19Stats = data.getJSONArray("covid19Stats");
                for (int i = 0; i < covid19Stats.length(); i++) {
                    JSONObject params = covid19Stats.getJSONObject(i);
                    String province = params.getString("province");
                    Log.e("Country", province);
                }


                // JSONArray covid19Stats = data.getJSONArray("covid19Stats");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            txtStats.setText(responseBody);
            Log.e("Henlo", "Henlo");
        }
    }

}
