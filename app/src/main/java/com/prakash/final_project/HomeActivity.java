package com.prakash.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    //Creating View
    private TextView mtvQueryBased;
    private TextView mtvMapBased;
    private TextView mtvSetting;
    private TextView mtvHelp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //calling method initialize(it initialize all the view)
        initialize();
        //implementing clicklistener
        mtvQueryBased.setOnClickListener(this);
        mtvMapBased.setOnClickListener(this);
        mtvSetting.setOnClickListener(this);


    }


    public void initialize(){
        mtvQueryBased=(TextView)findViewById(R.id.txt_query_search);
        mtvMapBased=(TextView)findViewById(R.id.txt_query_map);
        mtvSetting=(TextView)findViewById(R.id.txt_setting);
        mtvHelp=(TextView)findViewById(R.id.txt_help);




    }

    @Override
    public void onClick(View v) {
        //getting id
        int id=v.getId();

        //based on id will navigate to activity
        switch(id){

            case R.id.txt_query_search:
                //calling activity Navigator which will take to QueryActivity
                activityNavigator(QueryActivity.class);
                break;

            case R.id.txt_query_map:
                //calling activity Navigator which will take to QueryMap
                activityNavigator(MapsActivity.class);
                break;


            case R.id.txt_help:
                //calling method which will take to Help Activity:

            case R.id.txt_setting:
                activityNavigator(SettingActivity.class);
                break;


        }

    }


    //will help to navigate to another activity
    public void activityNavigator(Class<?> cls){
        Intent intent=new Intent(HomeActivity.this,cls);
        startActivity(intent);

    }
}
