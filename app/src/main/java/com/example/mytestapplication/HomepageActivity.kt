package com.example.mytestapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class HomepageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the homepage.xml layout
        setContentView(R.layout.homepage); // Make sure this layout exists in res/layout
    }
}
