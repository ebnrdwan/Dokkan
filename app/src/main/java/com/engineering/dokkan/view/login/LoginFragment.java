package com.engineering.dokkan.view.login;

import android.view.View;
import android.widget.Button;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.engineering.dokkan.R;
import com.engineering.dokkan.view.base.BaseFragment;

public class LoginFragment extends BaseFragment {
    private Button btn_sing;

    public LoginFragment() {

    }

    @Override
    public int getLayoutId() {
        return (R.layout.fragment_login2);
    }
    NavController getNavController(){

        return Navigation.findNavController(getActivity() ,R.id.nav_host_fragment);

    }



    @Override
    public void initializeViews(View view) {
        initialization(view);
        setListeners();
    }

    @Override
    public void setListeners() {
        btn_sing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNavController().navigate(R.id.action_loginFragment_to_profileFragment);
            }
        });
    }

    public void initialization( View view){

        btn_sing = view.findViewById(R.id.but_sing);}
}
