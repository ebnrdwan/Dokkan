package com.engineering.dokkan.view.productdetails;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewAdapter  extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>{
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    ArrayList<ReviewModel> reviewList;
    RatingBar ratingBar;


    public ReviewAdapter(ArrayList<ReviewModel> chatList) {
        this.reviewList = chatList;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_of_recycle, null, false);

        return new ReviewViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ReviewViewHolder holder, final int position) {
        ReviewModel ReviewModel = reviewList.get(position);
        holder.rate.setRating(ReviewModel.getRate());
        holder.customer_name.setText(ReviewModel.getName());
        holder.comment.setText(ReviewModel.getComment());
        Picasso.get().load(reviewList.get(position).getImage()).into(holder.CImage);
        holder.time_of_comment.setText(ReviewModel.getTime());

        //   holder.rate.setRating(ReviewModel.getRate());
        holder.rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float ratingValue = ratingBar.getRating();
            }
        });

        //Favourite
        isFavourite(reviewList.get(position).getKey(), holder.Fav_comment, reviewList.get(position));
        holder.Fav_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!reviewList.get(position).getFav_comment()) { //  if it was false
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("reviews");
                    databaseReference.child(reviewList.get(position).getKey()).child("Fav_comment").setValue(true)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                }
                            });
                    reviewList.get(position).setFav_comment(true);

                } else { //if it was already true
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("reviews");
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


        databaseReference = FirebaseDatabase.getInstance().getReference().child("reviews");
        databaseReference.child("likesOfComment").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if ( dataSnapshot.exists()){
                    holder.num_of_like.setText(((int) dataSnapshot.getChildrenCount()));

                }
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


    private void isFavourite(String key, final ImageView favourite, final ReviewModel reviewModel) {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("reviews");
        databaseReference.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if ( dataSnapshot.child("Fav_comment").exists()){
                    favourite.setImageResource(R.drawable.fav_icon2);
                    reviewModel.setFav_comment(true);
                } else  {
                    favourite.setImageResource(R.drawable.fav_icon3);
                    reviewModel.setFav_comment(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView comment, num_of_like, customer_name,time_of_comment;
        ImageView Fav_comment;
        CircleImageView CImage;
        RatingBar rate;
        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            customer_name = itemView.findViewById(R.id.tv_customer_name);
            comment = itemView.findViewById(R.id.tv_comment);
            time_of_comment = itemView.findViewById(R.id.tv_date);
            num_of_like = itemView.findViewById(R.id.num_of_like);
            rate=itemView.findViewById(R.id.ratingBar);
            Fav_comment=itemView.findViewById(R.id.fav_comment);
            CImage = itemView.findViewById(R.id.circimage);
        }
    }
}

