package com.engineering.dokkan.view.home;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.ProductitemModel;
import com.engineering.dokkan.data.models.ShopitemModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShopRecyclerAdaptar extends RecyclerView.Adapter<ShopRecyclerAdaptar.shopHolder> {
    Context c ;
    private ArrayList<ShopitemModel> shopList;
    private ItemClickListener onItemClickListener;
    //private FavouriteClickListener onFavClickListener ;
    //private RateBarClickListener onRateClickListener ;

    DatabaseReference databaseReference ;

    public ShopRecyclerAdaptar(Context c, ArrayList<ShopitemModel> shopList,
                               ItemClickListener onItemClickListener) {
        this.c = c;
        this.shopList = shopList;
        this.onItemClickListener = onItemClickListener;
       // this.onFavClickListener = onFavClickListener;
        //this.onRateClickListener = onRateClickListener;
    }

    @NonNull
    @Override
    public shopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop, parent, false);
        return new shopHolder(v , onItemClickListener );
    }

    @Override
    public void onBindViewHolder(@NonNull final shopHolder holder, final int position) {
        Picasso.get().load(shopList.get(position).getShopImage()).into(holder.shopImage);
        Picasso.get().load(shopList.get(position).getShopImage()).into(holder.Shop_Name_Image);
        holder.ShopName.setText(shopList.get(position).getShopName());
        holder.shoplocation.setText(shopList.get(position).getLocation());

        holder.setDatainView(shopList.get(position));

        holder.ratingBar.setRating(shopList.get(position).getRate());

        holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
//                LayerDrawable stars = (LayerDrawable)holder.ratingBar.getProgressDrawable();
//                stars.getDrawable(2) .setColorFilter(Color.YELLOW , PorterDuff.Mode.SRC_ATOP);

                shopList.get(position).setRate(ratingBar.getRating());
               //holder.rateBarClickListener.onRateClicked(position , ratingBar.getRating() );
                databaseReference = FirebaseDatabase.getInstance().getReference("shops");
                databaseReference.child(shopList.get(position).getKey()).child("rate").setValue(ratingBar.getRating())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Toast.makeText(getActivity() , "Rate Saved Succcesfully.." , Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });


        //favourite Togglebtn
//        holder.favourite.setChecked(false);
//        holder.favourite.setBackgroundDrawable(ContextCompat.getDrawable(c, R.drawable.ic_favorite_empty));
//        holder.favourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    shopList.get(position).setFav(true);
//                    //holder.favouriteClickListener.onFavouriteClicked(position , shopList.get(position).isFav());
//                    holder.favourite.setBackgroundDrawable(ContextCompat.getDrawable(c, R.drawable.fav_icon));
//                    databaseReference = FirebaseDatabase.getInstance().getReference("shops");
//                    databaseReference.child(shopList.get(position).getKey()).child("favourite").setValue(true)
//                            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                        // Toast.makeText(getActivity() , "shop favourite Succcesfully.." , Toast.LENGTH_LONG).show();
//                                }
//                            });
//                } else {
//                    holder.favourite.setBackgroundDrawable(ContextCompat.getDrawable(c, R.drawable.ic_favorite_empty));
//                    shopList.get(position).setFav(false);
//                    //holder.favouriteClickListener.onFavouriteClicked(position , shopList.get(position).isFav());
//
//                    databaseReference = FirebaseDatabase.getInstance().getReference("shops");
//                    databaseReference.child(shopList.get(position).getKey()).child("favourite").setValue(false)
//                            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                                    // Toast.makeText(getActivity() , "shop favourite Succcesfully.." , Toast.LENGTH_LONG).show();
//                                }
//                            });
//                }
//               // holder.favouriteClickListener.onFavouriteClicked(position , shopList.get(position).isFav());
//            }
//        });

        isFavourite(shopList.get(position).getKey() , holder.favourite) ;

        holder.favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( !shopList.get(position).isFav() ){ //  if it was false
                    databaseReference = FirebaseDatabase.getInstance().getReference("shops");
                    databaseReference.child(shopList.get(position).getKey()).child("favourite").setValue(true)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // Toast.makeText(getActivity() , "shop favourite Succcesfully.." , Toast.LENGTH_LONG).show();
                                }
                            });
                    shopList.get(position).setFav(true);
                } else { //if it was already true
                    databaseReference = FirebaseDatabase.getInstance().getReference("shops");
                    databaseReference.child(shopList.get(position).getKey()).child("favourite").removeValue()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // Toast.makeText(getActivity() , "shop favourite Succcesfully.." , Toast.LENGTH_LONG).show();
                                }
                            });
                    shopList.get(position).setFav(false);
                }

            }
        });

    }


    private void isFavourite(String key, final ImageView favourite) {

        databaseReference = FirebaseDatabase.getInstance().getReference("shops");
        databaseReference.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if ( dataSnapshot.child("favourite").exists()){
                    favourite.setImageResource(R.drawable.fav_icon);
                } else  {
                    favourite.setImageResource(R.drawable.ic_favorite_empty);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
        ImageView favourite  ;
        ItemClickListener itemClickListener;
        //FavouriteClickListener favouriteClickListener ;
        //RateBarClickListener rateBarClickListener;
        View rootView ;
        ImageButton ShareBtn ;
        RatingBar ratingBar ;

        public shopHolder(@NonNull View itemView,final ItemClickListener itemClickListener) {
            super(itemView);
            shopImage = itemView.findViewById(R.id.shopImage);
            Shop_Name_Image = itemView.findViewById(R.id.shop_name_image);
            ShopName = itemView.findViewById(R.id.shop_name);
            shoplocation = itemView.findViewById(R.id.shop_location);
            rootView = itemView ;
            //this.favouriteClickListener = favouriteClickListener ;
            this.itemClickListener =itemClickListener;
            //this.rateBarClickListener =rateBarClickListener ;
            this.favourite = itemView.findViewById(R.id.favourite_icon);
            ShareBtn = itemView.findViewById(R.id.share_shop);
            ratingBar = itemView.findViewById(R.id.rating_bar);



        }

        public void setDatainView(final ShopitemModel item) {

            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(item);
                }
            });
        }
    }


    interface ItemClickListener {
        void onItemClick(ShopitemModel item);
    }
//
//    interface FavouriteClickListener {
//        void onFavouriteClicked(int position , boolean isFav );
//    }

//    interface RateBarClickListener {
//        void onRateClicked(int position , float rate );
//    }

}
