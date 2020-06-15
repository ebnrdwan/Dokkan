package com.engineering.dokkan.view.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.ShopitemModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShopRecyclerAdaptar extends RecyclerView.Adapter<ShopRecyclerAdaptar.shopHolder> {
    private ArrayList<ShopitemModel> shopList;

    public ShopRecyclerAdaptar(ArrayList<ShopitemModel> shopList) {
        this.shopList = shopList;
    }

    @NonNull
    @Override
    public shopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop, parent, false);
        return new shopHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull shopHolder holder, int position) {
        Picasso.get().load(shopList.get(position).getCoverImage()).into(holder.shopImage);
        Picasso.get().load(shopList.get(position).getShopImage()).into(holder.Shop_Name_Image);
        holder.ShopName.setText(shopList.get(position).getShopName());
        holder.shoplocation.setText(shopList.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }


    class shopHolder extends RecyclerView.ViewHolder {
        ImageView shopImage;
        ImageView Shop_Name_Image ;
        TextView ShopName ;
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
