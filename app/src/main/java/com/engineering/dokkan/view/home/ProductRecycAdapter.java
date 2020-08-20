package com.engineering.dokkan.view.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ProductRecycAdapter extends RecyclerView.Adapter<ProductRecycAdapter.favouriteHolder> {
    Context c ;
    private ArrayList<ProductitemModel> productsList;
    private ItemClickListener onItemClickListener;
    DatabaseReference databaseReferenceProd;
    private String currentuserId ;




    public ProductRecycAdapter( Context c , ArrayList<ProductitemModel> productsList
            , ItemClickListener onItemClickListener) {
        this.c = c;
        this.productsList = productsList;
        this.onItemClickListener = onItemClickListener;
        currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    @NonNull
    @Override
    public favouriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new favouriteHolder(v , onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final favouriteHolder holder, final int position) {
        Picasso.get().load(productsList.get(position).getImage1()).into(holder.Item_Image);
        holder.Item_Name.setText(productsList.get(position).getName());
        holder.Item_Price.setText(productsList.get(position).getPrice());

        //share the link of photo 
        holder.ShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,  productsList.get(position).getImage1() );
                intent.setType("text/plain");
                c.startActivity(Intent.createChooser(intent, "Send To"));

            }
        });

        //RateBar
        isRating(holder.ratingBar , productsList.get(position));
//        holder.ratingBar.setRating(productsList.get(position).getRate());
        holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                productsList.get(position).setRate(ratingBar.getRating());

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("RatedList")
                .child(productsList.get(position).getProductId() ).child("ListOfRated");
               // String key = databaseReference.push().getKey();
                databaseReference.child(currentuserId).child("key").setValue( currentuserId);
                databaseReference.child(currentuserId).child("isProduct").setValue(true);
                databaseReference.child(currentuserId).child("customerId").setValue(  currentuserId );
                databaseReference.child(currentuserId).child("Rate").setValue( ratingBar.getRating() );



            }
        });

        //Favourite
        isFavourite(productsList.get(position).getProductId() , holder.favourite , productsList.get(position)) ;

        holder.favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( ! productsList.get(position).isFav() ){ //  if it was false

                    databaseReferenceProd = FirebaseDatabase.getInstance().getReference("products");
                    databaseReferenceProd.child( productsList.get(position).getProductId() ).child("isFav").setValue(true);

                                 DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users")
                                            .child(currentuserId).child("FavList");
                                  String key = databaseReference.push().getKey();
                                  databaseReference.child(key).child("key").setValue(key);
                                  databaseReference.child(key).child("isProduct").setValue(true);
                                  databaseReference.child(key).child("itemId").setValue(  productsList.get(position).getProductId() );

                }
                else { //if it was already true

                    databaseReferenceProd = FirebaseDatabase.getInstance().getReference("products");
                    databaseReferenceProd.child( productsList.get(position).getProductId() ).child("isFav").removeValue();

                     final Query query = FirebaseDatabase.getInstance().getReference("Users")
                                            .child(currentuserId).child("FavList")
                             .orderByChild("itemId").equalTo( productsList.get(position).getProductId() );
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


            }
        });
        //Product click
        holder.setDatainView(productsList.get(position));

    }

    private void isRating(final RatingBar ratingBar, ProductitemModel productitemModel) {

        Query query = FirebaseDatabase.getInstance().getReference("RatedList").child(productitemModel.getProductId())
                .child("ListOfRated").orderByChild("customerId").equalTo(currentuserId);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if ( dataSnapshot.exists()){
                            for ( DataSnapshot snapshot : dataSnapshot.getChildren()){

                                if (snapshot.child("Rate").getValue(Float.class) != null){
                                    ratingBar.setRating( snapshot.child("Rate").getValue(Float.class) );
                                }
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }

    private void isFavourite(final String prod_Id, final ImageView favourite, final ProductitemModel productitemModel) {

        final Query query = FirebaseDatabase.getInstance().getReference("Users")
                            .child(currentuserId).child("FavList")
                            .orderByChild("itemId").equalTo( prod_Id );
                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                favourite.setImageResource(R.drawable.fav_icon);
                                productitemModel.setFav(true);
                            } else {
                                favourite.setImageResource(R.drawable.ic_favorite_empty);
                                productitemModel.setFav(false);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });




//        databaseReferenceProd = FirebaseDatabase.getInstance().getReference("products");
//        databaseReferenceProd.child(prod_Id).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                if ( dataSnapshot.child("isFav").exists() ){

//                    final Query query = FirebaseDatabase.getInstance().getReference("Users")
//                            .child(currentuserId).child("FavList")
//                            .orderByChild("itemId").equalTo( productitemModel.getProductId() );
//                    query.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            if(dataSnapshot.exists()){
//                                favourite.setImageResource(R.drawable.fav_icon);
//                                productitemModel.setFav(true);
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//                    });



//                } else  {
//                    favourite.setImageResource(R.drawable.ic_favorite_empty);
//                    productitemModel.setFav(false);
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }



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



        public void setDatainView(final ProductitemModel item) {
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(item);
                }
            });
        }
    }

    interface ItemClickListener {
        void onItemClick(ProductitemModel item);
    }



}

