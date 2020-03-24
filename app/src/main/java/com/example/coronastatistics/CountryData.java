package com.example.coronastatistics;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CountryData {
    private String responseBody;
    private String country;
    private String province;
    private String city;
    private String lastUpdate;
    private String confirmed;
    private String deaths;
    private String recovered;
    private List<CountryData> countryDataList;

    public CountryData(String responsyBody) {
        this.responseBody = responsyBody;
    }

    public CountryData(String country, String province, String city, String lastUpdate,
                       String confirmed, String deaths, String recovered) {
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

    public String getConfirmed() {
        return confirmed;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getRecovered() {
        return recovered;
    }

    protected List initializeData() {
        countryDataList = new ArrayList<>();

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
                this.confirmed = params.getString("confirmed");
                this.deaths = params.getString("deaths");
                this.recovered = params.getString("recovered");

                countryDataList.add(new CountryData(country, province, city, lastUpdate, confirmed, deaths, recovered));
            }
            Log.e("Data", String.valueOf(countryDataList));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return countryDataList;
    }
}
