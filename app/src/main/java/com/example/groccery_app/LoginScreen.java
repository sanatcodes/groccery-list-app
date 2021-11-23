package com.example.groccery_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView username = (TextView) findViewById(R.id.userName);
        TextView password = (TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        Button test_btn = (Button) findViewById(R.id.test_btn);

        loginbtn.setOnClickListener(new View.OnClickListener(){

            @Override   //todo:relace this with DB Verification
            public void onClick(View v) {
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("password")){
                    //User Authenticated
                    Toast.makeText(getApplicationContext(),"Sign In Successful",Toast.LENGTH_SHORT).show();
                    openGrid();
                }else{
                    //User not Authetnticated
                    Toast.makeText(getApplicationContext(),"Sign In Unsuccessful",Toast.LENGTH_SHORT).show();
                }
            }
        });

        test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Sign In Successful",Toast.LENGTH_SHORT).show();
                openGrid();
            }
        });



    }

    public void openGrid(){
        Intent intent = new Intent(this, Grid.class);
        startActivity(intent);
    };
}