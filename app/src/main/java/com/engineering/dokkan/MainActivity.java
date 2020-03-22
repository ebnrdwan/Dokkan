package com.engineering.dokkan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    Button button_x, button_y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setListeners();


//        navigateToProfile();
        navigateToQuestions();


    }


    void navigateToProfile() {
        ProfileFragment headFragment = new ProfileFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.container, headFragment)
                .commit();
    }


    public void initViews() {
        button_x = findViewById(R.id.button);
        button_y = findViewById(R.id.button1);
    }

    void navigateToQuestions() {
        Askquestion_Fragment Fragment = new Askquestion_Fragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.container, Fragment);
        ft.commit();
    }
}



    public void setListeners() {
        button_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navigateToNotification();
            }
        });
        button_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToNoNotification();
            }
        });
    }


    public void navigateToNoNotification() {
        No_Notifications fragy = new No_Notifications();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.contener, fragy);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void navigateToNotification() {
        Notification fragx = new Notification();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.contener, fragx);
        ft.addToBackStack(null);
        ft.commit();
    }
}