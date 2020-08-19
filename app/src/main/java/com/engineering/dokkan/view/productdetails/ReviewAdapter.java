package com.engineering.dokkan.view.productdetails;

import android.util.Log;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewAdapter  extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>{
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
        ReviewModel review_model = reviewList.get(position);

        holder.time_of_comment.setText(review_model.getDate());

        holder.rate.setRating( Float.parseFloat( review_model.getRate() ) );
        holder.rate.setIsIndicator(true);

        holder.comment.setText(review_model.getComment());

        String  userid = review_model.getUserID()  ;
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
                        Picasso.get().load(url).into(holder.CImage);
                    }

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



    class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView comment, customer_name,time_of_comment;
        CircleImageView CImage;
        RatingBar rate;
        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            customer_name = itemView.findViewById(R.id.tv_customer_name);
            comment = itemView.findViewById(R.id.tv_comment);
            time_of_comment = itemView.findViewById(R.id.tv_date);
            rate=itemView.findViewById(R.id.ratingBar);
            CImage = itemView.findViewById(R.id.circimage);
        }
    }
}

