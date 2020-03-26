package com.example.coronastatistics;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final LatLng ALBANIA = new LatLng(41.158790, 20.067650);
    private static final LatLng ANDORRA = new LatLng(42.534734, 1.570799);
    private static final LatLng BELGIUM = new LatLng(50.639324, 4.621837);
    private static final LatLng BOSNIAANDHERZEGOVINA = new LatLng(44.354806, 17.740051);
    private static final LatLng BULGARIA = new LatLng(42.674630, 25.244097);
    private static final LatLng DENMARK = new LatLng(55.800767, 9.506245);
    private static final LatLng GERMANY = new LatLng(51.308969, 10.276098);
    private static final LatLng ESTONIA = new LatLng(58.777190, 25.831528);
    private static final LatLng FINLAND = new LatLng(63.582803, 26.734297);
    private static final LatLng FRANCE = new LatLng(46.927854, 2.399323);
    private static final LatLng GREECE = new LatLng(39.798997, 22.057417);
    private static final LatLng GREATBRITAIN = new LatLng(53.585556, -1.535887);
    private static final LatLng IRELAND = new LatLng(53.158908, -7.652722);
    private static final LatLng ICELAND = new LatLng(64.994182, -18.025117);
    private static final LatLng ITALY = new LatLng(42.268165, 13.358368);
    private static final LatLng KOSOVO = new LatLng(42.564426, 21.020131);
    private static final LatLng CROATIA = new LatLng(45.631179, 16.363414);
    private static final LatLng LATVIA = new LatLng(57.083053, 25.955585);
    private static final LatLng LIECHTENSTEIN = new LatLng(47.134593, 9.559855);
    private static final LatLng LITHUANIA = new LatLng(55.383243, 24.218256);
    private static final LatLng LUXEMBOURG = new LatLng(49.706117, 6.135483);
    private static final LatLng MALTA = new LatLng(35.874363, 14.458243);
    private static final LatLng NETHERLANDS = new LatLng(51.993476, 5.430255);
    private static final LatLng NORTHMACEDONIA = new LatLng(41.607156, 21.795942);
    private static final LatLng NORWAY = new LatLng(61.542708, 9.156257);
    private static final LatLng AUSTRIA = new LatLng(47.408489, 14.544960);
    private static final LatLng POLAND = new LatLng(52.318713, 19.413603);
    private static final LatLng PORTUGAL = new LatLng(39.950720, -8.164066);
    private static final LatLng ROMANIA  = new LatLng(39.950720, -8.164066);
    private static final LatLng RUSSIA  = new LatLng(56.766257, 41.259890);
    private static final LatLng SANMARINO = new LatLng(43.935818, 12.462866);
    private static final LatLng SWEDEN  = new LatLng(63.161678, 16.644731);
    private static final LatLng SWITZERLAND  = new LatLng(46.935377, 8.247744);
    private static final LatLng SLOVAKIA  = new LatLng(48.773992, 19.395394);
    private static final LatLng SLOVENIA  = new LatLng(46.106582, 14.702019);
    private static final LatLng SPAIN = new LatLng(40.225540, -3.612054);
    private static final LatLng CZECHREPUBLIC  = new LatLng(49.883989, 15.267908);
    private static final LatLng TURKEY  = new LatLng(39.189988, 34.357453);
    private static final LatLng UKRAINE  = new LatLng(49.320290, 32.087909);
    private static final LatLng HUNGARY  = new LatLng(47.155526, 19.456917);
    private static final LatLng VATICANCITY = new LatLng(41.903328, 12.453144);
    private static final LatLng BELARUS = new LatLng(53.664727, 27.695295);
    private static final LatLng CHINA = new LatLng(33.851374, 103.578709);
    private static final LatLng SOUTHKOREA = new LatLng(36.774610, 128.026229);
    private static final LatLng IRAN = new LatLng(32.596629, 54.368078);

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
        LatLng [] geoEurope = {ALBANIA, ANDORRA, BELGIUM, BOSNIAANDHERZEGOVINA, BULGARIA, DENMARK, GERMANY, ESTONIA, FINLAND, FRANCE, GREECE, GREATBRITAIN,
                IRELAND, ICELAND, ITALY, KOSOVO, CROATIA, LATVIA, LIECHTENSTEIN, LITHUANIA, LUXEMBOURG, MALTA, NETHERLANDS, NORTHMACEDONIA, NORWAY, AUSTRIA, POLAND, PORTUGAL,
                ROMANIA, RUSSIA, SANMARINO, SWEDEN, SWITZERLAND, SLOVAKIA, SLOVENIA, SPAIN, CZECHREPUBLIC, TURKEY, UKRAINE, HUNGARY, VATICANCITY, BELARUS, CHINA, SOUTHKOREA, IRAN};

        String [] strEurope = {"Albania", "Andorra", "Belgium", "Bosniaandherzegovina", "Bulgaria", "Denmark", "Germany", "Estonia", "Finland", "France", "Greece", "Greatbritain",
                "Ireland", "Iceland", "Italy", "Kosovo", "Croatia", "Latvia", "Liechtenstein", "Lithuania", "Luxembourg", "Malta", "Netherlands", "Northmacedonia",
                "Norway", "Austria", "Poland", "Portugal", "Romania", "Russia", "Sanmarino", "Sweden", "Switzerland", "Slovakia", "Slovenia", "Spain", "Czechrepublic", "Turkey",
                "Ukraine", "Hungary", "Vaticancity", "Belarus", "China", "Southkorea", "Iran"};

        mMap = googleMap;

        Intent intent_country = getIntent();
        String transmittedCountry = intent_country.getStringExtra("country");

        for (int i=0; i<strEurope.length; i++)
            if (transmittedCountry.equals(strEurope[i])){
                //Log.d("Test",inCountry);
                //Log.d("Test", strEurope[0]);
                mMap.addMarker(new MarkerOptions().position(geoEurope[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(geoEurope[i]));
            }
    }
}