package com.engineering.dokkan.view.Favourite;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.FavShopModel;
import com.engineering.dokkan.view.base.BaseFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FavoriteShopFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private ArrayList<FavShopModel> shopData = new ArrayList<>();
    private ShopRecycAdaptar myAdapter;
    private String currentuserId;
    SwipeRefreshLayout swipeRefreshLayout;

    public FavoriteShopFragment() {
    }


    @Override
    public int getLayoutId() {
        return R.layout.shop_fragment;
    }

    @Override
    public void initializeViews(View view) {
        currentuserId = getUserIdWrapper();
        Log.d("CURREN ID ", "the id is" + currentuserId);
        initializeRecyclerWithAdapter();
        ShowShopData(view);

    }

    @Override
    public void setListeners() {
        if (getView() != null) {
            swipeRefreshLayout = getView().findViewById(R.id.swipeRefresh);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    ShowShopData(getView());
                }
            });
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        if (getView() != null) {
            initializeViews(getView());
        }
    }

    private void ShowShopData(View v) {

        final Query query = FirebaseDatabase.getInstance().getReference("Users")
                .child(currentuserId).child("FavList")
                .orderByChild("isProduct").equalTo(false);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                swipeRefreshLayout.setRefreshing(false);
                shopData.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String shopID = dataSnapshot1.child("itemId").getValue(String.class);
                    Log.d("SHOP ID ", "the id is" + shopID);

                    final Query query1 = FirebaseDatabase.getInstance().getReference("shops")
                            .orderByChild("key").equalTo(shopID);
                    query1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                FavShopModel favShopModel = snapshot.getValue(FavShopModel.class);
                                Log.d("FAV MODEL ", "name" + favShopModel.getShopName());
                                shopData.add(favShopModel);
                            }
                            myAdapter = new ShopRecycAdaptar(getContext(), shopData);
                            recyclerView.setAdapter(myAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    });

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void initializeRecyclerWithAdapter() {
        if (getView() == null) return;
        recyclerView = getView().findViewById(R.id.rvFavoriteShops);
        myAdapter = new ShopRecycAdaptar(getContext(), shopData);
        recyclerView.setAdapter(myAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


    }


}
