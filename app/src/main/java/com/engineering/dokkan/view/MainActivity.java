package com.engineering.dokkan.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.engineering.dokkan.R;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

    }


}