package com.example.coronastatistics;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

public class CoronaCall {
    OkHttpClient client = new OkHttpClient();
    InputStream is = null;

    public String sendCoronaRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "7e6ace8a6dmsh86c04f7b84eada4p15df6fjsn9c141732c6b9")
                .build();

        Response response = client.newCall(request).execute();

        Log.e("Corona", String.valueOf(response.isSuccessful()));
        Log.e("Ccorona", String.valueOf(response.body().string()));

        return response.toString();
    }
}
