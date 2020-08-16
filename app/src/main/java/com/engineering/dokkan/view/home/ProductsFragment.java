package com.engineering.dokkan.view.home;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.ProductitemModel;
import com.engineering.dokkan.utils.Constants;
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
    ProductRecycAdapter.ItemClickListener ListenerProducts;


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
        ListenerProducts = new ProductRecycAdapter.ItemClickListener() {
            @Override
            public void onItemClick(final ProductitemModel item) {
                dbReference = FirebaseDatabase.getInstance().getReference("RecentViewed");
                Query query = dbReference.orderByChild("key").equalTo(item.getKey());
                query.addValueEventListener(new ValueEventListener() {

                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (!dataSnapshot.exists()) {
                            String key = dbReference.push().getKey();
                            dbReference.child(key).setValue(item).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                }
                            });
                        }

                        Bundle bundle = new Bundle();
                        bundle.putString("productId", item.getKey());
                        navigateTo(R.id.action_global_to_ProductDetails, null, null, bundle);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });

            }
        };
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
                ProductRecycAdapter Adapter = new ProductRecycAdapter(getContext(), data, ListenerProducts);
                recyclerView.setAdapter(Adapter);
                Log.d("STEP", "adapter: " + "done");

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


}
