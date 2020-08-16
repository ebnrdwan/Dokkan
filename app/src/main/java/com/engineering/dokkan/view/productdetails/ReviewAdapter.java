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

