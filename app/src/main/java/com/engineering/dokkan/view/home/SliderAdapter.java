package com.engineering.dokkan.view.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.SliderItemModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {
    private ArrayList<SliderItemModel> sliderList ;
    private ViewPager2 viewPager2 ;

    public SliderAdapter(ArrayList<SliderItemModel> sliderList, ViewPager2 viewPager2) {
        this.sliderList = sliderList;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.slider_item , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        Picasso.get().load(sliderList.get(position).getImage()).into(holder.imageView);
        holder.slidename.setText(sliderList.get(position).getCategoryname());
    }

    @Override
    public int getItemCount() {
        return sliderList.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView ;
        private TextView slidename ;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview_slide);
            slidename=itemView.findViewById(R.id.tv_slide);
        }

    }
}
