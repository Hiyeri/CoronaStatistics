package com.example.coronastatistics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

public class CoronaActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnMap;
    private TextView txtStats;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int i = 0;
        if(i == 0) {
            setContentView(R.layout.activity_corona_noprovince);
            Log.e("View", Integer.toString(i));
        } else {
            // setContentView(R.layout.test);
            Log.e("View", Integer.toString(i));
        }

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

    /* Uses AsyncTask to create a task away from the main UI thread (to avoid
     * a NetworkOnMainThreadException). This task takes a
     * URL string and uses it to create an HttpUrlConnection.
     * */
    private class AsyncCoronaTask extends AsyncTask<String, Void, String> {
        private String url;
        private String responseBody;
        private OkHttpClient client = new OkHttpClient();
        private Response response = null;
        private List<CoronaCountryData> coronaCountryDataList;

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... urls) {
            Request request = new Request.Builder()
                    .url("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country=Germany")
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
            CoronaCountryData coronaCountryData = new CoronaCountryData(responseBody);
            coronaCountryDataList = coronaCountryData.initializeData();

<<<<<<< HEAD
=======
            country = (TextView) findViewById(R.id.country);
            country.setText(coronaCountryDataList.get(0).getCountry());

            int sum_confirmed = 0;
            int sum_deaths = 0;
            int sum_recovered = 0;
            for(int i = 0; i < coronaCountryDataList.size(); i++) {
                sum_confirmed = sum_confirmed + coronaCountryDataList.get(i).getConfirmed();
                sum_deaths = sum_deaths + coronaCountryDataList.get(i).getDeaths();
            }

            String str_confirmed = "Confirmed \n" + String.valueOf(formatter.format(sum_confirmed));
            confirmed = (TextView) findViewById(R.id.confirmed);
            confirmed.setText(str_confirmed);

            String str_deaths = "Deaths \n" + String.valueOf(formatter.format(sum_deaths));
            deaths = (TextView) findViewById(R.id.deaths);
            deaths.setText(str_deaths);

            String str_recovered = "Recovered \n" + String.valueOf(formatter.format(sum_recovered));
            recovered = (TextView) findViewById(R.id.recovered);
            recovered.setText(str_recovered);

            lastUpdated = (TextView) findViewById(R.id.lastUpdated);
            lastUpdated.setText(coronaCountryDataList.get(0).getLastUpdate());

>>>>>>> parent of f702e67... Merge pull request #9 from Hiyeri/corona
            CoronaRVAdapter adapter = new CoronaRVAdapter(coronaCountryDataList);
            recyclerView.setAdapter(adapter);
        }
    }

}
