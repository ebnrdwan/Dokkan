package com.engineering.dokkan.view.Favourite;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.FavitemModel;
import com.engineering.dokkan.view.base.BaseFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Item_Fragment extends BaseFragment {


     // ImageButton mShareBtn ;
     // ImageView mImageOfItem ;



// -------------
     private RecyclerView recyclerView;
     private ItemRecycAdapter mAdapter ;
     private ArrayList<FavitemModel> data;
     DatabaseReference mReferance;


    public Item_Fragment() {
    }


    @Override
    public int getLayoutId() {
        return R.layout.item_fragment;
    }

    @Override
    public void initializeViews(View view)
    {
        ShowItemData(view);
    }

    @Override
    public void setListeners() {


    }
    private void ShowItemData(View v) {
        recyclerView = v.findViewById(R.id.recycler_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        data = new ArrayList<FavitemModel>();
        mReferance = FirebaseDatabase.getInstance().getReference("Fav Item");
        mReferance.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    FavitemModel favModel = dataSnapshot1.getValue(FavitemModel.class);
                    data.add(favModel);
                }
                mAdapter = new ItemRecycAdapter(data);
                recyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }




// ...........................SHARE BUTTON ......................................

    public void onShareItem(View view) {
        // Get access to bitmap image from view
        ImageView ivImage = view.findViewById(R.id.itemImage);
        // Get access to the URI for the bitmap
        Uri bmpUri = getLocalBitmapUri(ivImage);
        if (bmpUri != null) {
            // Construct a ShareIntent with link to image
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
            shareIntent.setType("image/*");

            // Launch sharing dialog for image
            startActivity(Intent.createChooser(shareIntent, "Share Image"));

        } else {

        }
    }

    // Returns the URI path to the Bitmap displayed in specified ImageView
    public Uri getLocalBitmapUri(ImageView ivImage) {
        // Extract Bitmap from ImageView drawable
        Drawable drawable = ivImage.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable){
            bmp = ((BitmapDrawable) ivImage.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            File file =  new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }


}