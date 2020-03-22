package com.engineering.dokkan.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.engineering.dokkan.view.notifications.No_Notifications;
import com.engineering.dokkan.view.notifications.NotificationFragment;
import com.engineering.dokkan.R;
import com.engineering.dokkan.view.profile.ProfileFragment;
import com.engineering.dokkan.view.questions.AskQuestionFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        navigateToProfile();
//        navigateToNotification();
        navigateToQuestions();


    }


    void navigateToProfile() {
        ProfileFragment headFragment = new ProfileFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.container, headFragment)
                .commit();
    }



    void navigateToQuestions() {
        AskQuestionFragment Fragment = new AskQuestionFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.container, Fragment);
        ft.commit();
    }


    public void navigateToNotification() {
        NotificationFragment fragx = new NotificationFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, fragx);
        ft.addToBackStack(null);
        ft.commit();
    }







}