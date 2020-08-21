package com.engineering.dokkan.view.orders.allOrders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.OrderItemModel;
import com.engineering.dokkan.view.base.BaseFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.engineering.dokkan.utils.Constants.ORDER_ID_KEY;


public class AllOrdersListFragment extends BaseFragment {


    private RecyclerView allOrderedRecyclerview;
    private AllOrdersAdapter adapter;

    private DatabaseReference databaseReference;
    public static List<OrderItemModel> orderItemModelList;
    public static int ORDERPOS;

    public AllOrdersListFragment() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_all_orders_list;
    }

    @Override
    public void initializeViews(View view) {
        databaseReference = FirebaseDatabase.getInstance().getReference();

        initView(view);

        initRecView();
        fetchOrders();
    }

    @Override
    public void setListeners() {

    }


    private void fetchOrders() {

        databaseReference.child("Orders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                orderItemModelList = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    OrderItemModel orderModel = dataSnapshot1.getValue(OrderItemModel.class);
                    if ((getUserIdWrapper()).equals(orderModel.getAddress().getCustomerID()))
                        orderItemModelList.add(orderModel);


                }

                adapter.setList(orderItemModelList);
                //Log.e("a",orderItemModelList.get(0).getCartItem().);
                //Toast.makeText(getContext(),orderItemModelList.size()+"",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initRecView() {
        adapter = new AllOrdersAdapter();
        allOrderedRecyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener((pos, orderItemModel) -> {


            ORDERPOS = pos;
            // HomeFragmentDirections.actionNavExploreToDetailsFragment(item);

            Bundle bundle = new Bundle();
            bundle.putString(ORDER_ID_KEY, orderItemModel.getKey());

            getNavController().navigate(R.id.action_orderList_To_OrderDetails, bundle);
        });
    }


    private void initView(@NonNull final View itemView) {
        allOrderedRecyclerview = (RecyclerView) itemView.findViewById(R.id.recyclerview_id_all_product_list);
    }


}

