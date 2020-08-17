package com.engineering.dokkan.view.home;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.ProductitemModel;
import com.engineering.dokkan.data.models.SliderItemModel;
import com.engineering.dokkan.view.base.BaseFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class HomeFragment extends BaseFragment {
    //firebase
    private DatabaseReference dbReference;
    //slider
    private ArrayList<SliderItemModel> datasider;
    private ViewPager2 viewPager2;
    private SliderAdapter sliderAdapter;
    //tablayout
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPageAdapter viewadapter;
    //connect categories with tablayout
    Map<String, String> categoriesMap;
    private MainViewModel mainViewModel;

    //recentview
    private ArrayList<ProductitemModel> dataRecentView;
    private RecyclerView recyclerViewRecentView;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initializeViews(View view) {
        initViewModel();
        initializeTablayout(view);
        SliderWork(view);
        intializeRecentlyViewRecycler(view);


    }

    @Override
    public void setListeners() {


    }

    private void SliderWork(View view) {
        viewPager2 = view.findViewById(R.id.viewPagerSlider);
        showSlider(view);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                /*1-get key of current position from keySet
                 * 2-set adapter with the key*/

                mainViewModel.setCategoryID(datasider.get(position).getKey());
                viewadapter.setCatID(datasider.get(position).getKey());
                Log.d("PAGE_SELECTED", "onPageSelected: " + position);
                Log.d("SET CATID", "categoriesKEY: " + datasider.get(position).getKey());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            float pageMargin = getResources().getDimensionPixelOffset(R.dimen.pageMargin);
            float pageOffset = getResources().getDimensionPixelOffset(R.dimen.offset);

            @Override
            public void transformPage(@NonNull View page, float position) {
                String TAG = "VIEWPAGER_POS";
                float myOffset = position * -(2 * pageOffset + pageMargin);
                if (position < -1) {
                    page.setTranslationX(-myOffset);
                } else if (position <= 1) {
                    float scaleFactor = Math.max(0.7f, 1 - Math.abs(position - 0.14285715f));
                    Log.d(TAG, "transformPageVALUE: " + scaleFactor);
                    page.setTranslationX(myOffset);
                    page.setScaleY(scaleFactor);
                    page.setAlpha(scaleFactor);
                } else {
                    page.setAlpha(0);
                    page.setTranslationX(myOffset);
                }

            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
    }

    private void initializeTablayout(View view) {
        tabLayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.viewpager);
        viewadapter = new ViewPageAdapter(getActivity().getSupportFragmentManager());
    }

    public void intializeRecentlyViewRecycler(View view) {
        RVAdapterRecentlyView.ImageClickListener listenerRecView = new RVAdapterRecentlyView.ImageClickListener() {
            @Override
            public void onItemClick(ProductitemModel item) {

                Toast.makeText(getActivity(), "image Clicked", Toast.LENGTH_SHORT).show();
            }
        };
        showRecentView(view, listenerRecView);


    }

    private void initViewModel() {
        mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
    }

    private void showSlider(View view) {
        datasider = new ArrayList<>();
        dbReference = FirebaseDatabase.getInstance().getReference("categories");
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //categoriesMap = (Map<String, String>) dataSnapshot.getValue();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    SliderItemModel item = snapshot.getValue(SliderItemModel.class);
                    datasider.add(item);
                }
                initializeSlider();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }

    private void initializeSlider() {
        sliderAdapter = new SliderAdapter(datasider, viewPager2);
        viewPager2.setAdapter(sliderAdapter);
        if (!datasider.isEmpty())
            viewadapter.setCatID(datasider.get(0).getKey());
        viewPager.setAdapter(viewadapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void showRecentView(View view, final RVAdapterRecentlyView.ImageClickListener listenerRecView) {
        recyclerViewRecentView = (RecyclerView) view.findViewById(R.id.recently_view_recyclerView);
        recyclerViewRecentView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        dataRecentView = new ArrayList<>();
        dbReference = FirebaseDatabase.getInstance().getReference("RecentViewed");
        dbReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ProductitemModel item = dataSnapshot.getValue(ProductitemModel.class);
                Log.d("DATA SNAPSHOT", "values: " + item);

                dataRecentView.add(item);
                RVAdapterRecentlyView adapter = new RVAdapterRecentlyView(dataRecentView, listenerRecView);
                recyclerViewRecentView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}


