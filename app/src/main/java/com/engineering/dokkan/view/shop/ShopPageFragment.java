package com.engineering.dokkan.view.shop;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.ShopReviewModel;
import com.engineering.dokkan.data.models.ShopModel;
import com.engineering.dokkan.view.Favourite.Shop_Fragment;
import com.engineering.dokkan.view.MainActivity;
import com.engineering.dokkan.view.base.BaseFragment;
import com.engineering.dokkan.view.home.ShopRecyclerAdaptar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopPageFragment extends BaseFragment {
    private ArrayList<ShopReviewModel> reviewList;
    private RecyclerView reviewRecyclerView;
    private DatabaseReference databaseReference;
    Bundle bundle1;

    TextView shopname , location , desc ,about , policies ;
    Button fblink , instalink ;
    ImageView shopimg , fav , call , share ;
    RatingBar ratingBar ;

    String fb_link , insta_link , callnum ;



    public ShopPageFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop_page;
    }

    @Override
    public void initializeViews(View view) {
        initialization(view);
//        bundle1 = getArguments();
//        if( bundle1 != null) {
//            String shop_id = bundle1.getString("SHOP_KEY");
//            Log.d("SHOP_KEY_RECEIVE", shop_id);
//        }
        showShopDetails();
            }

    private void initialization(View view) {
        reviewRecyclerView = view.findViewById(R.id.recyclerview_review);

        shopimg = view.findViewById(R.id.img_shop) ;
        shopname = view.findViewById(R.id.name_shop);
        location = view.findViewById(R.id.location);
        desc = view.findViewById(R.id.shop_desc);
        ratingBar = view.findViewById(R.id.rating_bar) ;
        about = view.findViewById(R.id.about_tv);
        policies = view.findViewById(R.id.policies_tv);
        fblink = view.findViewById(R.id.tv_fb);
        instalink = view.findViewById(R.id.tv_insta);
        fav = view.findViewById(R.id.favourite_shop);
        call = view.findViewById(R.id.contact_shop);
        share = view.findViewById(R.id.share_shop);


    }

    private void showShopDetails() {
        final Query query = FirebaseDatabase.getInstance().getReference("shops")
                .orderByChild("key").equalTo("shopID2");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ShopModel shops = snapshot.getValue(ShopModel.class);
                    Picasso.get().load(shops.getShopImage()).into(shopimg);
                    shopname.setText(shops.getShopName());
                    ratingBar.setRating(shops.getRate());
                    location.setText(shops.getLocation());
                    desc.setText(shops.getBio());
                    about.setText(shops.getAbout());
                    policies.setText(shops.getPolicies());

                    fb_link = shops.getFbLink();
                    insta_link = shops.getInstaLink();
                    callnum = shops.getPhoneNum() ;


                    HashMap<String , String> map = shops.getReviews();
                    Collection<String> values = map.values();
                    //Creating an ArrayList of values
                    ArrayList<String> listOfIDs = new ArrayList<String>(values);

                    reviewList = new ArrayList<>();
                    for ( String id : listOfIDs){
                        Log.d("REVIEW_ID" , id) ;
                        showShopReviews(id);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity() , databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void showShopReviews(String id) {
        Query query = FirebaseDatabase.getInstance().getReference("Reviews")
                .orderByChild("Key").equalTo(id);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ShopReviewModel reviewModel = snapshot.getValue(ShopReviewModel.class);
                    reviewList.add(reviewModel);
                }

                ShopReviewRecycAdapter adapter = new ShopReviewRecycAdapter(reviewList);
                reviewRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                reviewRecyclerView.setAdapter(adapter);
//                DividerItemDecoration dv;
//                dv = new DividerItemDecoration(reviewRecyclerView.getContext(),
//                      ((LinearLayoutManager)new LinearLayoutManager(getActivity()) ).getOrientation());
//                reviewRecyclerView.addItemDecoration(dv);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity() , databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }

    @Override
    public void setListeners() {

        fblink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(fb_link) ;
                Intent i = new Intent(Intent.ACTION_VIEW , uri) ;
                startActivity(i);
            }
        });

        instalink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(insta_link);
                Intent i = new Intent(Intent.ACTION_VIEW , uri) ;
                startActivity(i);
            }
        });
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();

            }
        });

        //favoriteClick()
        //callClick()
        //shareClick()



    }

    private void openDialog() {
//        final Dialog dialog = new Dialog(getActivity()); // Context, this, etc.
//        dialog.setContentView(R.layout.layout_call_dialog);
//        dialog.setTitle("The phone Number :");
//        dialog.show();

        LayoutInflater inflater = getActivity().getLayoutInflater() ;
        View view = inflater.inflate(R.layout.layout_call_dialog , null);
        final AlertDialog.Builder mB =new AlertDialog.Builder(getActivity());

        mB.setTitle("The Phone Number :");
        mB.setView(view);
        mB.setCancelable(false);
        mB.setPositiveButton("call", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        mB.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog m = mB.create();
        mB.show();
    }


}
