package com.example.coronastatistics;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

/** Uses AsyncTask to create a task away from the main UI thread (to avoid
 * a NetworkOnMainThreadException). This task takes a
 * URL string and uses it to create an HttpUrlConnection.
 * */
class GetCoronaTask extends AsyncTask<String, Void, String> {
    private String url;
    CoronaCall coronaCall = new CoronaCall();

    public GetCoronaTask(String url) {
        this.url = url;
        Log.e("Constructor", url);
    }

    @Override
    protected String doInBackground(String... urls) {
        try {
            return coronaCall.sendCoronaRequest(url);
        } catch (IOException e) {
            return "Unable to retrieve data. URL may be invalid.";
        }
    }

    // onPostExecute displays the results of the doInBackgroud and also we
    // can hide progress dialog.
    @Override
    protected void onPostExecute(String result) {
        //
    }
}
