package com.engineering.dokkan.view.Favourite;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.FavShopModel;
import com.engineering.dokkan.data.models.FavitemModel;
import com.engineering.dokkan.view.base.BaseFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;



import java.util.ArrayList;

public class FavoriteItemFragment extends BaseFragment{


    private RecyclerView recyclerView;
    private ItemRecycAdapter mAdapter;
    private ArrayList<FavitemModel> data;
    DatabaseReference mDataReferance;
    private String currentuserId ;





    public FavoriteItemFragment() {
    }


    @Override
    public int getLayoutId() {
        return R.layout.item_fragment;
    }

    @Override
    public void initializeViews(View view) {
        currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ShowItemData(view);
    }

    @Override
    public void setListeners() {


    }


    private void ShowItemData(View v) {
        recyclerView = v.findViewById(R.id.recycler_idfav);
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        data = new ArrayList<>();

        mAdapter = new ItemRecycAdapter(getContext(), data);
        recyclerView.setAdapter(mAdapter);

        final Query query = FirebaseDatabase.getInstance().getReference("Users")
                .child(currentuserId).child("FavList")
                .orderByChild("isProduct").equalTo(true);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    String prodID = dataSnapshot1.child("itemId").getValue(String.class);
                    Log.d("PROD ID " , "the id is" + prodID);

                    final Query query1 = FirebaseDatabase.getInstance().getReference("products")
                            .orderByChild("productId").equalTo(prodID);
                    query1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for ( DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                FavitemModel favitemModel = snapshot.getValue(FavitemModel.class);
//                                Log.d("FAV MODEL ", "name" + favitemModel.getShopName());
                                data.add(favitemModel);
                            }
                            mAdapter.updateData(data);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }





}