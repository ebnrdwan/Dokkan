package com.engineering.dokkan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
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
        initializeViews();
        setLisetners(button_x,new Notification());
        setLisetners(button_y,new No_Notifications());

    }

    public void initializeViews(){
    button_x=findViewById(R.id.button);
    button_y = findViewById(R.id.button1);
    }
   public void navigateToNotification(Fragment fragment){
       FragmentManager fm = getSupportFragmentManager();
       FragmentTransaction ft = fm.beginTransaction();
       ft.replace(R.id.contener,fragment);
       ft.addToBackStack(null);
       ft.commit();
    }
     public void setLisetners(Button button , final Fragment fragment){
         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 navigateToNotification(fragment);

             }
         });
    }

}