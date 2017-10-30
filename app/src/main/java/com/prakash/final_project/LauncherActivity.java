package com.prakash.final_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

public class LauncherActivity extends AppCompatActivity {
    boolean aBoolean;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        textView = (TextView) findViewById(R.id.textview);
        textView.setText("");

        //checking if user is available or not
        if(userAvailable()){
            //if true it will navigate to login activity
            activityNavigator(LoginActivity.class);
        }else{
            //else it will take to signup activity;
            activityNavigator(SignupActivity.class);


        }
       /* Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                if(!aBoolean){
                    Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        },5000);*/

    }

    @Override
    public void onBackPressed() {
        aBoolean =true;
        super.onBackPressed();
    }


    /**
     * Method will return true or false(if user has already signup or not)
     * @return
     */
    public boolean userAvailable(){

        boolean result=true;
        //Getting value from sharedprefrences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        //Getting password from shared prefrence
        final String savedPassword = preferences.getString(SignupActivity.PASSWORD, "");
        final String saveEmail=preferences.getString(SignupActivity.EMAIL,"");

        //setting result as false if email and password are null
        if(savedPassword.equals("") || saveEmail.equals("")){
            result=false;
        }
        return result;
    }

    //will help to navigate to another activity
    public void activityNavigator(Class<?> cls){
        Intent intent=new Intent(LauncherActivity.this,cls);
        startActivity(intent);

    }
}
