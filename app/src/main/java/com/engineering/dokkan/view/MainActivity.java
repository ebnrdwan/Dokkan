package com.engineering.dokkan.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.OrderItemModel;
import com.engineering.dokkan.view.order.OrderAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<OrderItemModel>orderList;
    private DatabaseReference databaseReference;

    int total ;
    Button order ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        order = (Button)findViewById(R.id.order_total);

        recyclerView = findViewById(R.id.recyclerview_id_order);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        orderList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("cartList");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                orderList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    OrderItemModel item = snapshot.getValue(OrderItemModel.class);
                    orderList.add(item);

                }
                OrderAdapter adapter = new OrderAdapter(orderList ,ListenerOrder);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

    }


    OrderAdapter.ItemClickListener ListenerOrder = new OrderAdapter.ItemClickListener() {
        @Override
        public void onItemClick(OrderItemModel item) {
//            Toast.makeText(getApplicationContext() , "item Clicked", Toast.LENGTH_SHORT).show();
        }
    };

}
