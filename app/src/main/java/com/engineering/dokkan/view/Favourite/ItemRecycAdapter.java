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
import com.engineering.dokkan.data.models.FavitemModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ItemRecycAdapter extends RecyclerView.Adapter<ItemRecycAdapter.favouriteHolder> {
    Context c;
    private ArrayList<FavitemModel>favList ;

    public ItemRecycAdapter(Context c, ArrayList<FavitemModel> favList) {
        this.c = c;
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
    public void onBindViewHolder(@NonNull favouriteHolder holder, final int position) {
        Picasso.get().load(favList.get(position).getImage()).into(holder.Item_Image);
        holder.Item_Name.setText(favList.get(position).getName());
        holder.Item_Price.setText(favList.get(position).getPrice());
        holder.ShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.get()
                        .load(favList.get(position).getImage())
                        .into(new Target() {
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

