package com.engineering.dokkan.view.welcome;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.engineering.dokkan.R;
import com.engineering.dokkan.view.base.BaseFragment;

public class Fragment_Welcome extends BaseFragment {

    public Button btn_singIn;
    public Button btn_singUp;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_welcome;
    }

    @Override
    public void initializeViews(View view) {
        btn_singIn = view.findViewById(R.id.btn_signIn);
        btn_singUp = view.findViewById(R.id.btn_signUp);

        btn_singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
       //         getNavController().navigate(R.id.action_fragment_Welcome2_to_profileFragment2);
            }
        });

        btn_singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNavController().navigate(R.id.action_fragment_Welcome_to_registerFragment);
            }
        });
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    NavController getNavController() {
        return Navigation.findNavController(getActivity(), R.id.my_nav_host);

    }
/*
    void navigateToAskQuestion() {
        getNavController().navigate(R.id.action_fragment_Welcome2_to_askQuestionFragment);
    }

    void navigateToProfile() {
        getNavController().navigate(R.id.action_fragment_Welcome2_to_profileFragment2);
    }

    void navigateToM() {
        getNavController().navigate(R.id.action_fragment_Welcome2_to_mainActivity2);
    }
*/


}
