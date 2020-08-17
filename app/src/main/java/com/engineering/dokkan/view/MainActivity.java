package com.engineering.dokkan.view;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.SharedPreference;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<Integer> list = new ArrayList<>();
        list.add(R.id.fragment_Welcome);
        list.add(R.id.registerFragment);
        list.add(R.id.loginFragment);
        list.add(R.id.resetPassword);

        if (SharedPreference.getInstance(this).isUserSaved()) {
            Navigation.findNavController(this, R.id.my_nav_host).navigate(R.id.action_fragment_Welcome_to_homeFragment);
        }
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        NavController navController = Navigation.findNavController(this, R.id.my_nav_host);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (list.contains(destination.getId())) {
                    bottomNavigationView.setVisibility(View.GONE);
                } else {
                    bottomNavigationView.setVisibility(View.VISIBLE);
                }


            }
        });

    }


}
/*
* <?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/colorf8f6f7"
    >

    <include layout="@layout/order_toolbar"
        android:id="@+id/toolbar"/>


    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerview_id_order"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_below="@id/toolbar"
        android:layout_above="@+id/order_total"
        android:layout_marginTop="@dimen/mt2"
        android:scrollbars="vertical">

    </androidx.recyclerview.widget.RecyclerView>

    <Button
        android:id="@+id/order_total"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:text="Order All"
        android:textAlignment="gravity"
        android:padding="@dimen/pad25"
        android:textColor="@color/color_F1EDED"
        android:background="@color/color_9a7c84"
        android:layout_margin="@dimen/margin_15"
        />



</RelativeLayout>
* */

/*
*package com.engineering.dokkan.view;

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
* */