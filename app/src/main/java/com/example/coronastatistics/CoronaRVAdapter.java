package com.example.coronastatistics;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

public class CoronaRVAdapter extends RecyclerView.Adapter<CoronaRVAdapter.PersonViewHolder>{
    List<CoronaCountryData> coronaCountryData;

    public CoronaRVAdapter(List<CoronaCountryData> coronaCountryData) {
        this.coronaCountryData = coronaCountryData;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_view, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int i) {
        DecimalFormat formatter = new DecimalFormat("###,###");

        // Linebreak if number is too big
        if (coronaCountryData.get(i).getConfirmed() > 10000 || coronaCountryData.get(i).getDeaths() > 10000 ||
        coronaCountryData.get(i).getRecovered() > 10000) {
            String str_confirmed = "Confirmed: \n" + String.valueOf(formatter.format(coronaCountryData.get(i).getConfirmed()));
            holder.confirmed.setText(str_confirmed);
            String str_death = "Deaths: \n" + String.valueOf(formatter.format(coronaCountryData.get(i).getDeaths()));
            holder.death.setText(str_death);
        } else {
            String str_confirmed = "Confirmed: " + String.valueOf(formatter.format(coronaCountryData.get(i).getConfirmed()));
            holder.confirmed.setText(str_confirmed);
            String str_death = "Deaths: " + String.valueOf(formatter.format(coronaCountryData.get(i).getDeaths()));
            holder.death.setText(str_death);
        }

        holder.city.setText(coronaCountryData.get(i).getCity());
        holder.province.setText(coronaCountryData.get(i).getProvince());

        String str_recovered = "Recovered: " + String.valueOf(formatter.format(coronaCountryData.get(i).getRecovered()));
        holder.recovered.setText(str_recovered);

        /* // If there is no province, display country instead
        if(!coronaCountryData.get(i).getProvince().equals("")) {
            holder.province.setText(coronaCountryData.get(i).getProvince());
            holder.confirmed.setText(str_confirmed);
            holder.death.setText(str_death);
            holder.recovered.setText(str_recovered);
        } */
    }

    @Override
    public int getItemCount() {
        return coronaCountryData.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView city;
        TextView province;
        TextView confirmed;
        TextView death;
        TextView recovered;
        TextView lastUpdated;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardView);
            city = (TextView)itemView.findViewById(R.id.city);
            province = (TextView)itemView.findViewById(R.id.province);
            confirmed = (TextView)itemView.findViewById(R.id.confirmed);
            death = (TextView)itemView.findViewById(R.id.death);
            recovered = (TextView)itemView.findViewById(R.id.recovered);
            lastUpdated = (TextView)itemView.findViewById(R.id.lastUpdated);
        }
    }

}
