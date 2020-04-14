package com.engineering.dokkan.view.Favourite;


import android.os.Bundle;

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
public class mainFragment extends BaseFragment {
      private Button askq_btn , fav_btn , profile_btn ;

    public mainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    NavController getNavController() {
        return Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void initializeViews(View view) {
        askq_btn = view.findViewById(R.id.btn_askQ);
        askq_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNavController().navigate(R.id.action_mainFragment_to_askQuestionFragment);
            }
        });
        fav_btn = view.findViewById(R.id.btn_fav);
        fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNavController().navigate(R.id.action_mainFragment_to_fragmentFavourite);

            }
        });
        profile_btn = view.findViewById(R.id.btn_profile);
        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNavController().navigate(R.id.action_mainFragment_to_profileFragment);

            }
        });

    }

    @Override
    public void setListeners() {

    }


}
