package com.engineering.dokkan.view.Empty_Cart;

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
public class Empty_CartFragment extends BaseFragment {
    Button back;

    public Empty_CartFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_empty__cart;
    }

    @Override
    public void initializeViews(View view) {
        back=view.findViewById(R.id.button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_empty_CartFragment_to_askQuestionFragment);
            }
        });
    }

    @Override
    public void setListeners() {

    }
    NavController getNavController(){
        return Navigation.findNavController(getActivity() ,R.id.my_nav_host);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_empty__cart, container, false);
    }
}
