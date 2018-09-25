package com.example.claire.donationtrackerv1;

import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class AppHome extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private Button backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_home);

        backbutton = (Button) findViewById(R.id.tempsignoutbutton);

        backbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == backbutton) {
            // navigate to logged in screen(currently causing app crash
            Intent intentLogOut = new Intent(getApplicationContext(),signinscreen.class);
            startActivity(intentLogOut);
        }

    }
}
