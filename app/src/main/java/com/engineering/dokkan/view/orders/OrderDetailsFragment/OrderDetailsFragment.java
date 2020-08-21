package com.engineering.dokkan.view.orders.OrderDetailsFragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.CartItem;
import com.engineering.dokkan.data.models.OrderItemModel;

import com.engineering.dokkan.utils.Constants;
import com.engineering.dokkan.utils.OnItemClickListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.engineering.dokkan.utils.Constants.ORDER_ID_KEY;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderDetailsFragment extends Fragment implements OnItemClickListener<CartItem> {


    private CircleImageView customerProfileImg;
    private TextView customerName;
    private TextView customerPhone;
    private TextView customerAdress;
    private TextView orderDate;
    private RecyclerView cartItemsRec;

    String orderId;
    OrderItemModel orderItemModel;
    List<CartItem> cartItems = new ArrayList<>();
    private DatabaseReference databaseReference;

   CartAdapter adapter;
    private List<OrderItemModel> cartItemList;

    public OrderDetailsFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order_details, container, false);

        assert getArguments() != null;
        orderId = (String) getArguments().getString(ORDER_ID_KEY);

        initView(view);
        initRecView();
        fetchCarts();
        return view;
    }

    private void fetchCarts() {

        databaseReference = FirebaseDatabase.getInstance().getReference("Orders").child(orderId);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                OrderItemModel orderModel = snapshot.getValue(OrderItemModel.class);
                orderItemModel = orderModel;
                if (orderModel != null) {
                    bindData(orderModel);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void initView(@NonNull final View itemView) {
        customerProfileImg = (CircleImageView) itemView.findViewById(R.id.imgCustomerProfile);
        customerName = (TextView) itemView.findViewById(R.id.tvCustomerName);

        customerPhone = (TextView) itemView.findViewById(R.id.tvCustomerPhone);

        customerAdress = (TextView) itemView.findViewById(R.id.tvCustomerAdress);
        orderDate = (TextView) itemView.findViewById(R.id.tvOrderDateValue);
        cartItemsRec = (RecyclerView) itemView.findViewById(R.id.rvCartItems);
    }

    private void bindData(OrderItemModel orderItemModel) {
        customerName.setText(orderItemModel.getAddress().getCustomerName());
        customerPhone.setText(orderItemModel.getAddress().getCustomerNumber());
        customerAdress.setText(String.format("%s , %s", orderItemModel.getAddress().getCustomerCountry(), orderItemModel.getAddress().getCustomerAddress()));
        orderDate.setText(orderItemModel.getDate());
        adapter.setList(orderItemModel.getCartItem());
    }


    private void initRecView() {
        adapter = new CartAdapter(new ArrayList<CartItem>(), this);
        cartItemsRec.setAdapter(adapter);

    }


    @Override
    public void onItemClicked(View view, CartItem item) {

    }
}
