package com.example.jonnel.parkhere;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class uniqueIDActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unique_id);
        getIntent().getExtras();/* push unique id to the search*/
    }
}
