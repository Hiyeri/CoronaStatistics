package com.example.coronastatistics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class CoronaActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.editText);
        textView.setText(message);

        btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener((View.OnClickListener) this);
    }


    @Override
    public void onClick(View v) {
        String country = ("");
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
