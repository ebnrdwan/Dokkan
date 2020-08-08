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

    }


}