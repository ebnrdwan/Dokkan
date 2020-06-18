package com.engineering.dokkan.view.Favourite;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.FavitemModel;
import com.engineering.dokkan.view.base.BaseFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



import java.util.ArrayList;

public class Item_Fragment extends BaseFragment{


    private RecyclerView recyclerView;
    private ItemRecycAdapter mAdapter;
    private ArrayList<FavitemModel> data;
    DatabaseReference mDataReferance;




    public Item_Fragment() {
    }


    @Override
    public int getLayoutId() {
        return R.layout.item_fragment;
    }

    @Override
    public void initializeViews(View view) {
        ShowItemData(view);
    }

    @Override
    public void setListeners() {


    }


    private void ShowItemData(View v) {
        recyclerView = v.findViewById(R.id.recycler_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        data = new ArrayList<FavitemModel>();
        mDataReferance = FirebaseDatabase.getInstance().getReference().child("Fav_Item");
        mDataReferance.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    FavitemModel favModel = postSnapshot.getValue(FavitemModel.class);
                    data.add(favModel);
                }
                mAdapter = new ItemRecycAdapter(getContext(), data);
                recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_LONG).show();


            }
        });


    }





}