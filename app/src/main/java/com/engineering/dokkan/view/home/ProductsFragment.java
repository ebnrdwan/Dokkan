package com.engineering.dokkan.view.home;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.ProductitemModel;
import com.engineering.dokkan.view.base.BaseFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private ArrayList<ProductitemModel> data;
    private DatabaseReference dbReference;
    Bundle bundle;
    MainViewModel mainViewModel;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_products;
    }

    @Override
    public void initializeViews(View view) {
        showCategories(view);
    }

    @Override
    public void setListeners() {
    }


    private void showCategories(View rootView) {
        recyclerView = rootView.findViewById(R.id.recyclerview_id);
        data = new ArrayList<>();
        bundle = this.getArguments();
        String id = bundle.getString(Constants.CATEGORY_KEY);
        Log.d("ID", "CAT_ID: " + id);
        initViewModel();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        getProductsByCategoryId(id);
    }

    void getProductsByCategoryId(String catID) {
        dbReference = FirebaseDatabase.getInstance().getReference("products");
        Query query = FirebaseDatabase.getInstance().getReference("products")
                .orderByChild("categoryid").equalTo(catID);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ProductitemModel categ = snapshot.getValue(ProductitemModel.class);
                    data.add(categ);
                }
                ProductRecycAdapter Adapter = new ProductRecycAdapter( getContext() ,data , ListenerProducts);
                recyclerView.setAdapter(Adapter);
                Log.d("STEP", "adapter: " + "done" );

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

    void initViewModel() {
        mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        mainViewModel.catId.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String newCatID) {
                getProductsByCategoryId(newCatID);
            }
        });
    }


    //Listeners

    ProductRecycAdapter.ItemClickListener ListenerProducts = new ProductRecycAdapter.ItemClickListener() {
        @Override
        public void onItemClick(ProductitemModel item) {
            Toast.makeText(getActivity(), "item Clicked", Toast.LENGTH_SHORT).show();
            dbReference = FirebaseDatabase.getInstance().getReference("RecentViewed");
            String key = dbReference.push().getKey();
            dbReference.child(key).setValue(item).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
//                    Toast.makeText(getActivity() , "Product Added Succcesfully.." , Toast.LENGTH_LONG).show();
                }
            });



        }
    };

//    ProductRecycAdapter.FavouriteClickListener ListenerFavourite = new ProductRecycAdapter.FavouriteClickListener() {
//        @Override
//        public void onFavouriteClicked(final int position, final boolean isFav) {
//            Log.d("STEP", "ListenerFavourite: " + "done" );
//
//            dbReference = FirebaseDatabase.getInstance().getReference("products");
//            dbReference.child(data.get(position).getKey()).child("favourite").setValue(isFav)
//                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            Toast.makeText(getActivity() , "Product favourite Succcesfully.." , Toast.LENGTH_LONG).show();
//                        }
//                    });
//
//


//        }
//    };


}
