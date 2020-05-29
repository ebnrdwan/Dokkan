package com.engineering.dokkan.view.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;

import java.util.ArrayList;

public class RVAdapterRecentlyView extends RecyclerView.Adapter<RVAdapterRecentlyView.ImageViewHolder> {
    private ArrayList<Integer> imageList;
    private ImageClickListener onItemClickListener;

    public RVAdapterRecentlyView(ArrayList<Integer> imageList, ImageClickListener onItemClickListener) {
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
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
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

        public void setDatainView(final int item) {

            itemImage.setImageResource(item);
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(item);
                }
            });
        }
    }

    interface ImageClickListener {
        void onItemClick(int item);
    }
}
