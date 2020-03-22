package com.engineering.dokkan.view.notifications;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.Notification;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {
    Button showNotificationBtn, showNoNotificationBtn;
    View notification;
    View noNotification;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initializeNotificationRecyclerView(view);
        setListeners();

    }

    private void initializeNotificationRecyclerView(View v) {
        RecyclerView recyclerView = v.findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<Notification> data = new ArrayList<Notification>();
        data.add(new Notification("Name", "invited you to something.", R.drawable.prodict2, "11:04 AM"));
        data.add(new Notification("Name", "Suggested something.", R.drawable.product1, "11:04 AM"));
        data.add(new Notification("Name", "Ceck out new collection", R.drawable.product3, "11:04 AM"));
        data.add(new Notification("Item Name", "is back in stock", R.drawable.product1, "11:04 AM"));
        NotificationAdapter adapter = new NotificationAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    private void initViews(View view) {
        showNotificationBtn = view.findViewById(R.id.btnShowNotification);
        showNoNotificationBtn = view.findViewById(R.id.btnShowNoNotification);
        notification = view.findViewById(R.id.notification_layout);
        noNotification = view.findViewById(R.id.noNotification_layout);
    }

    private void setListeners() {
        showNotificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navigateToNotification();
            }
        });
        showNoNotificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToNoNotification();
            }
        });
    }


    private void navigateToNoNotification() {
    noNotification.setVisibility(View.VISIBLE);
    notification.setVisibility(View.GONE);
    }

    private void navigateToNotification() {
        notification.setVisibility(View.VISIBLE);
        noNotification.setVisibility(View.GONE);
    }

}
