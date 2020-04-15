package com.engineering.dokkan.view.Favourite;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.engineering.dokkan.R;
import com.engineering.dokkan.view.base.BaseFragment;
import com.google.android.material.tabs.TabLayout;

public class mainFragment extends BaseFragment {

      private TabLayout tabLayout;
      private ViewPager view;
      private ViewPagerAdapter viewadapter;
      View v ;
      private Button askq_btn , fav_btn , profile_btn ;

    public mainFragment() {
        // Required empty public constructor
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_favourite, container, false);
        tabLayout = v.findViewById(R.id.tablayout);
        view = v.findViewById(R.id.viewpager);
        viewadapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewadapter.AddFragment(new Item_Fragment(), "Item");
        viewadapter.AddFragment(new Shop_Fragment(), "Shop");
        view.setAdapter(viewadapter);
        tabLayout.setupWithViewPager(view);
        return v;

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
                getNavController().navigate(R.id.action_mainFragment_to_item_Fragment);

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
