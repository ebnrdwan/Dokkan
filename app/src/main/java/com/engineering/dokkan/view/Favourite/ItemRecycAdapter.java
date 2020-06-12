package com.engineering.dokkan.view.Favourite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.FavitemModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class ItemRecycAdapter extends RecyclerView.Adapter<ItemRecycAdapter.favouriteHolder> {
    private ArrayList<FavitemModel>favList ;

    public ItemRecycAdapter(ArrayList<FavitemModel> favList) {
        this.favList = favList;
    }

    @NonNull
    @Override
    public favouriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_favourite_item, parent, false);
        return new  ItemRecycAdapter.favouriteHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull favouriteHolder holder, int position) {
        holder.Item_Name.setText(favList.get(position).getName());
        holder.Item_Price.setText(favList.get(position).getPrice());
        Picasso.get().load(favList.get(position).getImage()).into(holder.Item_Image);
        final Item_Fragment itemfragment = new Item_Fragment();
        holder.ShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               itemfragment.onShareItem(view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return favList.size();
    }

    public class favouriteHolder extends RecyclerView.ViewHolder {
           TextView Item_Name ,Item_Price ;
               ImageView   Item_Image;
               ImageButton ShareBtn ;



        public favouriteHolder(@NonNull View itemView) {
            super(itemView);
            Item_Image=itemView.findViewById(R.id.itemImage);
            Item_Name = itemView.findViewById(R.id.itemName);
            Item_Price = itemView.findViewById(R.id.itemPrice);
            ShareBtn = itemView.findViewById(R.id.share_button);
        }
    }

}

