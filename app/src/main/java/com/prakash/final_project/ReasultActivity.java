package com.prakash.final_project;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
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

public class ReasultActivity extends AppCompatActivity {
    TextView textView_city,textView_help,textView_lat_log;
    LinearLayout linearLayout;
    ArrayList<Data> arraylist;
    MyTask myTask;
    RecyclerView recyclerView;
    ArrayList<Data> arrayList;
    MyAdapter myAdapter;
    LinearLayoutManager layoutManager;
   // Geocoder geocoder;
  //  ListView listview;
   // String name,add,rating;
   // SuperAdapter superAdapter;
   // String city;

    //Data d;
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.row,parent,false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Data d = arraylist.get(position);
            holder.textView1.setText(d.getName1());
            holder.textView2.setText(d.getRating1());
            holder.textView3.setText(d.getAdd1());
        }

        @Override
        public int getItemCount() {
            return arraylist.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView1,textView2,textView3;
            LinearLayout linearLayout_row;
            public ViewHolder(View itemView) {
                super(itemView);
                textView1 = (TextView) itemView.findViewById(R.id.textview_name_row);
                textView2 = (TextView) itemView.findViewById(R.id.textview_rate_row);
                textView3 = (TextView) itemView.findViewById(R.id.textview_address_row);
                linearLayout_row  = (LinearLayout) itemView.findViewById(R.id.linearlayout_row);
                linearLayout_row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        Data da = arraylist.get(position);
                        Intent i = new Intent(ReasultActivity.this, Final_Result_mapActivity.class);
                        i.putExtra("lat",da.getLat());
                        i.putExtra("lng",da.getLng());
                        i.putExtra("name",da.getName1());
                        i.putExtra("add",da.getAdd1());
                        startActivity(i);
                    }
                });

            }
        }
    }
    public class MyTask extends AsyncTask<String, Void, String> {
        URL url;
        HttpURLConnection connection;
        InputStream inputStream;
        BufferedReader bufferedReader;
        InputStreamReader inputStreamReader;
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        @Override
        protected void onPreExecute() {
            Toast.makeText(ReasultActivity.this,
                    "Please wait.......\n we are connecting to server",
                    Toast.LENGTH_SHORT).show();
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... params) {
            try {
                url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                inputStream = connection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);
                line = bufferedReader.readLine();
                while(line!=null){
                    stringBuilder.append(line);
                    line = bufferedReader.readLine();
                }
                return stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            String n = null,add = null,rating = null;
            String lat,lng;
            try {
                JSONObject j = new JSONObject(s);
                JSONArray result = j.getJSONArray("results");
                for(int i=0; i<result.length(); i++){
                    JSONObject a = result.getJSONObject(i);
                    JSONObject geometry = a.getJSONObject("geometry");
                    JSONObject location = geometry.getJSONObject("location");
                    lat = location.getString("lat");
                    lng = location.getString("lng");
                    n = a.getString("name");
                    rating = a.getString("place_id");
                    add = a.getString("vicinity");
                    Data d=new Data(n,rating,add,lat,lng);
                    arraylist.add(d);

                   myAdapter.notifyDataSetChanged();
                }
                //city = editText_city.getText().toString();

                //intent.putExtra("city",city);
                //intent.putExtra("help",help);
                   // Data d=new Data(name,rating,add);
                // arraylist.add(d);


            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(s);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reasult);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //listview = (ListView) findViewById(R.id.listview);
        myAdapter=new MyAdapter();
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        arraylist  = new ArrayList<Data>();
        recyclerView.setAdapter(myAdapter);
       // superAdapter = new SuperAdapter();

        Intent intent  = getIntent();
        Bundle bundle = intent.getExtras();

        final String link=bundle.getString("link");
        myTask=new MyTask();
        myTask.execute(link);


    }
}
