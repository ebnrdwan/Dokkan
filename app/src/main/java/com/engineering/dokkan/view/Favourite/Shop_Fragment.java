package com.engineering.dokkan.view.Favourite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;

import java.util.ArrayList;

public class Shop_Fragment extends Fragment {

    View v ;
    private RecyclerView recyclerView;
    private ArrayList<FavShopModel> data;


    public Shop_Fragment() {

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.shop_fragment,container,false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview_id);
        ShopRecycAdaptar Adapter = new ShopRecycAdaptar(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(Adapter);
        return v ;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        data = new ArrayList<>();
        data.add(new FavShopModel(R.drawable.photo1,R.drawable.icon3,"Shop Name","Cairo Egypt"));
        data.add(new FavShopModel(R.drawable.photo4,R.drawable.icon4,"Shop Name","Cairo Egypt"));



    }

}
