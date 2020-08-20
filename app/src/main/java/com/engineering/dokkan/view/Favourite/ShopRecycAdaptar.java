package com.engineering.dokkan.view.Favourite;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.FavShopModel;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class ShopRecycAdaptar extends RecyclerView.Adapter<ShopRecycAdaptar.shopHolder> {
    Context c;
    private ArrayList<FavShopModel> shopList;

    public ShopRecycAdaptar(Context c, ArrayList<FavShopModel> shopList) {
        this.c = c;
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
    public void onBindViewHolder(@NonNull ShopRecycAdaptar.shopHolder holder, final int position) {
        Picasso.get().load(shopList.get(position).getShop_name_image()).into(holder.Shop_Name_Image);
        Picasso.get().load(shopList.get(position).getShopCoverImage()).into(holder.ShopCoverImage);
        holder.ShopName.setText(shopList.get(position).getShopName());
        holder.Shop_location.setText(shopList.get(position).getShopLocation());
        holder.ratingBar.setRating(shopList.get(position).getRate());


        holder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference("Fav_Shop")
                        .child(shopList.get(position).getmKey()).removeValue();

            }
        });
        holder.ShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, shopList.get(position).getShop_name_image());
                intent.setType("text/plain");
                c.startActivity(Intent.createChooser(intent, "Send To"));
            }
        });

        }


    @Override
    public int getItemCount() {
        return shopList.size();
    }


  class shopHolder extends RecyclerView.ViewHolder {
    ImageView ShopCoverImage;
    ImageView Shop_Name_Image ;
    TextView  ShopName ;
    TextView Shop_location ;
    ImageButton ShareBtn ;
    ImageView favBtn;
    RatingBar ratingBar ;


    public shopHolder(@NonNull View itemView) {
        super(itemView);
        ShopCoverImage = itemView.findViewById(R.id.shopImage);
        Shop_Name_Image = itemView.findViewById(R.id.shop_name_image);
        ShopName = itemView.findViewById(R.id.shop_name);
        Shop_location = itemView.findViewById(R.id.shop_location);
        ShareBtn = itemView.findViewById(R.id.share_button);
        favBtn = itemView.findViewById(R.id.favourite_icon);
        ratingBar = itemView.findViewById(R.id.rating_bar);

    }
}
}