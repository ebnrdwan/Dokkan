package com.engineering.dokkan.view.shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.ShopReviewModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShopReviewRecycAdapter extends RecyclerView.Adapter<ShopReviewRecycAdapter.ReviewViewHolder>{
    private DatabaseReference databaseReference;
    ArrayList<ShopReviewModel> reviewList;

    public ShopReviewRecycAdapter(ArrayList<ShopReviewModel> chatList) {
        this.reviewList = chatList;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop_review, null, false);
        return new ReviewViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ReviewViewHolder holder, final int position) {
        holder.rate.setRating(reviewList.get(position).getRate());
        holder.customer_name.setText(reviewList.get(position).getName());
        holder.comment.setText(reviewList.get(position).getComment());
        Picasso.get().load(reviewList.get(position).getImage()).into(holder.customerImage);

        holder.time_of_comment.setText(reviewList.get(position).getTime());
        holder.num_of_like.setText( "" + reviewList.get(position).getLikesOfComment());

        holder.item_name.setText(reviewList.get(position).getItemName());
        holder.item_desc.setText(reviewList.get(position).getItemDesc());
        Picasso.get().load(reviewList.get(position).getItemImage()).into(holder.item_image);

        isFavourite(reviewList.get(position).getKey(), holder.Fav_comment , holder.num_of_like , reviewList.get(position));
        holder.Fav_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!reviewList.get(position).getFav_comment()) { //  if it was false
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Reviews");
                    databaseReference.child(reviewList.get(position).getKey()).child("Fav_comment").setValue(true)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                }
                            });
                    reviewList.get(position).setFav_comment(true);

                } else { //if it was already true
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Reviews");
                    databaseReference.child(reviewList.get(position).getKey()).child("Fav_comment").removeValue()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                }
                            });
                    reviewList.get(position).setFav_comment(false);
                }
            }
        });




    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }


    private void isFavourite(String key, final ImageView favourite, final TextView num_of_like, final ShopReviewModel reviewModel) {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Reviews");
        databaseReference.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if ( dataSnapshot.child("Fav_comment").exists() ) {
                    favourite.setImageResource(R.drawable.fav_icon);
                    reviewModel.setFav_comment(true);
                } else  {
                    favourite.setImageResource(R.drawable.ic_favorite_empty);
                    reviewModel.setFav_comment(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView comment, num_of_like, customer_name , time_of_comment , item_name , item_desc ;
        ImageView Fav_comment , item_image;
        CircleImageView customerImage;
        RatingBar rate;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            customer_name = itemView.findViewById(R.id.tv_customer_name);
            comment = itemView.findViewById(R.id.tv_comment);
            time_of_comment = itemView.findViewById(R.id.tv_date);
            num_of_like = itemView.findViewById(R.id.num_of_like);
            rate=itemView.findViewById(R.id.ratingBar);
            Fav_comment=itemView.findViewById(R.id.fav_comment);
            customerImage = itemView.findViewById(R.id.cutomer_image);
            item_name = itemView.findViewById(R.id.item_name);
            item_desc = itemView.findViewById(R.id.item_desc);
            item_image = itemView.findViewById(R.id.item_image);





        }

    }

}