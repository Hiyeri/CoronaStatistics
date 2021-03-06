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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class MainActivity<GetJSONTask> extends AppCompatActivity implements View.OnClickListener {
    public static String EXTRA_MESSAGE;
    private TextView coronaData;
    String url = "https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?";
    OkHttpClient client = new OkHttpClient();
    EditText selection;
    Button btnCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnCountry = (Button) findViewById(R.id.btnCountry);
        btnCountry.setOnClickListener((View.OnClickListener) this);

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

    @Override
    public void onClick(View view) {
        selection = (EditText) findViewById(R.id.selection);

        String inputCountry = selection.getText().toString().replaceAll("\\s+", "");
        //if (inputCountry != "") {
        inputCountry = inputCountry.substring(0, 1).toUpperCase() + inputCountry.substring(1).toLowerCase();
        //}
        /* Pass data to next activity
         * 'intent_currentActivity' is used when defining a new intent to transmit data
         * 'intent_lastActivity' is used when fetching transmitted data */
        Intent intent_main = new Intent(this, CoronaActivity.class);
        Log.d("Test", inputCountry);
        intent_main.putExtra("country", inputCountry);
        startActivity(intent_main);
    }

    /** Called when the user taps the Send button *//*
    public void sendMessage(View view) throws IOException {
        selection = (EditText) findViewById(R.id.selection);


        String inputCountry = selection.getText().toString().replaceAll("\\s+", "");
        //if (inputCountry != "") {
            inputCountry = inputCountry.substring(0, 1).toUpperCase() + inputCountry.substring(1).toLowerCase();
        //}
            *//* Pass data to next activity
             * 'intent_currentActivity' is used when defining a new intent to transmit data
             * 'intent_lastActivity' is used when fetching transmitted data *//*
            Intent intent_main = new Intent(this, CoronaActivity.class);
            Log.d("Test", inputCountry);
            intent_main.putExtra("country", inputCountry);
            startActivity(intent_main);

    }
*/
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
        private TextView confirmed;
        private TextView deaths;
        private TextView recovered;
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
            // Parsing JSON Object according to its structure
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
        }
    }
}
