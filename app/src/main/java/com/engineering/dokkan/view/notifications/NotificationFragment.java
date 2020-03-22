package com.engineering.dokkan.view.notifications;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.Notification;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {
    Button button_x, button_y;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_notification, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<Notification> data = new ArrayList<Notification>();
        data.add(new Notification("Name", "invited you to something.", R.drawable.prodict2, "11:04 AM"));
        data.add(new Notification("Name", "Suggested something.", R.drawable.product1, "11:04 AM"));
        data.add(new Notification("Name", "Ceck out new collection", R.drawable.product3, "11:04 AM"));
        data.add(new Notification("Item Name", "is back in stock", R.drawable.product1, "11:04 AM"));
        NotificationAdapter adapter = new NotificationAdapter(data);
        recyclerView.setAdapter(adapter);
        return v;
    }


    public void initViews(View view) {
        button_x = view.findViewById(R.id.button);
        button_y = view.findViewById(R.id.button1);
    }
    public void setListeners()
    {
        button_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navigateToNotification();
            }
        });
        button_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToNoNotification();
            }
        });
    }


    public void navigateToNoNotification() {
        No_Notifications fragy = new No_Notifications();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, fragy);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void navigateToNotification() {
        NotificationFragment fragx = new NotificationFragment();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, fragx);
        ft.addToBackStack(null);
        ft.commit();
    }

}
