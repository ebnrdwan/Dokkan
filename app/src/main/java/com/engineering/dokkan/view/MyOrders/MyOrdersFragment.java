package com.engineering.dokkan.view.MyOrders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.OrdersModel;
import com.engineering.dokkan.view.base.BaseFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersFragment extends BaseFragment {
    RecyclerView recyclerView;
    List<OrdersModel> Orders;


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    public MyOrdersFragment() {
        // Required empty public constructor
    }
 @Override
    public int getLayoutId() {
        return R.layout.fragment_my_orders;
    }

    @Override
    public void initializeViews(View view) {
        recyclerView = view.findViewById(R.id.recycler);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Orders");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Orders = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    OrdersModel ordersModel = snapshot.getValue(OrdersModel.class);
                    Orders.add(ordersModel);

                }
                OrdersAdapter ordersAdapter = new OrdersAdapter(Orders);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(ordersAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
    NavController getNavController(){
        return Navigation.findNavController(getActivity() ,R.id.nav_host_fragment);
    }







    @Override
    public void setListeners() {

    }


}