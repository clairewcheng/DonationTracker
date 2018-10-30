package com.example.claire.donationtrackerv1.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.claire.donationtrackerv1.R;

public class SearchResultsActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Intent intent = getIntent();
        String queryPassed = intent.getExtras().getString("category");

        Log.d("hello", queryPassed);


    }
}
