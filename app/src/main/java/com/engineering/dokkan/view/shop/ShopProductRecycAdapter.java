package com.engineering.dokkan.view.shop;

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
import com.engineering.dokkan.data.models.ShopProductModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShopProductRecycAdapter extends RecyclerView.Adapter<ShopProductRecycAdapter.favouriteHolder> {
    Context c ;
    private ArrayList<ShopProductModel> productsList;
    private ItemClickListener onItemClickListener;
    DatabaseReference databaseReference;




    public ShopProductRecycAdapter( Context c , ArrayList<ShopProductModel> productsList
            , ItemClickListener onItemClickListener) {
        this.c = c;
        this.productsList = productsList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public favouriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop_products, parent, false);
        return new favouriteHolder(v , onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final favouriteHolder holder, final int position) {
        Picasso.get().load(productsList.get(position).getImage()).into(holder.Item_Image);
        holder.Item_Name.setText(productsList.get(position).getName());
        holder.Item_Price.setText(productsList.get(position).getPrice());

        //share the link of photo 
        holder.ShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,  productsList.get(position).getImage() );
                intent.setType("text/plain");
                c.startActivity(Intent.createChooser(intent, "Send To"));

            }
        });

        //RateBar
//        holder.ratingBar.setRating(productsList.get(position).getRate());
//        holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
//                productsList.get(position).setRate(ratingBar.getRating());
//                databaseReference = FirebaseDatabase.getInstance().getReference("products");
//                databaseReference.child(productsList.get(position).getKey()).child("rate").setValue(ratingBar.getRating())
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                // Toast.makeText(getActivity() , "Rate Saved Succcesfully.." , Toast.LENGTH_LONG).show();
//                            }
//                        });
//            }
//        });

        //Favourite
//        isFavourite(productsList.get(position).getKey() , holder.favourite , productsList.get(position)) ;
//        holder.favourite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if ( !productsList.get(position).isFav() ){ //  if it was false
//                    databaseReference = FirebaseDatabase.getInstance().getReference("products");
//                    databaseReference.child(productsList.get(position).getKey()).child("favourite").setValue(true)
//                            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                                    // Toast.makeText(getActivity() , "shop favourite Succcesfully.." , Toast.LENGTH_LONG).show();
//                                }
//                            });
//                    productsList.get(position).setFav(true);
//
//                } else { //if it was already true
//                    databaseReference = FirebaseDatabase.getInstance().getReference("products");
//                    databaseReference.child(productsList.get(position).getKey()).child("favourite").removeValue()
//                            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                                    // Toast.makeText(getActivity() , "shop favourite Succcesfully.." , Toast.LENGTH_LONG).show();
//                                }
//                            });
//                    productsList.get(position).setFav(false);
//                }
//
//
//            }
//        });
        //Product click
        holder.setDatainView(productsList.get(position));

    }

//    private void isFavourite(String key, final ImageView favourite, final ShopProductModel productitemModel) {
//
//        databaseReference = FirebaseDatabase.getInstance().getReference("products");
//        databaseReference.child(key).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if ( dataSnapshot.child("favourite").exists()){
//                    favourite.setImageResource(R.drawable.fav_icon);
//                    productitemModel.setFav(true);
//                } else  {
//                    favourite.setImageResource(R.drawable.ic_favorite_empty);
//                    productitemModel.setFav(true);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    class favouriteHolder extends RecyclerView.ViewHolder {
        TextView Item_Name;
        TextView Item_Price ;
        ImageView Item_Image;
        ImageView favourite  ;
        ItemClickListener itemClickListener;
        View rootView ;
        ImageButton ShareBtn ;
        RatingBar ratingBar ;

        public favouriteHolder(@NonNull View itemView,final ItemClickListener itemClickListener
        ) {
            super(itemView);
            rootView = itemView ;
            //this.favouriteClickListener = favouriteClickListener ;
            this.itemClickListener =itemClickListener;
            this.favourite = itemView.findViewById(R.id.iconImage);
            Item_Image = itemView.findViewById(R.id.itemImage);
            Item_Name = itemView.findViewById(R.id.itemName);
            Item_Price = itemView.findViewById(R.id.itemPrice);
            ShareBtn = itemView.findViewById(R.id.share_product);
            ratingBar = itemView.findViewById(R.id.ratingBar);

        }



        public void setDatainView(final ShopProductModel item) {
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(item);
                }
            });
        }
    }

    interface ItemClickListener {
        void onItemClick(ShopProductModel item);
    }



}

