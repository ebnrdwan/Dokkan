package com.engineering.dokkan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button_x,button_y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_x=findViewById(R.id.button);
        button_y = findViewById(R.id.button1);
        button_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification fragx = new Notification();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.contener,fragx);
                ft.addToBackStack(null);
                ft.commit();

            }
        });
        button_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               No_Notifications fragy = new No_Notifications();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.contener,fragy);
                ft.addToBackStack(null);
                ft.commit();
            }
        });


    }
}