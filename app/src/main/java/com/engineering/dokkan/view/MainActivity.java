package com.engineering.dokkan.view;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.engineering.dokkan.R;

public class MainActivity extends AppCompatActivity {


    FrameLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootView = findViewById(R.id.rootView);

//        navigateToProfile();
//        navigateToNotification();
//        navigateToChat();
//        navigateToQuestions();


    }


    void navigateToProfile() {
        getNavController().navigate(R.id.toProfile);
    }


    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp();

    }

    void navigateToQuestions() {
        getNavController().navigate(R.id.toAskQuestions);
    }


    NavController getNavController() {
        return Navigation.findNavController(this, R.id.nav_host_fragment);

    }


    public void navigateToNotification() {
        getNavController().navigate(R.id.toNotification);
    }


    public void navigateToChat() {
        getNavController().navigate(R.id.toChat);
    }


}