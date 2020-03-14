package com.engineering.dokkan;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Notification extends Fragment {


    public Notification() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_notivication, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<Data> data = new ArrayList<Data>();
        data.add(new Data("Name", "invited you to something.", R.drawable.prodict2, "11:04 AM"));
        data.add(new Data("Name", "Suggested something.", R.drawable.product1, "11:04 AM"));
        data.add(new Data("Name", "Ceck out new collection", R.drawable.product3, "11:04 AM"));
        data.add(new Data("Item Name", "is back in stock", R.drawable.product1, "11:04 AM"));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(data);
        recyclerView.setAdapter(adapter);
        return v;
    }

}
