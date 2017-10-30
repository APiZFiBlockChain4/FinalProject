package com.prakash.final_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etOldEmail,etNewEmail,etOldPassword,etNewPassword,etConfirmPassword;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //Calling method to initialize all the view
        initialize();

        //adding click listener
        save.setOnClickListener(this);
    }


    public void initialize(){
        etOldEmail=(EditText)findViewById(R.id.et_setting_recentEmail);
        etNewEmail=(EditText)findViewById(R.id.et_setting_newEmail);
        etOldPassword=(EditText)findViewById(R.id.et_setting_recentPassword);
        etNewPassword=(EditText)findViewById(R.id.et_setting_newPassword);
        etConfirmPassword=(EditText)findViewById(R.id.et_setting_newConfirmPassword);
        save=(Button)findViewById(R.id.btn_setting_save);



    }

    @Override
    public void onClick(View v) {
        String oldEmailData=etOldEmail.getText().toString();
        String newEmailData=etNewEmail.getText().toString();
        String oldPasswordData=etOldPassword.getText().toString();
        String newPasswordData=etNewPassword.getText().toString();
        String confrimPasswordData=etConfirmPassword.getText().toString();

        validator(oldEmailData,newEmailData,oldPasswordData,newPasswordData,confrimPasswordData);


    }

    public void validator(String recentEmail,String newEmail,String oldPassword,String newPassword,String confrimPassword){

        //getting saved data
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        //Getting password from shared prefrence
        final String savedPassword = preferences.getString(SignupActivity.PASSWORD, "");
        final String saveEmail=preferences.getString(SignupActivity.EMAIL,"");

        if(recentEmail !=null && newEmail !=null && oldPassword !=null && newPassword!=null &&confrimPassword !=null){

            if(confrimPassword.equals(newPassword)) {

                int passwordLength = newPassword.length();

                if (passwordLength > 2 && passwordLength < 4) {

                    if (saveEmail.equals(recentEmail) && oldPassword.equals(savedPassword)) {

                        savePassword(newPassword,newEmail);
                        refresher();

                    } else {
                        Toast.makeText(this, "Recent mail or Recent Password is incorrect", Toast.LENGTH_SHORT).show();
                    }

                    //calling refresher method (set's all value to null)


                } else{
                    Toast.makeText(SettingActivity.this,"Password must be only 3 char",Toast.LENGTH_SHORT).show();
                }


            }else{
                Toast.makeText(SettingActivity.this,"Confirm Password didn't match",Toast.LENGTH_SHORT).show();
            }



        }else{
            Toast.makeText(SettingActivity.this,"Field Empty",Toast.LENGTH_LONG).show();
        }




    }


    public void savePassword(String password,String email){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SignupActivity.PASSWORD,password);
        editor.putString(SignupActivity.EMAIL,email);
        editor.apply();

        Toast.makeText(SettingActivity.this,"Success",Toast.LENGTH_SHORT).show();

        //It will take to the LoginActivity where user will enter password
        Intent intent=new Intent(SettingActivity.this,LoginActivity.class);
        startActivity(intent);




    }

    public void refresher(){
        etOldEmail.setText("");
        etNewEmail.setText("");
        etOldPassword.setText("");
        etNewPassword.setText("");
        etConfirmPassword.setText("");




    }



}
