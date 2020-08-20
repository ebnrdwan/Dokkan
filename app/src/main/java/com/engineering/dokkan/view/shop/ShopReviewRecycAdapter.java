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
import com.google.firebase.database.Query;
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

        holder.rate.setRating( Float.parseFloat(reviewList.get(position).getRate()) );
        holder.rate.setIsIndicator(true);

        String  userid = reviewList.get(position).getUserID()  ;
        final Query query = FirebaseDatabase.getInstance().getReference("Users")
                .orderByChild("uid").equalTo(userid);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for ( DataSnapshot snapshot : dataSnapshot.getChildren()){

                    if( snapshot.child("name").exists()) {
                        holder.customer_name.setText(  snapshot.child("name").getValue(String.class) );
                    }
                    if ( snapshot.child("userImage").exists()){
                        String url = snapshot.child("userImage").getValue(String.class);
                         Picasso.get().load(url).into(holder.customerImage);
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        holder.comment.setText(reviewList.get(position).getComment());
        holder.time_of_comment.setText(reviewList.get(position).getDate());

//
////
////

        databaseReference = FirebaseDatabase.getInstance().getReference("products");
        databaseReference.child(reviewList.get(position).getProductID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                holder.item_name.setText( dataSnapshot.child("name").getValue(String.class) );
                holder.item_desc.setText( dataSnapshot.child("description").getValue(String.class) );
                Picasso.get().load( dataSnapshot.child("image1").getValue(String.class) ).into(holder.item_image);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }



    class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView comment,  customer_name , time_of_comment ,item_name , item_desc ;
        ImageView item_image;
        CircleImageView customerImage;
        RatingBar rate;


        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            customer_name = itemView.findViewById(R.id.tv_customer_name);
            comment = itemView.findViewById(R.id.tv_comment);
            time_of_comment = itemView.findViewById(R.id.tv_date);
            rate=itemView.findViewById(R.id.ratingBar);
            customerImage = itemView.findViewById(R.id.cutomer_image);
            item_name = itemView.findViewById(R.id.item_name);
            item_desc = itemView.findViewById(R.id.item_desc);
            item_image = itemView.findViewById(R.id.item_image);





        }

    }

}