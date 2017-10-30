package com.prakash.final_project;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

public class QueryActivity extends AppCompatActivity {
    EditText editText_city;
    Spinner spinner;
    Button button_search;
    ArrayList<String> arrayList;
    ArrayList<Data> list;
    ArrayAdapter<String> arrayAdapter;
    String help;
    StringBuilder link;
    double latitude = 0,longitude = 0;
    String city;



   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        editText_city = (EditText) findViewById(R.id.edittext_city);
        spinner = (Spinner) findViewById(R.id.spinner);
        button_search = (Button) findViewById(R.id.button_search);
        arrayList = new ArrayList<String>();
        list=new ArrayList<>();
       arrayList.add("Choose any one to help:-");
       arrayList.add("Hospital");
       arrayList.add("Police Station");
       arrayList.add("Fire Station");
       arrayList.add("Restaurant");
       arrayList.add("School");
       arrayList.add("Collage");
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arrayList);
        spinner.setAdapter(arrayAdapter);
       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               help = arrayList.get(position);
               Toast.makeText(QueryActivity.this, "You Select:- "+help, Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {
               Toast.makeText(QueryActivity.this, "Please Select anything for Help",
                       Toast.LENGTH_SHORT).show();

           }
       });

       button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = editText_city.getText().toString();
                Intent intent = new Intent(QueryActivity.this, ReasultActivity.class);
                intent.putExtra("city",city);
                intent.putExtra("help",help);
                getData();
                intent.putExtra("link",link.toString());


               startActivity(intent);
            }
        });


    }

    public void getData(){

        String city = editText_city.getText().toString();
        String type="";
        link=new StringBuilder("https://maps.googleapis.com/maps/api/place/search/json?location=");

        Geocoder mGeocoder=new Geocoder(this);
        try {
            List<Address> list = mGeocoder.getFromLocationName(city,2);
            Address bestResult = list.get(0);
            latitude = bestResult.getLatitude();
            longitude = bestResult.getLongitude();
            link.append(latitude+",");
            link.append(longitude+"&");
            link.append("radius=500&types=");
            Toast.makeText(this,"lat"+latitude+"log"+longitude,Toast.LENGTH_SHORT).show();
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




        } catch (IOException e) {
            e.printStackTrace();
        }
        link.append(type);
        link.append("&sensor=false&key=AIzaSyB94dnTqE7T5CQ36lz6A2IuKrOzZ1vaFp0");
        String s=link.toString();
        //myTask.execute(link.toString());

    }
}

