package com.prakash.final_project;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends Activity implements View.OnClickListener {

    //Creating instance of view
    EditText etFullName;
    EditText etEmailId;
    EditText etLocation;
    EditText etPhone;
    EditText password,confirmpassword;
    Button save;

    public static final String PASSWORD="password";
    public static final String EMAIL="emial";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //calling method initialize to initialize all the views
        initialize();
        //setting click listener
        save.setOnClickListener(this);
    }
    public void initialize(){
        etFullName=(EditText)findViewById(R.id.fullName);
        etEmailId=(EditText)findViewById(R.id.userEmailId);
        etLocation=(EditText)findViewById(R.id.location);
        etPhone=(EditText)findViewById(R.id.mobileNumber);

        password=(EditText)findViewById(R.id.et_password);
        confirmpassword=(EditText)findViewById(R.id.et_confirmPassword);

        save=(Button)findViewById(R.id.btn_savePassword);


    }

    @Override
    public void onClick(View v) {
        String emailData=etEmailId.getText().toString();
        String passwrdData=password.getText().toString();
        String confirmData=confirmpassword.getText().toString();

        validation(passwrdData,confirmData,emailData);


    }

    public void validation(String passwordData,String confirmPassword,String email){

        if(passwordData==null){
            Toast.makeText(SignupActivity.this,"Please enter password",Toast.LENGTH_LONG).show();


        }
        else if(confirmPassword==null){
            Toast.makeText(SignupActivity.this,"Please enter confirm password",Toast.LENGTH_LONG).show();
        }else if(email==null){
            Toast.makeText(SignupActivity.this,"Please enter email",Toast.LENGTH_LONG).show();
        }

        if(passwordData.equals(confirmPassword)) {
            int passwordLength = passwordData.length();

            if (passwordLength > 2 && passwordLength < 4) {
                savePassword(passwordData,email);
                //calling refresher method (set's all value to null)
                refresher();

            } else{
                Toast.makeText(SignupActivity.this,"Password must be only 3 char",Toast.LENGTH_SHORT).show();
            }


        }else{
            Toast.makeText(SignupActivity.this,"Password and confirm password must be same",Toast.LENGTH_LONG).show();
        }




    }

    public void savePassword(String password,String email){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PASSWORD,password);
        editor.putString(EMAIL,email);
        editor.apply();

        Toast.makeText(SignupActivity.this,"Success",Toast.LENGTH_SHORT).show();

        //It will take to the LoginActivity where user will enter password
        Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
        startActivity(intent);




    }


    //This method will set all the edittext to null
    private void refresher(){
        etFullName.setText("");
        etEmailId.setText("");
        etLocation.setText("");

        password.setText("");
        confirmpassword.setText("");
        etPhone.setText("");




    }



}
