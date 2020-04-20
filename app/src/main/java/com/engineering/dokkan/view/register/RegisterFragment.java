package com.engineering.dokkan.view.register;


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
public class RegisterFragment extends BaseFragment {
private Button register;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_register;
    }
    NavController getNavController() {
        return Navigation.findNavController(getActivity(), R.id.my_nav_host);
    }
    @Override
    public void initializeViews(View view) {
        register = view.findViewById(R.id.butt);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNavController().navigate(R.id.action_registerFragment_to_profileFragment);
            }

        });

    }

    @Override
    public void setListeners() {

    }





}
