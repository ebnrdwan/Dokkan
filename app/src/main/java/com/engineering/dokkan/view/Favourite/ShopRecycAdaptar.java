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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class ShopRecycAdaptar extends RecyclerView.Adapter<ShopRecycAdaptar.shopHolder> {
    Context c;
    private ArrayList<FavShopModel> shopList;
    private String currentuserId ;


    public ShopRecycAdaptar(Context c, ArrayList<FavShopModel> shopList) {
        this.c = c;
        this.shopList = shopList;
        currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

    }

    @NonNull
    @Override
    public shopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_favourite_shop, parent, false);
        return new ShopRecycAdaptar.shopHolder(v);
    }


    void updateData(ArrayList<FavShopModel> newShops){
        shopList = newShops;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ShopRecycAdaptar.shopHolder holder, final int position) {
        Picasso.get().load( shopList.get(position).getShopImage() ).into ( holder.Shop_Name_Image );
        holder.ShopName.setText(shopList.get(position).getShopName());
        holder.Shop_location.setText(shopList.get(position).getLocation());
        holder.ratingBar.setRating(shopList.get(position).getRate());
        holder.favBtn.setImageResource(R.drawable.fav_icon);


        holder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               final Query query = FirebaseDatabase.getInstance().getReference("Users")
                        .child(currentuserId).child("FavList")
                        .orderByChild("itemId").equalTo( shopList.get(position).getKey() );
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for ( DataSnapshot snapshot : dataSnapshot.getChildren() ){
                            snapshot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        holder.ShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, shopList.get(position).getShopImage());
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
    ImageView Shop_Name_Image ;
    TextView  ShopName ;
    TextView Shop_location ;
    ImageButton ShareBtn ;
    ImageView favBtn;
    RatingBar ratingBar ;


    public shopHolder(@NonNull View itemView) {
        super(itemView);
        Shop_Name_Image = itemView.findViewById(R.id.shop_name_imagefav);
        ShopName = itemView.findViewById(R.id.shop_namefav);
        Shop_location = itemView.findViewById(R.id.shop_locationfav);
        ShareBtn = itemView.findViewById(R.id.share_buttonfav);
        favBtn = itemView.findViewById(R.id.favourite_iconn);
        ratingBar = itemView.findViewById(R.id.rating_barfav);

    }


}
}