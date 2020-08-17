package com.engineering.dokkan.view.Favourite;

import android.view.View;


import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.engineering.dokkan.R;
import com.engineering.dokkan.view.base.BaseFragment;
import com.google.android.material.tabs.TabLayout;

public class Favourite_Fragment extends BaseFragment{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewadapter;



    @Override
    public int getLayoutId() {
        return R.layout.fragment_favourite;
    }


    @Override
    public void initializeViews(View view) {
        tabLayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.viewpager);
        viewadapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewadapter.AddFragment(new Item_Fragment(), "Item");
        viewadapter.AddFragment(new Shop_Fragment(), "Shop");
        viewPager.setAdapter(viewadapter);
        tabLayout.setupWithViewPager(viewPager);

    }
        @Override
        public void setListeners () {

        }
    }
