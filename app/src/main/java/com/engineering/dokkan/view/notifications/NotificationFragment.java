package com.engineering.dokkan.view.notifications;


import android.view.View;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.Notification;
import com.engineering.dokkan.view.base.BaseFragment;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends BaseFragment {
    private Button showNotificationBtn, showNoNotificationBtn;
    private View notificationView;
    private View noNotificationView;

    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_notification;
    }

    @Override
    public void initializeViews(View view) {
        showNotificationBtn = view.findViewById(R.id.btnShowNotification);
        showNoNotificationBtn = view.findViewById(R.id.btnShowNoNotification);
        notificationView = view.findViewById(R.id.notification_layout);
        noNotificationView = view.findViewById(R.id.noNotification_layout);
        initializeNotificationRecyclerView(view);
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




    @Override
    public void setListeners() {
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
        noNotificationView.setVisibility(View.VISIBLE);
        notificationView.setVisibility(View.GONE);
    }

    private void navigateToNotification() {
        notificationView.setVisibility(View.VISIBLE);
        noNotificationView.setVisibility(View.GONE);
    }

}
