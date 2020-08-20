package com.engineering.dokkan.view.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.RecProdModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RVAdapterRecentlyView extends RecyclerView.Adapter<RVAdapterRecentlyView.ImageViewHolder> {
    private ArrayList<RecProdModel> imageList;
    private ImageClickListener onItemClickListener;
    DatabaseReference databaseReference;

    public RVAdapterRecentlyView(ArrayList<RecProdModel> imageList, ImageClickListener onItemClickListener) {
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
    public void onBindViewHolder(@NonNull final ImageViewHolder holder, final int position) {
        final Query query = FirebaseDatabase.getInstance().getReference("products")
                .orderByChild("productId").equalTo( imageList.get(position).getProductId() );
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for ( DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if ( snapshot.child("image1").exists()){
                        String url = snapshot.child("image1").getValue(String.class);
                        Picasso.get().load(url).into(holder.itemImage);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        holder.setDatainView(imageList.get(position));

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




        public ImageViewHolder(@NonNull View itemView ,final ImageClickListener itemClickListener) {
            super(itemView);
            this.itemClickListener =itemClickListener;
            rootView = itemView ;
            itemImage = (ImageView) itemView.findViewById(R.id.recently_view);

        }

        public void setDatainView(final RecProdModel item) {
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(item);
                }
            });
        }
    }

    interface ImageClickListener {
        void onItemClick(RecProdModel item);
    }
}
