package com.example.coronastatistics;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CoronaCountryData {
    private String responseBody;
    private String country;
    private String province;
    private String city;
    private String lastUpdate;
    private int confirmed;
    private int deaths;
    private int recovered;
    private List<CoronaCountryData> coronaCountryDataList;

    public CoronaCountryData(String responsyBody) {
        this.responseBody = responsyBody;
    }

    public CoronaCountryData(String country, String province, String city, String lastUpdate,
                             int confirmed, int deaths, int recovered) {
        this.country = country;
        this.province = province;
        this.city = city;
        this.lastUpdate = lastUpdate;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
    }

    public String getCountry() {
        return country;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    protected List initializeData() {
        coronaCountryDataList = new ArrayList<>();

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
                this.country = params.getString("country");
                this.province = params.getString("province");
                this.city = params.getString("city");
                this.lastUpdate = params.getString("lastUpdate");
                this.confirmed = params.getInt("confirmed");
                this.deaths = params.getInt("deaths");
                this.recovered = params.getInt("recovered");

                coronaCountryDataList.add(new CoronaCountryData(country, province, city, lastUpdate, confirmed, deaths, recovered));
            }
            Log.e("Data", String.valueOf(coronaCountryDataList));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return coronaCountryDataList;
    }
}
