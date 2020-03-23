package com.example.coronastatistics;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final LatLng ALBANIA = new LatLng(41.6, 20.4);
    private static final LatLng ANDORRA = new LatLng(42.33, 1.34);
    private static final LatLng BALTICSTATES = new LatLng(56.51, 24.35);
    private static final LatLng BELGIUM = new LatLng(50.38, 4.4);
    private static final LatLng BOSNIAANDHERZEGOVINA = new LatLng(44.12, 17.47);
    private static final LatLng BULGARIA = new LatLng(42.45, 25.14);
    private static final LatLng DENMARK = new LatLng(55.57, 11.45);
    private static final LatLng GERMANY = new LatLng(51.8, 10.25);
    private static final LatLng ESTONIA = new LatLng(58.41, 25.54);
    private static final LatLng FINLAND = new LatLng(64.1, 25.48);
    private static final LatLng FRANCE = new LatLng(46.29, 2.36);
    private static final LatLng GREECE = new LatLng(38.28, 22.3);
    private static final LatLng GREATBRITAIN = new LatLng(54, -2.32);
    private static final LatLng IBERIANPENINSULA = new LatLng(40.18, -3.41);
    private static final LatLng IRELAND = new LatLng(53.3, -8);
    private static final LatLng ICELAND = new LatLng(67.9, -18.41);
    private static final LatLng ITALY = new LatLng(42.24, 12.51);
    private static final LatLng KAZAKHSTAN = new LatLng(49.3, 49.31);
    private static final LatLng KOSOVO = new LatLng(42.33, 20.54);
    private static final LatLng CROATIA = new LatLng(44.29, 16.29);
    private static final LatLng LATVIA = new LatLng(56.52, 24.36);
    private static final LatLng LIECHTENSTEIN = new LatLng(47.8, 9.33);
    private static final LatLng LITHUANIA = new LatLng(55, 23);
    private static final LatLng LUEXEMBOURG = new LatLng(49, 6);
    private static final LatLng MALTA = new LatLng(35, 14);
    private static final LatLng NETHERLANDS = new LatLng(52, 5);
    private static final LatLng NORTHMACEDONIA = new LatLng(41, 21);
    private static final LatLng NORWAY = new LatLng(63, 12);
    private static final LatLng AUSTRIA = new LatLng(47, 14);
    private static final LatLng POLAND = new LatLng(52, 19);
    private static final LatLng PORTUGAL = new LatLng(39, -8);
    private static final LatLng ROMANIA  = new LatLng(45, 24);
    private static final LatLng RUSSIA  = new LatLng(57, 44);
    private static final LatLng SANMARINO = new LatLng(43, 12);
    private static final LatLng SWEDEN  = new LatLng(62, 16);
    private static final LatLng SWITZERLAND  = new LatLng(46, 8);
    private static final LatLng SLOVAKIA  = new LatLng(48, 19);
    private static final LatLng SLOVENIA  = new LatLng(46, 14);
    private static final LatLng SPAIN = new LatLng(40, -3);
    private static final LatLng CZECHREPUBLIC  = new LatLng(49, 15);
    private static final LatLng TURKEY  = new LatLng(41, 27);
    private static final LatLng UKRAINE  = new LatLng(48, 31);
    private static final LatLng HUNGARY  = new LatLng(47, 19);
    private static final LatLng VATICANCITY = new LatLng(41, 12);
    private static final LatLng BELARUS = new LatLng(53, 28);


    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Intent inputCountry = getIntent();
        String inCountry = inputCountry.getStringExtra("country");
        LatLng latlng = null;

        // if (inCountry == "Spain"){
           // latlng = SPAIN;
            mMap.addMarker(new MarkerOptions().position(SPAIN));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(SPAIN));
       // }
    }
}