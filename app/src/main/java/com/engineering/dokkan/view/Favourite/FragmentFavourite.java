package com.engineering.dokkan.view.Favourite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.engineering.dokkan.R;
import com.engineering.dokkan.view.base.BaseFragment;
import com.google.android.material.tabs.TabLayout;

public class FragmentFavourite extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager view;
    private ViewPagerAdapter viewadapter;
    View v ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_favourite,container,false);
        tabLayout = v.findViewById(R.id.tablayout);
        view = v.findViewById(R.id.viewpager);
        viewadapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewadapter.AddFragment(new Item_Fragment(),"Item");
        viewadapter.AddFragment(new Shop_Fragment(),"Shop");
        view.setAdapter(viewadapter);
        tabLayout.setupWithViewPager(view);
        return v ;
    }



    @Override
    public int getLayoutId() {
        return 0;
    }


    @Override
    public void initializeViews(View view) {
    }


    @Override
    public void setListeners() {
    }
}
