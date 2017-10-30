package com.prakash.final_project;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReasultmapActivity extends AppCompatActivity {
    Geocoder geocoder;
    String lat,lng;
    double latitude1,longitude2;
    String city_map;
    TextView textview_map_city;
    Button button_get_data;
    Spinner spinner_map;
    StringBuilder link;
    String help;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reasultmap);
        textview_map_city = (TextView) findViewById(R.id.textview_current_city);
        button_get_data = (Button) findViewById(R.id.button_send);
        spinner_map = (Spinner) findViewById(R.id.spinner2);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        lat = bundle.getString("lat");
        lng = bundle.getString("lng");
        latitude1 = Double.parseDouble(lat);
        longitude2 = Double.parseDouble(lng);
        geocoder = new Geocoder(this);
        try {

            List<Address> list = geocoder.getFromLocation(latitude1, longitude2, 1);
            Address bestResult = list.get(0);
            city_map =bestResult.getLocality();
           // Toast.makeText(ReasultmapActivity.this, ""+city_map, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        textview_map_city.setText(city_map);
        arrayList = new ArrayList<String>();
        arrayList.add("Choose any one to help:-");
        arrayList.add("Hospital");
        arrayList.add("Police Station");
        arrayList.add("Fire Station");
        arrayList.add("Restaurant");
        arrayList.add("School");
        arrayList.add("Collage");
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arrayList);
        spinner_map.setAdapter(arrayAdapter);
        spinner_map.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                  help = arrayList.get(position);
                Toast.makeText(ReasultmapActivity.this, "You Select:- "+help, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(ReasultmapActivity.this, "Please Select anything foe Help",
                        Toast.LENGTH_SHORT).show();

            }
        });

        button_get_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(ReasultmapActivity.this, ReasultActivity.class);
            // intent.putExtra("city",city_map);
                intent.putExtra("help",help);
                getData();
                intent.putExtra("link",link.toString());
                startActivity(intent);
            }
        });
    }


    public void getData(){

        String city = city_map;
        String type="";
        link=new StringBuilder("https://maps.googleapis.com/maps/api/place/search/json?location=");

            link.append(latitude1+",");
            link.append(longitude2+"&");
            link.append("radius=500&types=");
           // Toast.makeText(this,"lat"+latitude1+"log"+longitude2,Toast.LENGTH_SHORT).show();
            switch (help){
                case "Hospital" :
                    type="hospital";

                    break;
                case "Police Station" :
                    type="policestation";
                    break;
                case "Fire Station" :
                    type="firestation";
                    break;
                case "Restaurant" :
                    type="restaurant";
                    break;
                case "School" :
                    type="school";
                    break;
                case "Collage" :
                    type="collage";
                    break;
            }

        link.append(type);
        link.append("&sensor=false&key=AIzaSyB94dnTqE7T5CQ36lz6A2IuKrOzZ1vaFp0");
        String s=link.toString();


    }

}
