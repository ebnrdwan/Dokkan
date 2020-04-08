package com.engineering.dokkan;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.engineering.dokkan.view.base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment {
   private Button btn_home , btn_msg , btn_notification , btn_profile , btn_askQ ;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    NavController getNavController(){
        return Navigation.findNavController(getActivity() ,R.id.nav_host_fragment);
    }



    @Override
    public void initializeViews(View view) {
        initialization(view);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNavController().navigate(R.id.action_mainFragment_to_homeFragment);
            }
        });
        btn_askQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNavController().navigate(R.id.action_mainFragment_to_askQuestionFragment);

            }
        });
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNavController().navigate(R.id.action_mainFragment_to_profileFragment);

            }
        });
        btn_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNavController().navigate(R.id.action_mainFragment_to_notificationFragment);

            }
        });
        btn_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNavController().navigate(R.id.action_mainFragment_to_chatFragement);

            }
        });


    }

    @Override
    public void setListeners() {

    }

    public void initialization( View view){
        btn_home = view.findViewById(R.id.btn_home);
        btn_msg =view.findViewById(R.id.btn_message);
        btn_notification = view.findViewById(R.id.btn_notify);
        btn_profile = view.findViewById(R.id.btn_profile);
        btn_askQ = view.findViewById(R.id.btn_ask);
    }


}
