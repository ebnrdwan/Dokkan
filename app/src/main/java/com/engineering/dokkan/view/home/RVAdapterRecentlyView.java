package com.engineering.dokkan.view.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.ProductitemModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RVAdapterRecentlyView extends RecyclerView.Adapter<RVAdapterRecentlyView.ImageViewHolder> {
    private ArrayList<ProductitemModel> imageList;
    private ImageClickListener onItemClickListener;
    DatabaseReference databaseReference;

    public RVAdapterRecentlyView(ArrayList<ProductitemModel> imageList, ImageClickListener onItemClickListener) {
        this.imageList = imageList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recently_viewed, parent, false);
        return new ImageViewHolder(rootView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, final int position) {
        Picasso.get().load(imageList.get(position).getImage()).into(holder.itemImage);
        holder.setDatainView(imageList.get(position));
        if ( imageList.get(position).isFav() ){
            holder.favourite.setImageResource(R.drawable.fav_icon);
        } else {
            holder.favourite.setImageResource(R.drawable.ic_favorite_empty);
        }

        isFavourite(imageList.get(position).getKey() , holder.favourite , imageList.get(position)) ;
        holder.favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( !imageList.get(position).isFav() ){ //  if it was false
                    databaseReference = FirebaseDatabase.getInstance().getReference("products");
                    databaseReference.child(imageList.get(position).getKey()).child("favourite").setValue(true)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // Toast.makeText(getActivity() , "shop favourite Succcesfully.." , Toast.LENGTH_LONG).show();
                                }
                            });
                    imageList.get(position).setFav(true);

                } else { //if it was already true
                    databaseReference = FirebaseDatabase.getInstance().getReference("products");
                    databaseReference.child(imageList.get(position).getKey()).child("favourite").removeValue()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // Toast.makeText(getActivity() , "shop favourite Succcesfully.." , Toast.LENGTH_LONG).show();
                                }
                            });
                    imageList.get(position).setFav(false);
                }


            }
        });

    }

    private void isFavourite(String key, final ImageView favourite, final ProductitemModel productitemModel) {

        databaseReference = FirebaseDatabase.getInstance().getReference("products");
        databaseReference.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if ( dataSnapshot.child("favourite").exists()){
                    favourite.setImageResource(R.drawable.fav_icon);
                    productitemModel.setFav(true);
                } else  {
                    favourite.setImageResource(R.drawable.ic_favorite_empty);
                    productitemModel.setFav(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public int getItemCount() {
        return imageList.size();
    }





    //Inner Class ViewHolder
    public  static class ImageViewHolder extends RecyclerView.ViewHolder{
        public ImageView itemImage ;
        View rootView;
        ImageClickListener itemClickListener;
        ImageView favourite  ;




        public ImageViewHolder(@NonNull View itemView ,final ImageClickListener itemClickListener) {
            super(itemView);
            this.itemClickListener =itemClickListener;
            rootView = itemView ;
            this.favourite = itemView.findViewById(R.id.fav_recentview);
            itemImage = (ImageView) itemView.findViewById(R.id.recently_view);

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

    interface ImageClickListener {
        void onItemClick(ProductitemModel item);
    }
}
