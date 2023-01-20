package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    Button a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        a= (Button)findViewById(R.id.start_btn);

        a.setOnClickListener(new View.OnClickListener()){
            public void onClick(View v){
                Intent myIntent = new Intent(LoginActivity.this, activi)
            }
        }
    }
}