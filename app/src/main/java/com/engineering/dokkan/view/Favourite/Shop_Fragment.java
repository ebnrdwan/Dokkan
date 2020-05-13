package com.engineering.dokkan.view.Favourite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.FavShopModel;
import com.engineering.dokkan.view.base.BaseFragment;

import java.util.ArrayList;

public class Shop_Fragment extends BaseFragment {
     RecyclerView recyclerView;
     ArrayList<FavShopModel> data;

    public Shop_Fragment() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.shop_fragment;
    }

    @Override
    public void initializeViews(View view) {
        initializeFavShopRecyclerView(view);

    }
    @Override
    public void setListeners() {

    }
    private void initializeFavShopRecyclerView(View v) {
        recyclerView = v.findViewById(R.id.recyclerview_id);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<FavShopModel> data = getList();
        ShopRecycAdaptar Adapter = new ShopRecycAdaptar(data);
        recyclerView.setAdapter(Adapter);
    }

     private ArrayList<FavShopModel> getList(){
         ArrayList<FavShopModel> data = new ArrayList<FavShopModel>();
         data.add(new FavShopModel(R.drawable.photo1,R.drawable.icon3,"Shop Name","Cairo Egypt"));
         data.add(new FavShopModel(R.drawable.photo4,R.drawable.icon4,"Shop Name","Cairo Egypt"));
         return data ;
     }


}
