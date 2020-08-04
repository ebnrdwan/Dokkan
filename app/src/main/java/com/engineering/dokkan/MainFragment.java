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

import com.engineering.dokkan.R;
import com.engineering.dokkan.view.base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment {
    private Button btn_home,btn_chating, btn_msg, btn_notification, btn_profile, btn_askQ , btn_product;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    NavController getNavController(){
        return Navigation.findNavController(getActivity() ,R.id.my_nav_host);
    }

    @Override
    public void initializeViews(View view) {
        initialization(view);
    }
    @Override
    public void setListeners() {
        btn_chating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNavController().navigate(R.id.action_mainFragment2_to_chatFragement);
            }
        });
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNavController().navigate(R.id.action_mainFragment2_to_homeFragment2);
            }
        });
        btn_askQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNavController().navigate(R.id.action_mainFragment2_to_askQuestionFragment);

            }
        });
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNavController().navigate(R.id.action_mainFragment2_to_profileFragment);

            }
        });
        btn_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNavController().navigate(R.id.action_mainFragment2_to_notificationFragment);

            }
        });
        btn_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNavController().navigate(R.id.action_mainFragment2_to_chatFragement);

            }
        });
        btn_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNavController().navigate(R.id.action_mainFragment_to_productDetailsFragment);

            }
        });

    }

    public void initialization(View view) {
        btn_home = view.findViewById(R.id.btn_home);
        btn_chating = view.findViewById(R.id.btn_chating);
        btn_msg = view.findViewById(R.id.btn_message);
        btn_notification = view.findViewById(R.id.btn_notify);
        btn_profile = view.findViewById(R.id.btn_profile);
        btn_askQ = view.findViewById(R.id.btn_ask);
        btn_product =  view.findViewById(R.id.btn_productDetails);
    }


}

