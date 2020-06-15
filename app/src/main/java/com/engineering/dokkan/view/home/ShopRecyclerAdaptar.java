package com.engineering.dokkan.view.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.ProductitemModel;
import com.engineering.dokkan.data.models.ShopitemModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShopRecyclerAdaptar extends RecyclerView.Adapter<ShopRecyclerAdaptar.shopHolder> {
    Context c ;
    private ArrayList<ShopitemModel> shopList;
    private ItemClickListener onItemClickListener;
    private FavouriteClickListener onFavClickListener ;


    public ShopRecyclerAdaptar(Context c, ArrayList<ShopitemModel> shopList, ItemClickListener onItemClickListener, FavouriteClickListener onFavClickListener) {
        this.c = c;
        this.shopList = shopList;
        this.onItemClickListener = onItemClickListener;
        this.onFavClickListener = onFavClickListener;
    }

    @NonNull
    @Override
    public shopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop, parent, false);
        return new shopHolder(v , onItemClickListener ,onFavClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final shopHolder holder, final int position) {
        Picasso.get().load(shopList.get(position).getCoverImage()).into(holder.shopImage);
        Picasso.get().load(shopList.get(position).getShopImage()).into(holder.Shop_Name_Image);
        holder.ShopName.setText(shopList.get(position).getShopName());
        holder.shoplocation.setText(shopList.get(position).getLocation());

        holder.setDatainView(shopList.get(position));

        //favourite Togglebtn
        holder.favourite.setChecked(false);
        holder.favourite.setBackgroundDrawable(ContextCompat.getDrawable(c, R.drawable.ic_favorite_empty));
        holder.favourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    holder.favourite.setBackgroundDrawable(ContextCompat.getDrawable(c, R.drawable.fav_icon));
                    shopList.get(position).setFav(true);
                    Log.d("IS FAV (IF WAS FALSE)", "FAV: " + shopList.get(position).isFav() );
                    Toast.makeText(c , "Fav Icon Succcesfully.." , Toast.LENGTH_LONG).show();

                }else{
                    holder.favourite.setBackgroundDrawable(ContextCompat.getDrawable(c, R.drawable.ic_favorite_empty));
                    shopList.get(position).setFav(false);
                    Log.d("IS FAV (IF TRUE)", "FAV: " + shopList.get(position).isFav() );
                    Toast.makeText(c , "Fav Empty Succcesfully.." , Toast.LENGTH_LONG).show();

                }
                holder.favouriteClickListener.onFavouriteClicked(position , shopList.get(position).isFav());
                Log.d("AFTER CALLING LISTENER", "FAV: " + shopList.get(position).isFav() );
            }
        });

        //old favourite Imagebtn
//        holder.favourite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if ( productsList.get(position).isFav()){ // if it was already true
//                    holder.favourite.setImageResource(R.drawable.ic_favorite_empty);
//                    productsList.get(position).setFav(false);
//                    Log.d("IS FAV (IF TRUE)", "FAV: " + productsList.get(position).isFav() );
//                } else { // if it was false
//                    holder.favourite.setImageResource(R.drawable.fav_icon);
//                    productsList.get(position).setFav(true);
//                    Log.d("IS FAV (IF FALSE)", "FAV: " + productsList.get(position).isFav() );
//
//                }
//                holder.favouriteClickListener.onFavouriteClicked(position , productsList.get(position).isFav());
//                Log.d("AFTER CALLING LISTENER", "FAV: " + productsList.get(position).isFav() );
//
//
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }


    class shopHolder extends RecyclerView.ViewHolder {
        ImageView shopImage;
        ImageView Shop_Name_Image ;
        TextView ShopName ;
        TextView shoplocation ;
        ToggleButton favourite  ;
        ItemClickListener itemClickListener;
        FavouriteClickListener favouriteClickListener ;
        View rootView ;
        ImageButton ShareBtn ;

        public shopHolder(@NonNull View itemView,final ItemClickListener itemClickListener
                , FavouriteClickListener favouriteClickListener ) {
            super(itemView);
            shopImage = itemView.findViewById(R.id.shopImage);
            Shop_Name_Image = itemView.findViewById(R.id.shop_name_image);
            ShopName = itemView.findViewById(R.id.shop_name);
            shoplocation = itemView.findViewById(R.id.shop_location);
            rootView = itemView ;
            this.favouriteClickListener = favouriteClickListener ;
            this.itemClickListener =itemClickListener;
            this.favourite = itemView.findViewById(R.id.favourite_icon);
            ShareBtn = itemView.findViewById(R.id.share_shop);

        }

        public void setDatainView(final ShopitemModel item) {
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(item);
                }
            });
        }
    }


    interface ItemClickListener {
        void onItemClick(ShopitemModel item);
    }

    interface FavouriteClickListener {
        void onFavouriteClicked(int position , boolean isFav );
    }

}
