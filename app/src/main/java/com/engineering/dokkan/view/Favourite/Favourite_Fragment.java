package com.engineering.dokkan.view.Favourite;

import android.view.View;
import android.widget.Button;

import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
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


    NavController getNavController() {
        return Navigation.findNavController(getActivity(), R.id.nav_host_fragment);}


    @Override
    public void initializeViews(View view) {
        tabLayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.viewpager);
        viewadapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewadapter.AddFragment(new Item_Fragment(), "Item");
        viewadapter.AddFragment(new Shop_Fragment(), "Shop");
        viewPager.setAdapter(viewadapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        getNavController().navigate(R.id.action_favourite_Fragment_to_item_Fragment);
                        break;
                    case 1:
                        getNavController().navigate(R.id.action_favourite_Fragment_to_shop_Fragment);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });}

            @Override
            public void setListeners() {

    }
}
