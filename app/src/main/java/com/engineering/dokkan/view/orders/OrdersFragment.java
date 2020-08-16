package com.engineering.dokkan.view.orders;

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
import com.engineering.dokkan.data.models.OrderItemModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class OrdersFragment extends Fragment implements View.OnClickListener {


    private ImageView mBackArrow;
    private TextView mStatusOrder;
    private TextView mAddressTv;
    private RecyclerView orderRecyclerview;
    private Button orderTotal;
    private OrderAdapter adapter ;

    private DatabaseReference databaseReference;
    private List<OrderItemModel> ordersList;
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


        /*HashMap<String, Object> map = new HashMap<>();
                 map.put("key" , "orderID1");
                map.put("categoryid" , "categoryID1");
                map.put("shopId" , "shopID2");
                map.put("productID" ,  "productIDD");
                map.put("productImage","https://firebasestorage.googleapis.com/v0/b/profile-34740.appspot.com/o/categoriesImages%2Fembroidery1.jpg?alt=media");
                map.put("quantity" , "2");
                map.put("productName" , "love embroidery");
                map.put("quantityPrice" , "80");
                map.put("shopname" , "Red Cloud Studio");

        FirebaseDatabase.getInstance().getReference().child("cartList").child("orderID1")
                .setValue(map);

        HashMap<String, Object> map2 = new HashMap<>();

        map2.put("key" , "orderID2");
        map2.put("categoryid" , "categoryID1");
        map2.put("shopId" , "shopID2");
        map2.put("productID" ,  "productIDD");
        map2.put("productImage","https://firebasestorage.googleapis.com/v0/b/profile-34740.appspot.com/o/categoriesImages%2Fembroidery1.jpg?alt=media");
        map2.put("quantity" , "2");
        map2.put("productName" , "love embroidery");
        map2.put("quantityPrice" , "80");
        map2.put("shopname" , "Red Cloud Studio");
        FirebaseDatabase.getInstance().getReference().child("cartList").child("orderID2")
                .setValue(map2);


        HashMap<String, Object> map3 = new HashMap<>();

        map3.put("key" , "orderID3");
        map3.put("categoryid" , "categoryID1");
        map3.put("shopId" , "shopID2");
        map3.put("productID" ,  "productIDD");
        map3.put("productImage","https://firebasestorage.googleapis.com/v0/b/profile-34740.appspot.com/o/categoriesImages%2Fembroidery1.jpg?alt=media");
        map3.put("quantity" , "2");
        map3.put("productName" , "love embroidery");
        map3.put("quantityPrice" , "80");
        map3.put("shopname" , "Red Cloud Studio");
        FirebaseDatabase.getInstance().getReference().child("cartList").child("orderID3")
                .setValue(map3);

        HashMap<String, Object> map4 = new HashMap<>();
        map4.put("key" , "orderID4");
        map4.put("categoryid" , "categoryID1");
        map4.put("shopId" , "shopID2");
        map4.put("productID" ,  "productIDD");
        map4.put("productImage","https://firebasestorage.googleapis.com/v0/b/profile-34740.appspot.com/o/categoriesImages%2Fembroidery1.jpg?alt=media");
        map4.put("quantity" , "7");
        map4.put("productName" , "love embroidery");
        map4.put("quantityPrice" , "80");
        map4.put("shopname" , "Red Cloud Studio");
        FirebaseDatabase.getInstance().getReference().child("cartList").child("orderID4")
                .setValue(map4);
         */



        return view;
    }

    private void fetchOrders() {

        databaseReference.child("cartList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ordersList = new ArrayList<OrderItemModel>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    OrderItemModel orderModel = dataSnapshot1.getValue(OrderItemModel.class);
                    ordersList.add(orderModel);
                }
                adapter.changeData(ordersList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initRecView() {
        adapter = new OrderAdapter();
        orderRecyclerview.setAdapter(adapter);

    }

    private void initView(@NonNull final View itemView) {
        mBackArrow = (ImageView) itemView.findViewById(R.id.arrow_back);
        mStatusOrder = (TextView) itemView.findViewById(R.id.order_status);
        mAddressTv = (TextView) itemView.findViewById(R.id.tv_address);
        orderRecyclerview = (RecyclerView) itemView.findViewById(R.id.recyclerview_id_order);
        orderTotal = (Button) itemView.findViewById(R.id.order_total);
        orderTotal.setOnClickListener(this);
        mAddressTv.setOnClickListener(this);

    }

    NavController getNavController() {
        return Navigation.findNavController(getActivity(), R.id.my_nav_host);}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_total:
                sumOfOrder();

                break;
            case  R.id.tv_address :
                Toast.makeText(getContext(),"ww", Toast.LENGTH_SHORT).show();
                getNavController().navigate(R.id.action_ordersFragment_to_addressesfragment);
                break;

            default:
                break;
        }
    }

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
}