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
import com.engineering.dokkan.data.models.FavitemModel;
import com.engineering.dokkan.view.base.BaseFragment;

import java.util.ArrayList;

public class Item_Fragment extends BaseFragment {

     private RecyclerView recyclerView;
     private ArrayList<FavitemModel> data;

    public Item_Fragment() {
    }


    @Override
    public int getLayoutId() {
        return R.layout.item_fragment;
    }

    @Override
    public void initializeViews(View view) {
        initializeFavItemRecyclerView(view);
    }

    @Override
    public void setListeners() {

    }
    private void initializeFavItemRecyclerView(View v) {
        recyclerView = v.findViewById(R.id.recyclerview_id);
        ArrayList<FavitemModel> data = getList();
        ItemRecycAdapter Adapter = new ItemRecycAdapter(data);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(Adapter);
    }


    private ArrayList<FavitemModel> getList() {
        ArrayList<FavitemModel> data = new ArrayList<FavitemModel>();
        data.add(new FavitemModel("Full Item Name", "150$", R.drawable.photo1, R.drawable.fav_icon));
        data.add(new FavitemModel("Full Item Name", "150$", R.drawable.photo2, R.drawable.fav_icon));
        data.add(new FavitemModel("Full Item Name", "150$", R.drawable.photo5, R.drawable.fav_icon));
        data.add(new FavitemModel("Full Item Name", "150$", R.drawable.photo4, R.drawable.fav_icon));
        data.add(new FavitemModel("Full Item Name", "150$", R.drawable.photo3, R.drawable.fav_icon));
        data.add(new FavitemModel("Full Item Name", "150$", R.drawable.photo1, R.drawable.fav_icon));
        return data;
    }
}
