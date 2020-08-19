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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShopRecyclerAdaptar extends RecyclerView.Adapter<ShopRecyclerAdaptar.shopHolder> {
    Context c ;
    private ArrayList<ShopitemModel> shopList;
    private ItemClickListener onItemClickListener;
    private String currentuserId ;
    DatabaseReference databaseReference ;

    public ShopRecyclerAdaptar(Context c, ArrayList<ShopitemModel> shopList,
                               ItemClickListener onItemClickListener) {
        this.c = c;
        this.shopList = shopList;
        this.onItemClickListener = onItemClickListener;
        currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

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
        Picasso.get().load(shopList.get(position).getShopImage()).into(holder.Shop_Name_Image);
        holder.ShopName.setText(shopList.get(position).getShopName());
        holder.shoplocation.setText(shopList.get(position).getLocation());

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



        isFavourite(shopList.get(position).getKey() , holder.favourite , shopList.get(position)) ;

        holder.favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( !shopList.get(position).isFav() ){ //  if it was false

                    databaseReference = FirebaseDatabase.getInstance().getReference("shops");
                    databaseReference.child(shopList.get(position).getKey()).child("isFav").setValue(true);

                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users")
                            .child(currentuserId).child("FavList");
                    String keyfav = databaseReference.push().getKey();
                    databaseReference.child(keyfav).child("key").setValue(keyfav);
                    databaseReference.child(keyfav).child("isProduct").setValue(false);
                    databaseReference.child(keyfav).child("itemId").setValue( shopList.get(position).getKey() );

                } else { //if it was already true

                    databaseReference = FirebaseDatabase.getInstance().getReference("shops");
                    databaseReference.child(shopList.get(position).getKey()).child("isFav").removeValue();

                    final Query query = FirebaseDatabase.getInstance().getReference("Users")
                            .child(currentuserId).child("FavList")
                            .orderByChild("itemId").equalTo( shopList.get(position).getKey() );
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for ( DataSnapshot snapshot : dataSnapshot.getChildren()){
                                snapshot.getRef().removeValue();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

            }
        });
        holder.setDatainView(shopList.get(position));


    }


    private void isFavourite(String key, final ImageView favourite, final ShopitemModel shopitemModel) {

        final Query query = FirebaseDatabase.getInstance().getReference("Users")
                .child(currentuserId).child("FavList")
                .orderByChild("itemId").equalTo( key );
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if ( dataSnapshot.exists() ){
                    favourite.setImageResource(R.drawable.fav_icon);
                    shopitemModel.setFav(true);
                } else {
                    favourite.setImageResource(R.drawable.ic_favorite_empty);
                    shopitemModel.setFav(false);


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
        ImageView Shop_Name_Image ;
        TextView ShopName ;
        TextView shoplocation ;
        ImageView favourite  ;
        ItemClickListener itemClickListener;
        View rootView ;
        ImageButton ShareBtn ;
        RatingBar ratingBar ;

        public shopHolder(@NonNull View itemView,final ItemClickListener itemClickListener) {
            super(itemView);
            Shop_Name_Image = itemView.findViewById(R.id.shop_name_image);
            ShopName = itemView.findViewById(R.id.shop_name);
            shoplocation = itemView.findViewById(R.id.shop_location);
            rootView = itemView ;
            this.itemClickListener =itemClickListener;
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
