package com.example.coronastatistics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CoronaActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnMap;
    private TextView txtStats;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setText("Google Maps");
        btnMap.setOnClickListener((View.OnClickListener) this);

        new AsyncCoronaTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
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
        private List<CountryData> countryDataList;

        @Override
        protected void onPreExecute() {
            // txtStats.setText("Nichts");
        }

        @Override
        protected String doInBackground(String... urls) {
            Request request = new Request.Builder()
                    .url("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country=US")
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

        @Override
        protected void onPostExecute(String result) {
            // https://code.tutsplus.com/tutorials/getting-started-with-recyclerview-and-cardview-on-android--cms-23465
            CountryData countryData = new CountryData(responseBody);
            countryDataList = countryData.initializeData();

            RVAdapter adapter = new RVAdapter(countryDataList);
            recyclerView.setAdapter(adapter);
        }
    }

}
