package com.example.coronastatistics;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;

public class MainActivity<GetJSONTask> extends AppCompatActivity {

    public static String EXTRA_MESSAGE;
    private TextView coronaData;
    String url = "https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country=Canada";
    CoronaCall coronaCall = new CoronaCall();
    OkHttpClient client = new OkHttpClient();
    GetCoronaTask getCoronaTask = new GetCoronaTask(url);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetCoronaTask(url).execute();
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) throws IOException {
        Intent intent = new Intent(this, CoronaActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
