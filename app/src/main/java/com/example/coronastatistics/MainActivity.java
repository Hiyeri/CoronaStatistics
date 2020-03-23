package com.example.coronastatistics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;

public class MainActivity<GetJSONTask> extends AppCompatActivity {

    public static String EXTRA_MESSAGE;
    private TextView coronaData;
    String url = "https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country=Canada";
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) throws IOException {
        // new AsyncCoronaTask(url).execute();
        Intent intent = new Intent(this, CoronaActivity.class);
        startActivity(intent);
    }
}
