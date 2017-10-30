package com.prakash.final_project;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Final_Result_mapActivity extends AppCompatActivity implements OnMapReadyCallback {
    TextView textView_name_final,textView_add_final;
   GoogleMap mGoogleMap;
    double lat,lng;
    String name, add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_final__result_map);
        textView_name_final = (TextView) findViewById(R.id.textview_name_final);
        textView_add_final = (TextView) findViewById(R.id.textview_add_final);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        supportMapFragment.getMapAsync(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        lat = Double.parseDouble(bundle.getString("lat"));
        lng = Double.parseDouble(bundle.getString("lng"));
        name = bundle.getString("name");
        add = bundle.getString("add");
        textView_name_final.setText(name);
        textView_add_final.setText(add);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        LatLng place = new LatLng(lat, lng);
        mGoogleMap.addMarker(new MarkerOptions().position(place).title(name));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(place));

    }
}
