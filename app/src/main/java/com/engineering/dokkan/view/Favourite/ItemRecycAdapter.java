package com.engineering.dokkan.view.Favourite;

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
import com.engineering.dokkan.data.models.FavShopModel;
import com.engineering.dokkan.data.models.FavitemModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class ItemRecycAdapter extends RecyclerView.Adapter<ItemRecycAdapter.favouriteHolder> {
    Context c;
    private ArrayList<FavitemModel> favList;
    private String currentuserId ;


    public ItemRecycAdapter(Context c, ArrayList<FavitemModel> favList) {
        this.c = c;
        this.favList = favList;
        currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();


    }

    void updateData(ArrayList<FavitemModel> newprods){
        favList = newprods;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public favouriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_favourite_item, parent, false);
        return new ItemRecycAdapter.favouriteHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final favouriteHolder holder, final int position) {
        Picasso.get().load(favList.get(position).getImage1() ).into(holder.Item_Image);
        holder.Item_Name.setText(favList.get(position).getName());
        holder.Item_Price.setText(favList.get(position).getPrice());
        holder.favBtn.setImageResource(R.drawable.fav_icon);

        holder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Query query = FirebaseDatabase.getInstance().getReference("Users")
                        .child(currentuserId).child("FavList")
                        .orderByChild("itemId").equalTo( favList.get(position).getProductId() );
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
        });

        holder.ShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, favList.get(position).getImage1());
                intent.setType("text/plain");
                c.startActivity(Intent.createChooser(intent, "Send To"));
            }
        });

        holder.ratingBar.setRating(favList.get(position).getRate());

    }

    @Override
    public int getItemCount() {
        return favList.size();
    }

    public class favouriteHolder extends RecyclerView.ViewHolder{
        TextView Item_Name, Item_Price;
        ImageView Item_Image;
        ImageButton ShareBtn;
        ImageView favBtn;
        RatingBar ratingBar ;



        public favouriteHolder(@NonNull View itemView) {

            super(itemView);
            Item_Image = itemView.findViewById(R.id.itemImage);
            Item_Name = itemView.findViewById(R.id.itemName);
            Item_Price = itemView.findViewById(R.id.itemPrice);
            ShareBtn = itemView.findViewById(R.id.share_button);
            favBtn = itemView.findViewById(R.id.fav_heart);
            ratingBar = itemView.findViewById(R.id.ratingBar);


        }


    }

}