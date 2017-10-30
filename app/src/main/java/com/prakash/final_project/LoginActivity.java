package com.prakash.final_project;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextPassword;
    private EditText editTextEmail;

    private Button login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //calling method initialize
        initialize();

        //getting value from the shared prefrences


        //Adding click listener in the button
        login.setOnClickListener(this);



    }

    /**
     * This method will initialize all the view
     *
      */
    public void initialize(){
        editTextEmail=(EditText)findViewById(R.id.userEmailId);
        editTextPassword=(EditText)findViewById(R.id.etpassword);

        login=(Button)findViewById(R.id.btn_login);


    }

    @Override
    public void onClick(View v) {

        //Getting value of edittext
        String emailData=editTextEmail.getText().toString();
        String passwordData=editTextPassword.getText().toString();

        if(loginChecker(emailData,passwordData)){
            //calling activity navigator
            activityNavigator(HomeActivity.class);
        }else{
            Toast.makeText(LoginActivity.this,"Please email or password is correct",Toast.LENGTH_LONG).show();
        }

    }


    public void activityNavigator(Class<?> cls){

        Intent intent=new Intent(LoginActivity.this,cls);
        startActivity(intent);

    }

    public boolean loginChecker(String email,String password){

        boolean result=false;
        //Getting value from sharedprefrences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        //Getting password from shared prefrence
        final String savedPassword = preferences.getString(SignupActivity.PASSWORD, "");
        final String saveEmail=preferences.getString(SignupActivity.EMAIL,"");

        //setting result as true if email and password matches
        if(email.equals(saveEmail)&&password.equals(savedPassword)){
           result=true;
        }
        return result;
    }



}
