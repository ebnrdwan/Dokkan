package com.engineering.dokkan.view.Favourite;

import android.view.View;


import androidx.viewpager.widget.ViewPager;

import com.engineering.dokkan.R;
import com.engineering.dokkan.view.base.BaseFragment;
import com.google.android.material.tabs.TabLayout;

public class FavouriteFragment extends BaseFragment{

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
        viewadapter.AddFragment(new FavoriteItemFragment(), "Item");
        viewadapter.AddFragment(new FavoriteShopFragment(), "Shop");
        viewPager.setAdapter(viewadapter);
        tabLayout.setupWithViewPager(viewPager);

    }
        @Override
        public void setListeners () {

        }
    }
