package com.engineering.dokkan.view.orders;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.SharedPreference;
import com.engineering.dokkan.data.models.CartItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class OrdersFragment extends Fragment implements View.OnClickListener {


    private ImageView mBackArrow;
    private TextView mStatusOrder;
    private TextView mAddressTv;
    private RecyclerView orderRecyclerview;
    private Button addAddress;
    private OrderAdapter adapter ;

    private DatabaseReference databaseReference;
    public static List<CartItem> cartItemList;
    private List<Integer> orders;


    public OrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        initView(view);
        initRecView();
        fetchOrders();


        return view;
    }

    private void fetchOrders() {

        databaseReference.child("Users").child(SharedPreference.getInstance(getContext()).getUser()).child("cart")
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cartItemList = new ArrayList<CartItem>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    CartItem orderModel = dataSnapshot1.getValue(CartItem.class);
                    cartItemList.add(orderModel);
                }
                adapter.changeData(cartItemList);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initRecView() {
        adapter = new OrderAdapter(getContext());
        orderRecyclerview.setAdapter(adapter);

    }

    private void initView(@NonNull final View itemView) {
        mBackArrow = (ImageView) itemView.findViewById(R.id.arrow_back);
        mStatusOrder = (TextView) itemView.findViewById(R.id.order_status);
        orderRecyclerview = (RecyclerView) itemView.findViewById(R.id.recyclerview_id_order);
        addAddress = (Button) itemView.findViewById(R.id.add_your_address);
        addAddress.setOnClickListener(this);


    }

    NavController getNavController() {
        return Navigation.findNavController(getActivity(), R.id.my_nav_host);}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case  R.id.add_your_address :
                Toast.makeText(getContext(),"ww", Toast.LENGTH_SHORT).show();
                getNavController().navigate(R.id.action_ordersFragment_to_addressesfragment);
                break;

            default:
                break;
        }
    }

    /*
    private void sumOfOrder() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("cartList");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                orders = new ArrayList<Integer>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    String orderModel = dataSnapshot1.child("sum").getValue(String.class);
                    assert orderModel != null;
                    orders.add(Integer.parseInt(orderModel));
                }

                 int sum =0 ;
                for(int i =0 ;i<orders.size();i++){
                    sum+=orders.get(i);
                }
                orderTotal.setText("Order Total Price : "+ sum);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

     */
}