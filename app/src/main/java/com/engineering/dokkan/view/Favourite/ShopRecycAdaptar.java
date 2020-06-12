package com.engineering.dokkan.view.Favourite;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.FavShopModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ShopRecycAdaptar extends RecyclerView.Adapter<ShopRecycAdaptar.shopHolder> {
    Context c;
    private ArrayList<FavShopModel> shopList;

    public ShopRecycAdaptar(Context c, ArrayList<FavShopModel> shopList) {
        this.c = c;
        this.shopList = shopList;
    }

    @NonNull
    @Override
    public shopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_favourite_shop, parent, false);
        return new ShopRecycAdaptar.shopHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopRecycAdaptar.shopHolder holder, final int position) {
        Picasso.get().load(shopList.get(position).getShop_name_image()).into(holder.Shop_Name_Image);
        Picasso.get().load(shopList.get(position).getShopCoverImage()).into(holder.ShopCoverImage);
        holder.ShopName.setText(shopList.get(position).getShopName());
        holder.Shop_location.setText(shopList.get(position).getShopLocation());
        holder.ShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.get().load(shopList.get(position).getShop_name_image()).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        Intent intent = new Intent("android.intent.action.SEND");
                        intent.setType("image/*");
                        intent.putExtra("android.intent.extra.STREAM", getlocalBitmapUri(bitmap));
                        c.startActivity(Intent.createChooser(intent, "share"));

                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
            }
        });
    }
        private Uri getlocalBitmapUri(Bitmap bitmap) {
            Uri bmuri = null;
            try {
                File file = new File(Environment.getExternalStorageDirectory() + File.separator + "image.jpg");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                fileOutputStream.close();
                bmuri = Uri.fromFile(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return bmuri;

        }


    @Override
    public int getItemCount() {
        return shopList.size();
    }


  class shopHolder extends RecyclerView.ViewHolder {
    ImageView ShopCoverImage;
    ImageView Shop_Name_Image ;
    TextView  ShopName ;
    TextView Shop_location ;
      ImageButton ShareBtn ;

    public shopHolder(@NonNull View itemView) {
        super(itemView);
        ShopCoverImage = itemView.findViewById(R.id.shopImage);
        Shop_Name_Image = itemView.findViewById(R.id.shop_name_image);
        ShopName = itemView.findViewById(R.id.shop_name);
        Shop_location = itemView.findViewById(R.id.shop_location);
        ShareBtn = itemView.findViewById(R.id.share_button);
    }
}
}