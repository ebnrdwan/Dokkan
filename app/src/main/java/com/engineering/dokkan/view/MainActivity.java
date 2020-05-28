package com.engineering.dokkan.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.engineering.dokkan.R;
import com.engineering.dokkan.view.chat.ChatFragement;
import com.engineering.dokkan.view.notifications.NotificationFragment;
import com.engineering.dokkan.view.profile.ProfileFragment;
import com.engineering.dokkan.view.questions.AskQuestionFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        navigateToProfile();
//        navigateToNotification();
//        navigateToChat();
   //       navigateToQuestions();


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
        ft.commit();
    }


    public void navigateToChat() {
        ChatFragement chatFragement = new ChatFragement();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, chatFragement);
        ft.commit();
    }



}