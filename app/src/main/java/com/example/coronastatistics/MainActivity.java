package com.example.coronastatistics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class MainActivity<GetJSONTask> extends AppCompatActivity {
    public static String EXTRA_MESSAGE;
    private TextView coronaData;
    String url = "https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country=Canada";
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new MainAsyncCoronaTask().execute();
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

    /** Called when the user taps the Send button */
    public void sendMessage(View view) throws IOException {
        // new AsyncCoronaTask(url).execute();
        Intent intent = new Intent(this, CoronaActivity.class);
        startActivity(intent);
    }

    /* Uses AsyncTask to create a task away from the main UI thread (to avoid
     * a NetworkOnMainThreadException). This task takes a
     * URL string and uses it to create an HttpUrlConnection.
     * */
    private class MainAsyncCoronaTask extends AsyncTask<String, Void, String> {
        private String url;
        private String responseBody;
        private OkHttpClient client = new OkHttpClient();
        private Response response = null;
        private List<CoronaCountryData> coronaCountryDataList;
        private TextView confirmed_nr;
        private TextView deaths_nr;
        private TextView recovered_nr;
        private TextView lastUpdated;

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... urls) {
            Request request = new Request.Builder()
                    .url("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?")
                    .get()
                    .addHeader("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", "7e6ace8a6dmsh86c04f7b84eada4p15df6fjsn9c141732c6b9")
                    .build();

            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Log.e("Corona", String.valueOf(response.isSuccessful()));

            try {
                responseBody = response.body().string();
                Log.e("Corona", responseBody);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.e("Confirmed", "hii");
            return response.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            // https://code.tutsplus.com/tutorials/getting-started-with-recyclerview-and-cardview-on-android--cms-23465
            CoronaCountryData coronaCountryData = new CoronaCountryData(responseBody);
            coronaCountryDataList = coronaCountryData.initializeData();

            // Get aggregated stats and set them on the text fields
            int sum_confirmed = 0;
            int sum_deaths = 0;
            int sum_recovered = 0;
            for(int i = 0; i < coronaCountryDataList.size(); i++) {
                sum_confirmed = sum_confirmed + coronaCountryDataList.get(i).getConfirmed();
                sum_deaths = sum_deaths + coronaCountryDataList.get(i).getDeaths();
                sum_recovered = sum_recovered + coronaCountryDataList.get(i).getRecovered();
            }
            DecimalFormat formatter = new DecimalFormat("###,###");

            confirmed_nr = (TextView)findViewById(R.id.confirmed_nr);
            confirmed_nr.setText(String.valueOf(formatter.format(sum_confirmed)));

            deaths_nr = (TextView)findViewById(R.id.deaths_nr);
            deaths_nr.setText(String.valueOf(formatter.format(sum_deaths)));

            recovered_nr = (TextView)findViewById(R.id.recovered_nr);
            recovered_nr.setText(String.valueOf(formatter.format(sum_recovered)));

            lastUpdated = (TextView)findViewById(R.id.lastUpdated);
            lastUpdated.setText(coronaCountryDataList.get(0).getLastUpdate());
        }
    }
}
