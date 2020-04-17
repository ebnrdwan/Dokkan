package com.engineering.dokkan.view.Favourite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;

import java.util.ArrayList;

public class ShopRecycAdaptar extends RecyclerView.Adapter<ShopRecycAdaptar.shopHolder> {
    private ArrayList<FavShopModel> shopList;

    public ShopRecycAdaptar(ArrayList<FavShopModel> shopList) {
        this.shopList = shopList;
    }

    @NonNull
    @Override
    public shopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_favourite_shop, parent, false);
        return new ShopRecycAdaptar.shopHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopRecycAdaptar.shopHolder holder, int position) {
        holder.shopImage.setImageResource(shopList.get(position).getShopImage());
        holder.Shop_Name_Image.setImageResource(shopList.get(position).getShop_name_image());
        holder.ShopName.setText(shopList.get(position).getShop_name());
        holder.shoplocation.setText(shopList.get(position).getShop_location());
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }


  class shopHolder extends RecyclerView.ViewHolder {
    ImageView shopImage;
    ImageView Shop_Name_Image ;
    TextView  ShopName ;
    TextView shoplocation ;

    public shopHolder(@NonNull View itemView) {
        super(itemView);
        shopImage = itemView.findViewById(R.id.shopImage);
        Shop_Name_Image = itemView.findViewById(R.id.shop_name_image);
        ShopName = itemView.findViewById(R.id.shop_name);
        shoplocation = itemView.findViewById(R.id.shop_location);
    }
}
}