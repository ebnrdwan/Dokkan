package com.engineering.dokkan.view.Favourite;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.FavShopModel;
import com.engineering.dokkan.view.base.BaseFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Shop_Fragment extends BaseFragment {
        private RecyclerView recyclerView;
        private ArrayList<FavShopModel> ShopData;
        private ShopRecycAdaptar myAdapter ;
        DatabaseReference mReferance;

    public Shop_Fragment() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.shop_fragment;
    }

    @Override
    public void initializeViews(View view) {
        ShowShopData(view);

    }
    @Override
    public void setListeners() {

    }
    private void ShowShopData(View v) {
        recyclerView = v.findViewById(R.id.recycler_id);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        ShopData = new ArrayList<FavShopModel>();

        mReferance = FirebaseDatabase.getInstance().getReference("Fav_Shop");
        mReferance.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ShopData.clear();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    FavShopModel favModel = dataSnapshot1.getValue(FavShopModel.class);
                    ShopData.add(favModel);
                }
                myAdapter = new ShopRecycAdaptar(getContext(),ShopData);
                recyclerView.setAdapter(myAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}
