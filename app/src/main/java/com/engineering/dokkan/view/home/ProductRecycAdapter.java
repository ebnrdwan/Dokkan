package com.engineering.dokkan.view.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
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
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ProductRecycAdapter extends RecyclerView.Adapter<ProductRecycAdapter.favouriteHolder> {
    Context c ;
    private ArrayList<ProductitemModel> productsList;
    private ItemClickListener onItemClickListener;
    private FavouriteClickListener onFavClickListener ;




    public ProductRecycAdapter( Context c , ArrayList<ProductitemModel> productsList
            , ItemClickListener onItemClickListener
            , FavouriteClickListener onFavClickListener) {
        this.c = c;
        this.productsList = productsList;
        this.onItemClickListener = onItemClickListener;
        this.onFavClickListener = onFavClickListener ;
    }

    @NonNull
    @Override
    public favouriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new favouriteHolder(v , onItemClickListener ,onFavClickListener );
    }

    @Override
    public void onBindViewHolder(@NonNull final favouriteHolder holder, final int position) {
        Picasso.get().load(productsList.get(position).getImage()).into(holder.Item_Image);
        holder.Item_Name.setText(productsList.get(position).getName());
        holder.Item_Price.setText(productsList.get(position).getPrice());

        //share the link of photo 
        holder.ShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//                Picasso.get()
//                        .load(productsList.get(position).getImage())
//                        .into(new Target() {
//                            @Override
//                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                                Intent intent = new Intent("android.intent.action.SEND");
//                                intent.setType("image/*");
//                                intent.putExtra("android.intent.extra.STREAM", getlocalBitmapUri(bitmap));
//                                c.startActivity(Intent.createChooser(intent, "share"));
//                            }
//
//                            @Override
//                            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
//
//                            }
//                            @Override
//                            public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//                            }
//                        });
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,  productsList.get(position).getImage() );
                intent.setType("text/plain");
                c.startActivity(Intent.createChooser(intent, "Send To"));


            }
        });


        holder.setDatainView(productsList.get(position));

        //favourite Togglebtn
        holder.favourite.setChecked(false);
        holder.favourite.setBackgroundDrawable(ContextCompat.getDrawable(c, R.drawable.ic_favorite_empty));
        holder.favourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    holder.favourite.setBackgroundDrawable(ContextCompat.getDrawable(c, R.drawable.fav_icon));
                    productsList.get(position).setFav(true);
                    Log.d("IS FAV (IF WAS FALSE)", "FAV: " + productsList.get(position).isFav() );
                    Toast.makeText(c , "Fav Icon Succcesfully.." , Toast.LENGTH_LONG).show();

                }else{
                    holder.favourite.setBackgroundDrawable(ContextCompat.getDrawable(c, R.drawable.ic_favorite_empty));
                    productsList.get(position).setFav(false);
                    Log.d("IS FAV (IF TRUE)", "FAV: " + productsList.get(position).isFav() );
                    Toast.makeText(c , "Fav Empty Succcesfully.." , Toast.LENGTH_LONG).show();

                }
                holder.favouriteClickListener.onFavouriteClicked(position , productsList.get(position).isFav());
                Log.d("AFTER CALLING LISTENER", "FAV: " + productsList.get(position).isFav() );
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

//    private Uri getlocalBitmapUri(Bitmap bitmap) {
//        Uri bmuri = null;
//        try {
//            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "image.jpg");
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
//            fileOutputStream.close();
//            bmuri = Uri.fromFile(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e2) {
//            e2.printStackTrace();
//        }
//        return bmuri;
//    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    class favouriteHolder extends RecyclerView.ViewHolder {
        TextView Item_Name;
        TextView Item_Price ;
        ImageView Item_Image;
        ToggleButton favourite  ;
        ItemClickListener itemClickListener;
        FavouriteClickListener favouriteClickListener ;
        View rootView ;
        ImageButton ShareBtn ;

        public favouriteHolder(@NonNull View itemView,final ItemClickListener itemClickListener
                , FavouriteClickListener favouriteClickListener) {
            super(itemView);
            rootView = itemView ;
            this.favouriteClickListener = favouriteClickListener ;
            this.itemClickListener =itemClickListener;
            this.favourite = itemView.findViewById(R.id.iconImage);
            Item_Image = itemView.findViewById(R.id.itemImage);
            Item_Name = itemView.findViewById(R.id.itemName);
            Item_Price = itemView.findViewById(R.id.itemPrice);
            ShareBtn = itemView.findViewById(R.id.share_product);
        }



        public void setDatainView(final ProductitemModel item) {
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(item);
                }
            });
        }
    }

    interface ItemClickListener {
        void onItemClick(ProductitemModel item);
    }

    interface FavouriteClickListener {
        void onFavouriteClicked(int position , boolean isFav );
    }




}

