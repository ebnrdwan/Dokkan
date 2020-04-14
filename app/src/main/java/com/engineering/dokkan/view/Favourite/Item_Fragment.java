package com.engineering.dokkan.view.Favourite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;

import java.util.ArrayList;

public class Item_Fragment extends Fragment {

     View v ;
     private RecyclerView recyclerView;
     private ArrayList<FavouriteData> data;

    public Item_Fragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.item_fragment,container,false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview_id);
        MyAdapter Adapter = new MyAdapter(data);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(Adapter);
        return v ;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        data = new ArrayList<>();
        data.add(new FavouriteData("Full Item Name","150$",R.drawable.photo1,R.drawable.fav_icon));
        data.add(new FavouriteData("Full Item Name","150$",R.drawable.photo2,R.drawable.fav_icon));
        data.add(new FavouriteData("Full Item Name","150$",R.drawable.photo5,R.drawable.fav_icon));
        data.add(new FavouriteData("Full Item Name","150$",R.drawable.photo4,R.drawable.fav_icon));
        data.add(new FavouriteData("Full Item Name","150$",R.drawable.photo3,R.drawable.fav_icon));
        data.add(new FavouriteData("Full Item Name","150$",R.drawable.photo1,R.drawable.fav_icon));
    }
}
